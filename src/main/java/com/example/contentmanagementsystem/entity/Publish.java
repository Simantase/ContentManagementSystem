package com.example.contentmanagementsystem.entity;
import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.annotation.Generated;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;
@Entity
@Getter
@Setter
@EntityListeners(value = {AuditingEntityListener.class})
public class Publish {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int publishId;
	private String seoTitle;
	private String seoDescription;
	private String seoTags;
	@CreatedDate
	@Column(updatable = false)
	private LocalDateTime createdAt;
	@LastModifiedDate
	private LocalDateTime lastModifiedAt;
	@CreatedBy
	@Column(updatable = false)
	private String createdBy;
	@LastModifiedBy
	private String lastModifiedBy;

	@OneToOne
	private BlogPost blogPost;
	
	@OneToOne
	private Schedule schedule;


}
