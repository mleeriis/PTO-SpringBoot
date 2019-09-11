package com.riis.service.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.riis.io.entity.UserEntity;
import com.riis.io.repositories.UserRepository;
import com.riis.shared.dto.UserDto;
import com.riis.ws.exceptions.UserServiceException;

class UserServiceImplTest {

	@InjectMocks
	UserServiceImpl userService;

	@Mock
	UserRepository userRepository;
	
	@Mock
	BCryptPasswordEncoder passwordEncoder;

	private UserEntity userEntityStub = new UserEntity();
	private UserDto userDtoStub = new UserDto();

	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		userEntityStub.setEmail("test@test.com");
		userEntityStub.setFirstname("Joe");
		userEntityStub.setLastname("Smith");
		userEntityStub.setId(1);
		userEntityStub.setRoleID(1);
		userEntityStub.setPassword("password");
		
		userDtoStub.setEmail("test@test.com");
		userDtoStub.setFirstname("Joe");
		userDtoStub.setLastname("Smith");
		userDtoStub.setId(1);
		userDtoStub.setRoleID(1);
		userDtoStub.setPassword("password");
	}

	@Test
	void successfullyGetUserWithCorrectDetails() {

		when(userRepository.findByEmail(anyString())).thenReturn(userEntityStub);

		UserDto userDto = userService.getUser("test@test.com");

		assertNotNull(userDto);
		assertEquals(userEntityStub.getFirstname(), userDto.getFirstname());
		assertEquals(userEntityStub.getLastname(), userDto.getLastname());
		assertEquals(userEntityStub.getEmail(), userDto.getEmail());
		assertEquals(userEntityStub.getId(), userDto.getId());
		assertEquals(userEntityStub.getRoleID(), userDto.getRoleID());
		assertEquals(userEntityStub.getPassword(), userDto.getPassword());
	}

	@Test
	void throwExceptionWhenUserNotFound() {
		when(userRepository.findByEmail(anyString())).thenReturn(null);

		assertThrows(UserServiceException.class, () -> {
			userService.getUser("test@test.com");
		});
	}
	
	@Test
	void successfullyCreateNewUser() {
		when(userRepository.findByEmail(anyString())).thenReturn(null);
		when(passwordEncoder.encode(anyString())).thenReturn("password");
		when(userRepository.save(any(UserEntity.class))).thenReturn(userEntityStub);
		
		UserDto userDto = userService.createUser(userDtoStub);
		
		assertNotNull(userDto);
		assertEquals(userEntityStub.getFirstname(), userDto.getFirstname());
		assertEquals(userEntityStub.getLastname(), userDto.getLastname());
		assertEquals(userEntityStub.getEmail(), userDto.getEmail());
		assertEquals(userEntityStub.getId(), userDto.getId());
		assertEquals(userEntityStub.getRoleID(), userDto.getRoleID());
		assertEquals(userEntityStub.getPassword(), userDto.getPassword());
		verify(passwordEncoder, times(1)).encode("password");
		verify(userRepository, times(1)).save(any(UserEntity.class));	
	}

	@Test
	void throwExceptionWhenUserAlreadyCreated() {
		// Used in findPTO(), updatePTO(), and deletePTO()
		when(userRepository.findByEmail(anyString())).thenReturn(userEntityStub);

		assertThrows(UserServiceException.class, () -> {
			userService.createUser(userDtoStub);
		});
	}
	
	

}
