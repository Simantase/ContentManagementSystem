package com.example.contentmanagementsystem.entity;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.stereotype.Component;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;
@Setter
@Getter
@Entity
@EntityListeners(value = {AuditingEntityListener.class})
@Component
public class Blog {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int blogId;
	@Column(unique = true)
	private String title;
	private String[] topics;
	private String about;
	@CreatedDate
	@Column(updatable=false)//For Auditing
	private LocalDateTime createdAt;//For Auditing
	@LastModifiedDate//For Auditing
	private LocalDateTime lastModifiedAt;//For Auditing
	@CreatedBy
	@Column(updatable = false)
	private String createdBy;
	@LastModifiedBy
	private String lastModifiedBy;
	
	@ManyToOne
	private User users;

	@OneToOne
	private ContributionalPanel contributionalPanel;

	@OneToMany(mappedBy = "blog")
	private List<BlogPost> blogPosts;
}
