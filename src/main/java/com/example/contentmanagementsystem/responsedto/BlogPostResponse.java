package com.example.contentmanagementsystem.responsedto;
import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import com.example.contentmanagementsystem.enums.PostType;

import jakarta.persistence.Column;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
@Builder
public class BlogPostResponse {
	private int postId;
	private String title;
	private String subTitle;
	private String summary;
	private PostType postType;
	private LocalDateTime createdAt;
	private String createdBy;
	private LocalDateTime lastModifiedAt;
	private String lastModifiedBy;
	private PublishResponse publishResponse;
}
