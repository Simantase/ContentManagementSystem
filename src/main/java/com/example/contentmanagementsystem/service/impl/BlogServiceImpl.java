package com.example.contentmanagementsystem.service.impl;

import java.util.Arrays;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.contentmanagementsystem.entity.Blog;
import com.example.contentmanagementsystem.entity.ContributionalPanel;
import com.example.contentmanagementsystem.exception.AboutLengthIsNotValidException;
import com.example.contentmanagementsystem.exception.BlogAlreadyExistsByTitleException;
import com.example.contentmanagementsystem.exception.BlogIsNotFoundByIdException;
import com.example.contentmanagementsystem.exception.BlogIsNotUpdatedException;
import com.example.contentmanagementsystem.exception.TopicNotSpecifiedException;
import com.example.contentmanagementsystem.exception.UserNotFoundByIdException;
import com.example.contentmanagementsystem.repository.BlogRepository;
import com.example.contentmanagementsystem.repository.ContributionPanelRepository;
import com.example.contentmanagementsystem.repository.UserRepository;
import com.example.contentmanagementsystem.requestdto.BlogRequest;
import com.example.contentmanagementsystem.responsedto.BlogResponse;
import com.example.contentmanagementsystem.service.BlogService;
import com.example.contentmanagementsystem.utility.ResponseStructure;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class BlogServiceImpl implements BlogService {
	private UserRepository userRepository;
	private BlogRepository blogRepository;
	private ContributionPanelRepository contributionPanelRepository;
	private ResponseStructure<BlogResponse> responseStructure;

	@Override
	public ResponseEntity<ResponseStructure<BlogResponse>> createBlog(int userId, BlogRequest blogRequest) {
		return userRepository.findById(userId).map(user -> {
			if (blogRepository.existsByTitle(blogRequest.getTitle())) {
				throw new BlogAlreadyExistsByTitleException("Invalid blog title");
			}
			if (blogRequest.getTopics().length < 1) {
				throw new TopicNotSpecifiedException("Failed to create");
			}
			Blog blog = mapToBlog(blogRequest, new Blog());

			//blog.setUsers(user);// ***

			ContributionalPanel contributionalPanel=contributionPanelRepository.save(new ContributionalPanel());
			blog.setContributionalPanel(contributionalPanel);
			blog.setUsers(user);
			blog=blogRepository.save(blog);

			blogRepository.save(blog);
			userRepository.save(user);
			return blog;
		}).map(blog -> ResponseEntity.ok(responseStructure.setStatusCode(HttpStatus.OK.value())
				.setStatusMessage("Blog added sucessfully to thr user").setStatusData(mapToResponse(blog))))
				.orElseThrow(() -> new UserNotFoundByIdException("Invalid userID"));
	}

	private BlogResponse mapToResponse(Blog blog) {
		return BlogResponse.builder().blogId(blog.getBlogId()).about(blog.getAbout()).topics(blog.getTopics())
				.title(blog.getTitle()).build();
	}

	private Blog mapToBlog(BlogRequest blogRequest, Blog blog) {
		blog.setTitle(blogRequest.getTitle());
		blog.setAbout(blogRequest.getAbout());
		blog.setTopics(blogRequest.getTopics());
		return blog;
	}

	@Override
	public ResponseEntity<Boolean> fetchByTitle(String title) {
		return ResponseEntity.ok(blogRepository.existsByTitle(title));
	}

	@Override
	public ResponseEntity<ResponseStructure<BlogResponse>> findBlogById(int blogId) {
		return blogRepository.findById(blogId)
				.map(blog -> ResponseEntity.ok(responseStructure
						.setStatusCode(HttpStatus.OK.value())
						.setStatusMessage("Blog Data Is Fetched Successfully")
						.setStatusData(mapToResponse(blog))))
				.orElseThrow(() -> new BlogIsNotFoundByIdException("Blog Is Not Found!!!"));
	}

	@Override
	public ResponseEntity<ResponseStructure<BlogResponse>> updateblogData(int blogId, BlogRequest blogRequest) {
		return blogRepository.findById(blogId).map(blog -> {
			if (blogRepository.existsByTitle(blogRequest.getTitle()))
				throw new BlogAlreadyExistsByTitleException("Blog Tile Is Exist!!");
			if (blog.getAbout().length() < 1)
				throw new AboutLengthIsNotValidException("Blog About Length Is Not Valid!!");

			return ResponseEntity.ok(
					responseStructure.setStatusCode(HttpStatus.OK.value())
					.setStatusMessage("Blog Data Is Updated!!!")
					.setStatusData(mapToResponse(blogRepository.save(mapToBlog(blogRequest, blog)))));
		}).orElseThrow(() -> new BlogIsNotUpdatedException("Blog Data Is Not Updated!!"));
	}

}