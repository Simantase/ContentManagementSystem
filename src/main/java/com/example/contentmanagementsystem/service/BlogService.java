package com.example.contentmanagementsystem.service;
import org.springframework.http.ResponseEntity;

import com.example.contentmanagementsystem.entity.Blog;
import com.example.contentmanagementsystem.requestdto.BlogRequest;
import com.example.contentmanagementsystem.responsedto.BlogResponse;
import com.example.contentmanagementsystem.utility.ResponseStructure;
public interface BlogService {
	ResponseEntity<ResponseStructure<BlogResponse>> createBlog(int userId, BlogRequest blogRequest);
	ResponseEntity<Boolean> fetchByTitle(String title);
	ResponseEntity<ResponseStructure<BlogResponse>> findBlogById(int blogId);
	ResponseEntity<ResponseStructure<BlogResponse>> updateblogData(int blogId, BlogRequest blogRequest);

	

}
