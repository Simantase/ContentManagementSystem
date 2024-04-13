package com.example.contentmanagementsystem;
import java.time.LocalDateTime;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
@SpringBootApplication
@EnableScheduling
public class ContentManagementSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(ContentManagementSystemApplication.class, args);
		System.out.println("Application Has Started");
		
	}

} 
