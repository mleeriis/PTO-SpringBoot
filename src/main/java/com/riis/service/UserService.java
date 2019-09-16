package com.riis.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.riis.shared.dto.UserDto;

public interface UserService extends UserDetailsService {
	UserDto createUser(UserDto user);
	UserDto getUser(String email);
	List<UserDto> getAllUsers();
	UserDto updateUser(String email, UserDto user);
}
