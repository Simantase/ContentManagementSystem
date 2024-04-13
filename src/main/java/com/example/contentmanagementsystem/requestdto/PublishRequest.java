package com.example.contentmanagementsystem.requestdto;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PublishRequest {
	@NotNull(message = "SeoTitle Should Not Be Null")
	@NotBlank(message = "SeoTitle Should Not Be Blank")
	@NotEmpty(message = "SeoTitle Should Not Be Empty")
	private String seoTitle;
	private String seoDescription;
	private String seoTags;
	private LocalDateTime dateTime;
	private ScheduleRequest schedule;
}
