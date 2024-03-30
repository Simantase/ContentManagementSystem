package com.example.contentmanagementsystem.exception;
import lombok.AllArgsConstructor;
import lombok.Getter;
@Getter
@AllArgsConstructor
public class TopicNotSpecifiedException extends RuntimeException {
	private String message;
}
