package com.example.contentmanagementsystem.utility;
import org.springframework.stereotype.Component;

import lombok.Getter;
@Component
@Getter
public class ErrorStructure<T> {
	private int errorCode;
	private String errorMessage;
	private T rootcause;
	public ErrorStructure<T> setErrorCode(int errorCode) {
		this.errorCode = errorCode;
		return this;
	}
	public ErrorStructure<T> setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
		return this;
	}
	public ErrorStructure<T> setRootcause(T rootcause) {
		this.rootcause = rootcause;
		return this;
	}


}
