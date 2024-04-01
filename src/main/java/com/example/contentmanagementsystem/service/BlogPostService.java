package com.example.contentmanagementsystem.service;

import org.springframework.http.ResponseEntity;

import com.example.contentmanagementsystem.entity.BlogPost;
import com.example.contentmanagementsystem.requestdto.BlogPostRequest;
import com.example.contentmanagementsystem.responsedto.BlogPostResponse;
import com.example.contentmanagementsystem.utility.ResponseStructure;

import jakarta.validation.Valid;

public interface BlogPostService {

	ResponseEntity<ResponseStructure<BlogPostResponse>> createDraft(int blogId, @Valid BlogPostRequest blogPostRequest);

	ResponseEntity<ResponseStructure<BlogPostResponse>> updateDraft(int postId, @Valid BlogPostRequest blogPostRequest);

	
	

}
