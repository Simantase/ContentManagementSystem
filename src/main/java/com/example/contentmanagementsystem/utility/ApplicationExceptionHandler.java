package com.example.contentmanagementsystem.utility;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import lombok.AllArgsConstructor;
@RestControllerAdvice
@AllArgsConstructor
public class ApplicationExceptionHandler extends ResponseEntityExceptionHandler {
	private ErrorStructure errorStructure;
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatusCode status, WebRequest request) {

		Map<String,String> messages=new HashMap<String,String>();
		ex.getAllErrors().forEach(error->{
			messages.put(((FieldError)error).getField(),error.getDefaultMessage());
		});
		return ResponseEntity.badRequest().body(
				errorStructure.setErrorCode(HttpStatus.BAD_REQUEST.value())
				.setErrorMessage("Invalid Inputs!!!")
				.setRootcause(messages));
	}

}
