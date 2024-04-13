package com.example.contentmanagementsystem.controller;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.contentmanagementsystem.requestdto.PublishRequest;
import com.example.contentmanagementsystem.responsedto.PublishResponse;
import com.example.contentmanagementsystem.service.PublishService;
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
public class PublishController {
	private PublishService publishService;
	@Operation(description = "This EndPoint Is Used To Publish The Blog Post",responses = {
			@ApiResponse(responseCode = "200",description = "Blog Post Is Published Successfully"),
			@ApiResponse(responseCode = "404",description = "Invalid Operation!!!",
			content = @Content(schema =@Schema(implementation = ErrorStructure.class) ))
	})
	@PostMapping("/blog-posts/{postId}/publishes")
	public ResponseEntity<ResponseStructure<PublishResponse>> publishBlogPost(@RequestBody @Valid PublishRequest publishRequest,
			@PathVariable int postId){
		return publishService.publishBlogPost(publishRequest,postId);
	}
	@Operation(description = "This EndPoint Is Used To UnPublish The Blog Post",responses = {
			@ApiResponse(responseCode = "200",description = "Blog Post Is Unpublished Successfully"),
			@ApiResponse(responseCode = "404",description = "Invalid Operation!!!",
			content =@Content(schema = @Schema(implementation = ErrorStructure.class)) )
	})
	@PutMapping("/blog-posts/{postId}/publishes")
	public ResponseEntity<ResponseStructure<PublishResponse>> unpublishBlogPost(@PathVariable int postId){
		return publishService.unpublishBlogPost(postId);
	}
}
