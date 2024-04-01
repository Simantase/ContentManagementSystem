package com.example.contentmanagementsystem.entity;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.example.contentmanagementsystem.enums.PostType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;
@Entity
@Getter
@Setter
@EntityListeners(value = {AuditingEntityListener.class})
public class BlogPost {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int postId;
	private String title;
	private String subTitle;
	@Column(length = 4000)
	private String summary;
	private PostType postType;
	
	@CreatedDate
	@Column(updatable = false)
	private LocalDateTime createdAt;
	@CreatedBy
	@Column(updatable = false)
	private String createdBy;
	@LastModifiedDate
	private LocalDateTime lastModifiedAt;
	@LastModifiedBy
	private String lastModifiedBy;

	@ManyToOne
	private Blog blog;
}
