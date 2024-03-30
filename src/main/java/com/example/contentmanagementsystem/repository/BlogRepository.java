package com.example.contentmanagementsystem.repository;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.contentmanagementsystem.entity.Blog;
import com.example.contentmanagementsystem.entity.ContributionalPanel;
import com.example.contentmanagementsystem.entity.User;

public interface BlogRepository extends JpaRepository<Blog, Integer> {
	boolean existsByTitle(String title);
	boolean existsByUsersAndContributionalPanel(User owner, ContributionalPanel panel);
	




}
