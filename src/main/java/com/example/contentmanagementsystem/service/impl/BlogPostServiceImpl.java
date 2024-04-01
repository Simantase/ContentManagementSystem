package com.example.contentmanagementsystem.service.impl;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.example.contentmanagementsystem.entity.BlogPost;
import com.example.contentmanagementsystem.enums.PostType;
import com.example.contentmanagementsystem.exception.BlogPostNotFoundException;
import com.example.contentmanagementsystem.exception.UserAlreadyExixtsByEmailException;
import com.example.contentmanagementsystem.exception.UserIsNotFoundException;
import com.example.contentmanagementsystem.repository.BlogPostRepository;
import com.example.contentmanagementsystem.repository.BlogRepository;
import com.example.contentmanagementsystem.repository.ContributionPanelRepository;
import com.example.contentmanagementsystem.repository.UserRepository;
import com.example.contentmanagementsystem.requestdto.BlogPostRequest;
import com.example.contentmanagementsystem.responsedto.BlogPostResponse;
import com.example.contentmanagementsystem.service.BlogPostService;
import com.example.contentmanagementsystem.utility.ResponseStructure;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class BlogPostServiceImpl implements BlogPostService{
	private BlogPostRepository blogPostRepository;
	private ResponseStructure<BlogPostResponse> responseStructure;
	private BlogRepository blogRepository;
	private ContributionPanelRepository contributionPanelRepository;
	private UserRepository userRepository;
	@Override
	public ResponseEntity<ResponseStructure<BlogPostResponse>> createDraft(int blogId,
			BlogPostRequest blogPostRequest) {
		String email = SecurityContextHolder.getContext().getAuthentication().getName();
		return	userRepository.findByEmail(email).map(user->{
			return	blogRepository.findById(blogId).map(blog->{
				if(!blog.getUsers().getEmail().equals(email) && 
						contributionPanelRepository.existsByPanelIdAndContributors(blog.getContributionalPanel().getPanelId(),user))
					throw new UserIsNotFoundException("Invalid Input!!!");
				return blogRepository.findById(blogId).map(blog2->{
					BlogPost blogPost2 = mapToBlogPost(blogPostRequest,new BlogPost());
					blogPost2.setBlog(blog2);
					blogPost2.setPostType(PostType.DRAFT);
					return ResponseEntity.ok(responseStructure
							.setStatusCode(HttpStatus.OK.value())
							.setStatusMessage("Blog Post Data Is Saved Successfully")
							.setStatusData(mapToBlogPostResponse(blogPostRepository.save(blogPost2))));
				}).orElseThrow(()->new BlogPostNotFoundException("BlogPost Is Not Found!!!"));
			}).orElseThrow(()-> new UserAlreadyExixtsByEmailException("Invalid Input!!!"));
		}).orElseThrow(()->new BlogPostNotFoundException("Invalid Input!!!"));

	}

	private BlogPost mapToBlogPost(BlogPostRequest blogPostRequest,BlogPost blogPost) {
		blogPost.setTitle(blogPostRequest.getTitle());
		blogPost.setSubTitle(blogPostRequest.getSubTitle());
		blogPost.setSummary(blogPostRequest.getSummary());
		return blogPost;
	}
	private BlogPostResponse mapToBlogPostResponse(BlogPost blogPost) {
		return BlogPostResponse.builder()
				.title(blogPost.getTitle())
				.subTitle(blogPost.getSubTitle())
				.summary(blogPost.getSummary())
				.postType(blogPost.getPostType()).build();
	}

	@Override
	public ResponseEntity<ResponseStructure<BlogPostResponse>> updateDraft(int postId,BlogPostRequest blogPostRequest) {
		return blogPostRepository.findById(postId).map(blogpost->{
			return ResponseEntity.ok(responseStructure.setStatusCode(HttpStatus.OK.value())
					.setStatusMessage("Blog Post Data Is Updated Successfully")
					.setStatusData(mapToBlogPostResponse(blogPostRepository.save(mapToBlogPost(blogPostRequest, blogpost)))));
		}).orElseThrow(()->new BlogPostNotFoundException("Blog Post Is Not Updated!!!"));
	}


}
