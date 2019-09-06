package com.riis.ui.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.riis.service.PTOService;
import com.riis.shared.dto.PTODto;
import com.riis.ui.model.request.PTODetailsRequestModel;
import com.riis.ui.model.response.PTORest;


@RestController
@RequestMapping("pto")
public class PTOController {
	@Autowired
	PTOService ptoService;
	
	@GetMapping
	public String getUser() {
		return "called get pto";
	}

	@PostMapping
	public PTORest createUser(@RequestBody PTODetailsRequestModel ptoDetails) {
		PTORest returnValue = new PTORest();
		
		PTODto ptoDto = new PTODto();
		BeanUtils.copyProperties(ptoDetails, ptoDto);
		
		PTODto createPto = ptoService.createPTO(ptoDto);
		BeanUtils.copyProperties(createPto, returnValue);
		
		return returnValue;
	}

	@PutMapping
	public String updateUser() {
		return "update pto was called";
	}

	@DeleteMapping
	public String deleteUser() {
		return "delete pto was called";
	}

}
