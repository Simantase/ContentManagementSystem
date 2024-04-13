package com.example.contentmanagementsystem.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.contentmanagementsystem.entity.BlogPost;
import com.example.contentmanagementsystem.requestdto.BlogPostRequest;
import com.example.contentmanagementsystem.responsedto.BlogPostResponse;
import com.example.contentmanagementsystem.service.BlogPostService;
import com.example.contentmanagementsystem.utility.ErrorStructure;
import com.example.contentmanagementsystem.utility.ResponseStructure;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
@RestController
@AllArgsConstructor
public class BlogPostController {
	private BlogPostService blogPostService;
	@Operation(description = "User Will Be Able To Write Blog As A Draft",responses = {
			@ApiResponse(responseCode = "200",description = "Blog Post Data Is Saved"),
			@ApiResponse(responseCode = "404",description = "Blog Post Data Is Not Saved!!!",
			content = @Content(schema = @Schema(implementation = ErrorStructure.class)))
	})
	@PostMapping("/blogs/{blogId}/blog-posts")
	public ResponseEntity<ResponseStructure<BlogPostResponse>> createDraft(@PathVariable int blogId,
			@RequestBody @Valid BlogPostRequest blogPostRequest){
		return blogPostService.createDraft(blogId,blogPostRequest);
	}
	@Operation(description = "User Will Be Able To Write Blog As A Draft",responses = {
			@ApiResponse(responseCode = "200",description = "Blog Post Data Is Updated"),
			@ApiResponse(responseCode = "404",description = "Blog Post Data Is Not Updated!!!",
			content=@Content(schema = @Schema(implementation = ErrorStructure.class)))
	})
	@PutMapping("/blog-posts/{postId}")
	public ResponseEntity<ResponseStructure<BlogPostResponse>> updateDraft(@PathVariable int postId,
			@RequestBody @Valid BlogPostRequest blogPostRequest){
		return blogPostService.updateDraft(postId,blogPostRequest);
	}
	@Operation(description ="User is allowed to delete the blog post ",responses = {
			@ApiResponse(responseCode = "200",description = "Blog Post Is Deleted Successfully"),
			@ApiResponse(responseCode = "404",description = "Blog Post Is Not Deleted!!",
			content = @Content(schema = @Schema(implementation = ErrorStructure.class)))
	})
	@DeleteMapping("/blog-posts/{postId}")
	public ResponseEntity<ResponseStructure<BlogPostResponse>> deleteBlogPost(@PathVariable int postId){
		return blogPostService.deleteBlogPost(postId);
	}
	@Operation(description ="User Is Allowed To Find The Blog Post By Id",responses = {
			@ApiResponse(responseCode = "200",description = "Blog Post Is Deleted Successfully"),
			@ApiResponse(responseCode = "404",description = "Blog Post Is Not Deleted!!",
			content = @Content(schema = @Schema(implementation = ErrorStructure.class)))
	})
	@GetMapping("published/blog-posts/{postId}")
	public ResponseEntity<ResponseStructure<BlogPostResponse>> blogPostFindById(@PathVariable int postId){
		return blogPostService. blogPostFindById(postId);
	}

}
