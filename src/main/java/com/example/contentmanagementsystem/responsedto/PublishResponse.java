package com.example.contentmanagementsystem.responsedto;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
@Builder
public class PublishResponse {
	private int publishId;
	private String seoTitle;
	private String seoDescription;
	private String seoTags;
}
