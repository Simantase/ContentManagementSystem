package com.example.contentmanagementsystem.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AboutLengthIsNotValidException extends RuntimeException {
private String name;
}
