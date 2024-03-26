package com.example.contentmanagementsystem.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.contentmanagementsystem.requestdto.UserRequestDto;
import com.example.contentmanagementsystem.responsedto.UserResponseDto;
import com.example.contentmanagementsystem.service.impl.UserServiceImpl;
import com.example.contentmanagementsystem.utility.ResponseStructure;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class UserController {
	private UserServiceImpl userServiceImpl;

	@Operation(description = "This EndPoint Is Used To Register The Data ", responses = {
			@ApiResponse(responseCode = "200", description = "Data Is Registered Successfully"),
			@ApiResponse(responseCode = "404", description = "Invalid Inputs!!") })
	@PostMapping("/users/register")
	public ResponseEntity<ResponseStructure<UserResponseDto>> register(
			@RequestBody @Valid UserRequestDto userRequestDto) {
		return userServiceImpl.register(userRequestDto);
	}
	@GetMapping("/test")
	public String test() {
		return "WELCOME TO BANGALORE";
	}

}
