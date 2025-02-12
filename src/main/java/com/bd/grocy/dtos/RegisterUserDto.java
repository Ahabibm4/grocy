package com.bd.grocy.dtos;

import lombok.Data;

@Data
public class RegisterUserDto {
	
	private String email;

	private String password;
	
	private String fullName;
	
	private String userName;
}
