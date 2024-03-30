package com.example.contentmanagementsystem.requestdto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BlogRequest {

	@NotNull(message = "The Title Shouldn't be Null")
	//@Pattern(regexp = "[a-zA-Z]", message = "Title supports only alphabets")
	private String title;
	@NotNull(message = "The Topics Shouldn't be Null")
	private String[] topics;
	private String about;
}
