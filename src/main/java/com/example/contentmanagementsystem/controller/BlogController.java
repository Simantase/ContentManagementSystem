package com.example.contentmanagementsystem.controller;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.contentmanagementsystem.entity.Blog;
import com.example.contentmanagementsystem.requestdto.BlogRequest;
import com.example.contentmanagementsystem.responsedto.BlogResponse;
import com.example.contentmanagementsystem.service.BlogService;
import com.example.contentmanagementsystem.utility.ErrorStructure;
import com.example.contentmanagementsystem.utility.ResponseStructure;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
@AllArgsConstructor
@RestController
public class BlogController {
	private BlogService blogService;
	@Operation(description = "This EndPoint Is Used To Save The Blog Data",responses = {
			@ApiResponse(responseCode = "200",description = "Blog Data Is Saved Successlly"),
			@ApiResponse(responseCode = "404",description = "Invalid Operation!!!",
			content = @Content(schema = @Schema(implementation = ErrorStructure.class)))
	})
	@PostMapping("/users/{userId}/blogs")
	public ResponseEntity<ResponseStructure<BlogResponse>> createBlog(@PathVariable int userId, @RequestBody @Valid BlogRequest blogRequest){
		return blogService.createBlog(userId, blogRequest);
	}
	@Operation(description = "This EndPoint Is Used To Save The Blog Data",responses = {
			@ApiResponse(responseCode = "200",description = "Blog Data Is Saved Successlly"),
			@ApiResponse(responseCode = "404",description = "Invalid Operation!!!" ,
			content= {@Content(schema = @Schema(implementation = ErrorStructure.class))})
	})
	@GetMapping("/titles/{title}/blogs")
	public ResponseEntity<Boolean> fetchByTitle(@PathVariable String title){
		return blogService.fetchByTitle(title);
	}
	@Operation(description = "This EndPoint Is Used To Fetch The Blog By Id",responses = {
			@ApiResponse(responseCode = "200",description = "Blog Data Is Fetched Successfully"),
			@ApiResponse(responseCode = "404",description = "Invalid Operation!!",
			content = @Content(schema = @Schema(implementation = ErrorStructure.class)))
	})
	@GetMapping("/blogs/{blogId}")
	public ResponseEntity<ResponseStructure<BlogResponse>> findBlogById(@PathVariable int blogId){
		return blogService. findBlogById(blogId);
	}
	@Operation(description = "This EndPoint Is Used To Update The Blog Data",responses = {
			@ApiResponse(responseCode = "200",description = "Data Is Updated Successfully"),
			@ApiResponse(responseCode = "404",description = "Invalid Operation!!!",
			content = @Content(schema = @Schema(implementation = ErrorStructure.class)))
	})
	@PutMapping("/blogs/{blogId}")
	public ResponseEntity<ResponseStructure<BlogResponse>> updateblogData(@PathVariable int blogId, @RequestBody  BlogRequest blogRequest){
		return blogService.updateblogData(blogId,blogRequest);
	}
}