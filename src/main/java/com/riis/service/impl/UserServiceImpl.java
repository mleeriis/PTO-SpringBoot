package com.riis.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.riis.io.entity.BalanceEntity;
import com.riis.io.entity.UserEntity;
import com.riis.io.repositories.UserRepository;
import com.riis.service.UserService;
import com.riis.shared.dto.BalanceDto;
import com.riis.shared.dto.UserDto;
import com.riis.ui.model.response.ErrorMessages;
import com.riis.ws.exceptions.PTOServiceException;
import com.riis.ws.exceptions.UserServiceException;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;

	@Autowired
	BCryptPasswordEncoder passwordEncoder;

	@Override
	public UserDto createUser(UserDto user) {
		if (userRepository.findByEmail(user.getEmail()) != null)
			throw new UserServiceException(ErrorMessages.RECORD_ALREADY_EXISTS.getErrorMessage());

		UserEntity userEntity = new UserEntity();
		BeanUtils.copyProperties(user, userEntity);

		BalanceEntity userBalance = new BalanceEntity();
		userBalance.setId(user.getId());
		userBalance.setHoursBalance(120);
		
		userEntity.setFirstname(user.getFirstname());
		userEntity.setLastname(user.getLastname());
		userEntity.setEmail(user.getEmail());
		userEntity.setRoleID(user.getRoleID());
		userEntity.setPassword(passwordEncoder.encode(user.getPassword()));
		userEntity.setId(user.getId());
		userEntity.setBalance(userBalance);
		UserEntity storedUserDetails = userRepository.save(userEntity);

		UserDto returnValue = new UserDto();
		BeanUtils.copyProperties(storedUserDetails, returnValue);

		return returnValue;

	}

	@Override
	public UserDto getUser(String email) {
		UserEntity userDetails = userRepository.findByEmail(email);

		if (userDetails == null)
			throw new UserServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());

		UserDto returnValue = new UserDto();
		BeanUtils.copyProperties(userDetails, returnValue);
		
		BalanceDto balanceDto = new BalanceDto();
		BeanUtils.copyProperties(userDetails.getBalance(), balanceDto);
		
		returnValue.setBalance(balanceDto);

		return returnValue;
	}
	
	@Override
	public UserDto updateUser(String email, UserDto user) {
		UserDto returnValue = new UserDto();

		UserEntity foundUser = userRepository.findByEmail(email);

		if (foundUser == null)
			throw new PTOServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());

		foundUser.setPassword(passwordEncoder.encode(user.getPassword()));

		UserEntity updatedUserEntity = userRepository.save(foundUser);
		BeanUtils.copyProperties(updatedUserEntity, returnValue);

		return returnValue;
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		UserEntity userEntity = userRepository.findByEmail(email);

		if (userEntity == null)
			throw new UserServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());
		return new User(userEntity.getEmail(), userEntity.getPassword(), new ArrayList<>());
	}

	@Override
	public List<UserDto> getAllUsers() {
		List<UserDto> returnValue = new ArrayList<>();
		
		Iterable<UserEntity> userList = userRepository.findAll();

		for (UserEntity userEntity : userList) {
			UserDto userDto = new UserDto();
			BeanUtils.copyProperties(userEntity, userDto);
			
			BalanceDto balanceDto = new BalanceDto();
			BeanUtils.copyProperties(userEntity.getBalance(), balanceDto);
			userDto.setBalance(balanceDto);
			
			returnValue.add(userDto);
		}
		
		return returnValue;
	}
}
