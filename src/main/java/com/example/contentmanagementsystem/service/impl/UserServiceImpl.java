package com.example.contentmanagementsystem.service.impl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.contentmanagementsystem.entity.User;
import com.example.contentmanagementsystem.exception.UserAlreadyExixtsByEmailException;
import com.example.contentmanagementsystem.repository.UserRepository;
import com.example.contentmanagementsystem.requestdto.UserRequestDto;
import com.example.contentmanagementsystem.responsedto.UserResponseDto;
import com.example.contentmanagementsystem.service.UserService;
import com.example.contentmanagementsystem.utility.ResponseStructure;

import lombok.AllArgsConstructor;
@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService{
	private UserRepository userRepository;
	private ResponseStructure<UserResponseDto> responseStructure;
	private PasswordEncoder passwordEncoder;
	@Override
	public ResponseEntity<ResponseStructure<UserResponseDto>> register(UserRequestDto userRequestDto) {
		if(userRepository.existsByEmail(userRequestDto.getEmail()))
			throw new UserAlreadyExixtsByEmailException("Failed To Register User");
		
		User user=userRepository.save(mapToUser(userRequestDto,new User()));
		return  ResponseEntity.ok(
				responseStructure.setStatusCode(HttpStatus.OK.value())
				.setStatusMessage("Data Is Registered Successfully")
				.setStatusData(mapToUserResponse(user)));
	}
	private User mapToUser(UserRequestDto userRequestDto,User user) {
		user.setUsername(userRequestDto.getUsername());
		user.setEmail(userRequestDto.getEmail());
		user.setPassword(passwordEncoder.encode(userRequestDto.getPassword()));
		return user;
	}
	private UserResponseDto mapToUserResponse(User user) {
		return UserResponseDto.builder()
				.userId(user.getUserId())
				.username(user.getUsername())
				.email(user.getEmail()).createdAt(user.getCreatedAt())
				.lastModifiedAt(user.getLastModifiedAt()).build();

	}

}
