package com.example.contentmanagementsystem.service.impl;
import java.time.LocalDateTime;
import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import com.example.contentmanagementsystem.entity.BlogPost;
import com.example.contentmanagementsystem.entity.Publish;
import com.example.contentmanagementsystem.entity.Schedule;
import com.example.contentmanagementsystem.enums.PostType;
import com.example.contentmanagementsystem.exception.PostIsNotFoundByIdException;
import com.example.contentmanagementsystem.exception.ValidateDateException;
import com.example.contentmanagementsystem.repository.BlogPostRepository;
import com.example.contentmanagementsystem.repository.PublishRepository;
import com.example.contentmanagementsystem.requestdto.PublishRequest;
import com.example.contentmanagementsystem.requestdto.ScheduleRequest;
import com.example.contentmanagementsystem.responsedto.PublishResponse;
import com.example.contentmanagementsystem.service.PublishService;
import com.example.contentmanagementsystem.utility.ResponseStructure;

import lombok.AllArgsConstructor;
@Service
@AllArgsConstructor
public class PublishServiceImpl implements PublishService{
	private PublishRepository publishRepository;
	private BlogPostRepository blogPostRepository;
	private ResponseStructure<PublishResponse> responseStructure;
	@Override
	public ResponseEntity<ResponseStructure<PublishResponse>> publishBlogPost(PublishRequest publishRequest,
			int postId) {
		String email = SecurityContextHolder.getContext().getAuthentication().getName();
		return blogPostRepository.findById(postId).map(blogpost->{
			if(!blogpost.getBlog().getUsers().getEmail().equals(email))
				throw new PostIsNotFoundByIdException("We Can Not Publish Blog Post");
			Publish publish=null;
			if(publishRequest.getSchedule()!=null) {
				blogpost.setPostType(PostType.SCHEDULED);
			}
			blogpost.setPostType(PostType.PUBLISHED);
			publish.setBlogPost(blogpost);
			return	ResponseEntity.ok(responseStructure
					.setStatusCode(HttpStatus.OK.value())
					.setStatusMessage("User Will Be Able To Publish BlogPost")
					.setStatusData(mapToResponse(publishRepository.save(publish))));
		}).orElseThrow(()->new PostIsNotFoundByIdException("Post Is Not Found By Id!!!"));
	}
	@Override
	public ResponseEntity<ResponseStructure<PublishResponse>> unpublishBlogPost(int postId) {
		String email=SecurityContextHolder.getContext().getAuthentication().getName();
		return blogPostRepository.findById(postId).map(blogpost->{
			if(!blogpost.getBlog().getUsers().getEmail().equals(email))
				throw new PostIsNotFoundByIdException("We Can Not Publish Blog Post");

			blogpost.setPostType(PostType.DRAFT);
			BlogPost updateBlogPost=blogPostRepository.save(blogpost);
			if(updateBlogPost.getPublish()!=null) {
				return	ResponseEntity.ok(responseStructure
						.setStatusCode(HttpStatus.OK.value())
						.setStatusMessage("User Will Be Able To Unpublish BlogPost")
						.setStatusData(mapToResponse(updateBlogPost.getPublish())));
			}
			else {
				return	ResponseEntity.ok(responseStructure
						.setStatusCode(HttpStatus.OK.value())
						.setStatusMessage("User Will Be Able To Unpublish BlogPost"));
			}

		}).orElseThrow(()->new PostIsNotFoundByIdException("Post Is Not Found By Id!!!"));
	}
	private Publish mapToPublish(PublishRequest publishRequest,Publish publish) {
		publish.setSeoTitle(publishRequest.getSeoTitle());
		publish.setSeoDescription(publishRequest.getSeoDescription());
		publish.setSeoTags(publishRequest.getSeoTags());
		publishRequest.getDateTime();
		return publish;
	}
	private PublishResponse mapToResponse(Publish publish) {
		return PublishResponse.builder()
				.publishId(publish.getPublishId())
				.seoTitle(publish.getSeoTitle())
				.seoDescription(publish.getSeoDescription())
				.seoTags(publish.getSeoTags())
				.build();
	}
	private Schedule mapToSchedule(ScheduleRequest scheduleRequest,Schedule schedule) {
		schedule.setDateTime(scheduleRequest.getDateTime());
		return schedule;

	}


}
