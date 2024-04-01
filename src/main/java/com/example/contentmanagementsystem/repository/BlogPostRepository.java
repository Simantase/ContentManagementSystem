package com.example.contentmanagementsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.contentmanagementsystem.entity.BlogPost;

public interface BlogPostRepository extends JpaRepository<BlogPost,Integer>{

}
