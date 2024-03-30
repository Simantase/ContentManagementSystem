package com.example.contentmanagementsystem.utility;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.contentmanagementsystem.exception.BlogAlreadyExistsByTitleException;
import com.example.contentmanagementsystem.exception.BlogIsNotFoundByIdException;
import com.example.contentmanagementsystem.exception.BlogIsNotUpdatedException;
import com.example.contentmanagementsystem.exception.IllegalAccessRequestException;
import com.example.contentmanagementsystem.exception.TopicNotSpecifiedException;
import com.example.contentmanagementsystem.exception.UserAlreadyExixtsByEmailException;
import com.example.contentmanagementsystem.exception.UserIsNotFoundException;
import com.example.contentmanagementsystem.exception.UserNotFoundByIdException;

import lombok.AllArgsConstructor;

@RestControllerAdvice
@AllArgsConstructor
public class ApplicationHandler {
	private ErrorStructure<String> errorStructure;
	private ResponseEntity<ErrorStructure<String>> errorResponse(HttpStatus status,String message,String rootCause){
		return new ResponseEntity<ErrorStructure<String>>(errorStructure.setErrorCode(status.value())
				.setErrorMessage(message)
				.setRootcause(rootCause),status);
	}
	@ExceptionHandler
	public ResponseEntity<ErrorStructure<String>> handleUserAlreadyExixtByEmail(UserAlreadyExixtsByEmailException ex){
		return errorResponse(HttpStatus.BAD_REQUEST,ex.getMessage(),"User Already Exixt By Given Mail Id");
	}
	@ExceptionHandler
	public ResponseEntity<ErrorStructure<String>> handleUserNotFoundException(UserIsNotFoundException ex){
		return errorResponse(HttpStatus.BAD_REQUEST,ex.getMessage(),"User Is Found!!!");
	}
	@ExceptionHandler
	public ResponseEntity<ErrorStructure<String>> handleUserNotFoundByIdException(UserNotFoundByIdException ex){
		return errorResponse(HttpStatus.BAD_REQUEST,ex.getMessage(),"User Is Found!!!");
	}
	@ExceptionHandler
	public ResponseEntity<ErrorStructure<String>> handleBlogAlreadyExistsByTitleException(BlogAlreadyExistsByTitleException ex){
		return errorResponse(HttpStatus.BAD_REQUEST,ex.getMessage(),"Blog Is Not Found!!!");
	}
	@ExceptionHandler
	public ResponseEntity<ErrorStructure<String>> handleTopicNotSpecifiedException(TopicNotSpecifiedException ex){
		return errorResponse(HttpStatus.BAD_REQUEST,ex.getMessage(),"Blog Is Not Found!!!");
	}
	@ExceptionHandler
	public ResponseEntity<ErrorStructure<String>> handleBlogIsNotFoundByIdException(BlogIsNotFoundByIdException ex){
		return errorResponse(HttpStatus.BAD_REQUEST,ex.getMessage(),"Blog Is Not Found!!!");
	}
	@ExceptionHandler
	public ResponseEntity<ErrorStructure<String>> handleBlogIsNotUpdatedException(BlogIsNotUpdatedException ex){
		return errorResponse(HttpStatus.BAD_REQUEST,ex.getMessage(),"Invalid Blog Input!!!");
	}
	@ExceptionHandler
	public ResponseEntity<ErrorStructure<String>> handleIllegalAccessRequestException(IllegalAccessRequestException ex){
		return errorResponse(HttpStatus.BAD_REQUEST,ex.getMessage(),"Invalid Blog Input!!!");
	}
	
	


}
