package com.example.contentmanagementsystem.repository;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.contentmanagementsystem.entity.User;

public interface UserRepository extends JpaRepository<User,Integer>{
	boolean existsByEmail(String email);
	Optional<User> findByEmail(String email);
}
