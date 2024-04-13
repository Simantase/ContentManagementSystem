package com.example.contentmanagementsystem.service.impl;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import com.example.contentmanagementsystem.entity.Blog;
import com.example.contentmanagementsystem.entity.BlogPost;
import com.example.contentmanagementsystem.entity.Publish;
import com.example.contentmanagementsystem.enums.PostType;
import com.example.contentmanagementsystem.exception.BlogIsNotFoundByIdException;
import com.example.contentmanagementsystem.exception.BlogPostNotFoundException;
import com.example.contentmanagementsystem.exception.IllegalAccessRequestException;
import com.example.contentmanagementsystem.exception.UserAlreadyExixtsByEmailException;
import com.example.contentmanagementsystem.exception.UserIsNotFoundException;
import com.example.contentmanagementsystem.repository.BlogPostRepository;
import com.example.contentmanagementsystem.repository.BlogRepository;
import com.example.contentmanagementsystem.repository.ContributionPanelRepository;
import com.example.contentmanagementsystem.repository.UserRepository;
import com.example.contentmanagementsystem.requestdto.BlogPostRequest;
import com.example.contentmanagementsystem.responsedto.BlogPostResponse;
import com.example.contentmanagementsystem.responsedto.BlogPostResponse.BlogPostResponseBuilder;
import com.example.contentmanagementsystem.responsedto.PublishResponse;
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
				if(!blog.getUsers().getEmail().equals(email) && //******
						contributionPanelRepository.existsByPanelIdAndContributors(blog.getContributionalPanel().getPanelId(),user))//****
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
			}).orElseThrow(()->new BlogPostNotFoundException("Invalid Input!!!"));
		}).orElseThrow(()-> new UserAlreadyExixtsByEmailException("Invalid Input!!!"));


	}

	private BlogPost mapToBlogPost(BlogPostRequest blogPostRequest,BlogPost blogPost) {
		blogPost.setTitle(blogPostRequest.getTitle());
		blogPost.setSubTitle(blogPostRequest.getSubTitle());
		blogPost.setSummary(blogPostRequest.getSummary());
		return blogPost;
	}
	private BlogPostResponse mapToBlogPostResponse(BlogPost blogPost) {
		return BlogPostResponse.builder()
				.postId(blogPost.getPostId())
				.title(blogPost.getTitle())
				.subTitle(blogPost.getSubTitle())
				.summary(blogPost.getSummary())
				.postType(blogPost.getPostType()).build();
	}

	@Override
	public ResponseEntity<ResponseStructure<BlogPostResponse>> updateDraft(int postId,BlogPostRequest blogPostRequest) {
		String email = SecurityContextHolder.getContext().getAuthentication().getName();
		return	userRepository.findByEmail(email).map(user->{
			return blogPostRepository.findById(postId).map(blogpost->{
				if(!blogpost.getBlog().getUsers().getEmail().equals(email) && 
						contributionPanelRepository.existsByPanelIdAndContributors(blogpost.getBlog().getContributionalPanel().getPanelId(),user))
					throw new UserIsNotFoundException("Invalid Input!!!");
				return ResponseEntity.ok(responseStructure.setStatusCode(HttpStatus.OK.value())
						.setStatusMessage("Blog Post Data Is Updated Successfully")
						.setStatusData(mapToBlogPostResponse1(blogPostRepository.save(mapToBlogPost(blogPostRequest, blogpost)))));
			}).orElseThrow(()->new BlogPostNotFoundException("Blog Post Is Not Updated!!!"));
		}).orElseThrow(()-> new UserAlreadyExixtsByEmailException("Invalid Input!!!"));
	}
	@Override
	public ResponseEntity<ResponseStructure<BlogPostResponse>> deleteBlogPost(int postId) {
		String email = SecurityContextHolder.getContext().getAuthentication().getName();
		return	blogPostRepository.findById(postId).map(blogpost->{
			//			if(!blogpost.getBlog().getUsers().getEmail().equals(email) || !blogpost.getCreatedBy().equals(email))
			//				throw new IllegalAccessRequestException("Failed To Delete Blog Post!!!");

			blogPostRepository.deleteById(postId);
			return	ResponseEntity.ok(responseStructure
					.setStatusCode(HttpStatus.OK.value())
					.setStatusMessage("Blog Data Is Deleted Successfully")
					.setStatusData(mapToBlogPostResponse1(blogpost)));
		}).orElseThrow(()->new BlogPostNotFoundException("Blog Post Is Not Deleted!!!"));
	}

	@Override
	public ResponseEntity<ResponseStructure<BlogPostResponse>> blogPostFindById(int postId) {
		String email = SecurityContextHolder.getContext().getAuthentication().getName();
		return	blogPostRepository.findByPostIdAndPostType(postId,PostType.PUBLISHED).map(blogpost->{
			//			if(!blogpost.getBlog().getUsers().getEmail().equals(email) || !blogpost.getCreatedBy().equals(email))
			//				throw new IllegalAccessRequestException("Invalid Input");

			return	ResponseEntity.ok(responseStructure.setStatusCode(HttpStatus.OK.value())
					.setStatusMessage("Blog Post Is Found By Id")
					.setStatusData(mapToBlogPostResponse1(blogpost)));
		}).orElseThrow(()->new BlogIsNotFoundByIdException("Blog Is Not Found By Id!!!"));
	}
	private PublishResponse mapToPublishResponse(Publish publish) {
		return PublishResponse.builder()
				.publishId(publish.getPublishId())
				.seoTitle(publish.getSeoTitle())
				.seoDescription(publish.getSeoDescription())
				.seoTags(publish.getSeoTags()).build();
	}
	private BlogPostResponse mapToBlogPostResponse1(BlogPost blogPost) {
		BlogPostResponse response = BlogPostResponse.builder()
				.postId(blogPost.getPostId())
				.title(blogPost.getTitle())
				.subTitle(blogPost.getSubTitle())
				.summary(blogPost.getSummary())
				.postType(blogPost.getPostType())
				.createdAt(blogPost.getCreatedAt())
				.lastModifiedAt(blogPost.getLastModifiedAt())
				.createdBy(blogPost.getCreatedBy())
				.lastModifiedBy(blogPost.getLastModifiedBy()).build();
		if(blogPost.getPublish()!=null)//if it is not null then it add publish response
			response.setPublishResponse(mapToPublishResponse(blogPost.getPublish()));
		return response;
	}



}
