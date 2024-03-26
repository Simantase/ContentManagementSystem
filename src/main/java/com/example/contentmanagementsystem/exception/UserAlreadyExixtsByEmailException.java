package com.example.contentmanagementsystem.exception;
import lombok.AllArgsConstructor;
import lombok.Getter;
@Getter
@AllArgsConstructor
public class UserAlreadyExixtsByEmailException extends RuntimeException {
	private String message;
}
