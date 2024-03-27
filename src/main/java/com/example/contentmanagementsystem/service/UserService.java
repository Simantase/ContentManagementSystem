package com.example.contentmanagementsystem.service;
import org.springframework.http.ResponseEntity;

import com.example.contentmanagementsystem.entity.User;
import com.example.contentmanagementsystem.requestdto.UserRequestDto;
import com.example.contentmanagementsystem.responsedto.UserResponseDto;
import com.example.contentmanagementsystem.utility.ResponseStructure;
public interface UserService {
	ResponseEntity<ResponseStructure<UserResponseDto>> register(UserRequestDto userRequestDto);
}
