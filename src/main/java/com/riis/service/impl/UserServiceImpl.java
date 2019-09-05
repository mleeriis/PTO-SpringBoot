package com.riis.service.impl;

import java.util.ArrayList;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.riis.UserRepository;
import com.riis.io.entity.UserEntity;
import com.riis.service.UserService;
import com.riis.shared.dto.UserDto;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;

	@Autowired
	BCryptPasswordEncoder passwordEncoder;

	@Override
	public UserDto createUser(UserDto user) {
//		UserEntity foundUserDetails = userRepository.findByEmail(user.getEmail());
//		
//		if(foundUserDetails != null) throw new RuntimeException("Already exists");

		UserEntity userEntity = new UserEntity();
		BeanUtils.copyProperties(user, userEntity);

		userEntity.setFirstname(user.getFirstname());
		userEntity.setLastname(user.getLastname());
		userEntity.setEmail(user.getEmail());
		userEntity.setRoleID(user.getRoleID());
//		userEntity.setPassword(user.getPassword());
		// TODO: Using bCrypt on the password makes it too long to store in the database
		userEntity.setPassword(passwordEncoder.encode(user.getPassword()));

		UserEntity storedUserDetails = userRepository.save(userEntity);

		UserDto returnValue = new UserDto();
		BeanUtils.copyProperties(storedUserDetails, returnValue);

		return returnValue;

	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		UserEntity userEntity = userRepository.findByEmail(email);
		
		if(userEntity == null) throw new UsernameNotFoundException(email);
		return new User(userEntity.getEmail(), userEntity.getPassword(), new ArrayList<>());
	}

}
