package com.example.contentmanagementsystem.entity;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;
@Entity
@EntityListeners(value = {AuditingEntityListener.class})//For Auditing
@Getter
@Setter
@Table(name = "User")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int userId;
	private String username;
	private String email;
	private String password;
	@CreatedDate//For Auditing
	@Column(updatable=false)//For Auditing
	private LocalDateTime createdAt;//For Auditing
	@LastModifiedDate//For Auditing
	private LocalDateTime lastModifiedAt;//For Auditing
	private Boolean deleted;

	@OneToMany(mappedBy = "users")
	private List<Blog> blogs;

	

}
