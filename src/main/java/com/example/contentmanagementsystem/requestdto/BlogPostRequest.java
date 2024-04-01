package com.example.contentmanagementsystem.requestdto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class BlogPostRequest {
	private String title;
	@NotNull(message = "The Title Should Not Be Null")
	private String subTitle;
	private String summary;
}
