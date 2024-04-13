package com.example.contentmanagementsystem.utility;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import com.example.contentmanagementsystem.entity.BlogPost;
import com.example.contentmanagementsystem.enums.PostType;
import com.example.contentmanagementsystem.repository.BlogPostRepository;

import lombok.AllArgsConstructor;
@Component
@AllArgsConstructor
public class ScheduleJob {
	private BlogPostRepository blogPostRepository;
	@Scheduled(fixedDelay = 60*1000l)
	public void logDateTime() {
		//System.out.println(LocalDateTime.now());
		//		List<BlogPost> posts=blogPostRepository.findAllByPublishScheduleDateTimeLessThanEqualAndPostType(LocalDateTime.now(),PostType.SCHEDULED)
		//				.stream().map(post->{
		//					post.setPostType(PostType.PUBLISHED);
		//					return post;
		//				}).collect(Collectors.toList());
		List<BlogPost> list=blogPostRepository.findByPublishScheduleDateTimeLessThanEqual(LocalDateTime.now())
				.stream().map(post->{
					post.setPostType(PostType.PUBLISHED);
					return post;
				}).collect(Collectors.toList());
		blogPostRepository.saveAll(list);
	}
}
