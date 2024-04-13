package com.example.contentmanagementsystem.service;

import org.springframework.http.ResponseEntity;

import com.example.contentmanagementsystem.requestdto.PublishRequest;
import com.example.contentmanagementsystem.responsedto.PublishResponse;
import com.example.contentmanagementsystem.utility.ResponseStructure;

import jakarta.validation.Valid;

public interface PublishService {
	ResponseEntity<ResponseStructure<PublishResponse>> publishBlogPost(PublishRequest publishRequest, int postId);

	ResponseEntity<ResponseStructure<PublishResponse>> unpublishBlogPost(int postId);
	

}
