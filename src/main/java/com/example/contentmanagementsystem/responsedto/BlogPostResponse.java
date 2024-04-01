package com.example.contentmanagementsystem.responsedto;
import com.example.contentmanagementsystem.enums.PostType;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
@Builder
public class BlogPostResponse {
	private String title;
	private String subTitle;
	private String summary;
	private PostType postType;
	
}
