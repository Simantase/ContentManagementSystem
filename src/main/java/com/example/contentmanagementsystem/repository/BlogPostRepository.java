package com.example.contentmanagementsystem.repository;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;

import com.example.contentmanagementsystem.entity.BlogPost;
import com.example.contentmanagementsystem.enums.PostType;
import com.example.contentmanagementsystem.responsedto.BlogPostResponse;
import com.example.contentmanagementsystem.utility.ResponseStructure;

public interface BlogPostRepository extends JpaRepository<BlogPost,Integer>{
	Optional<BlogPost> findByPostIdAndPostType(int postId, PostType published);
	List<BlogPost> findByPublishScheduleDateTimeLessThanEqual(LocalDateTime now);


}
