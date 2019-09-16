package com.riis.ui.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.riis.service.UserService;
import com.riis.shared.dto.UserDto;
import com.riis.ui.model.request.UserDetailsRequestModel;
import com.riis.ui.model.response.UserRest;

@RestController
@RequestMapping("employees")
public class UserController {

	@Autowired
	UserService userService;

	@GetMapping(path = "/{email}")
	public UserRest getUser(@PathVariable String email) {

		UserRest returnValue = new UserRest();

		UserDto userDto = userService.getUser(email);
		BeanUtils.copyProperties(userDto, returnValue);
		return returnValue;
	}

	@PostMapping
	public UserRest createUser(@RequestBody UserDetailsRequestModel userDetails) {
		UserRest returnValue = new UserRest();
		UserDto userDto = new UserDto();
		BeanUtils.copyProperties(userDetails, userDto);

		UserDto createUser = userService.createUser(userDto);
		BeanUtils.copyProperties(createUser, returnValue);

		return returnValue;
	}

	@PutMapping(path = "/{email}")
	public UserRest updateUser(@PathVariable String email, @RequestBody UserDetailsRequestModel userDetails) {
		UserRest returnValue = new UserRest();
		UserDto userDto = new UserDto();
		BeanUtils.copyProperties(userDetails, userDto);

		UserDto createUser = userService.updateUser(email, userDto);
		BeanUtils.copyProperties(createUser, returnValue);

		return returnValue;
	}

	@DeleteMapping
	public String deleteUser() {
		return "deleteUser was called";
	}

	@GetMapping
	public List<UserRest> getAllUsers() {
		List<UserRest> returnValue = new ArrayList<>();

		List<UserDto> userlist = userService.getAllUsers();

		for (UserDto userDto : userlist) {
			UserRest userEntry = new UserRest();
			BeanUtils.copyProperties(userDto, userEntry);
			returnValue.add(userEntry);
		}

		return returnValue;
	}

}
