package com.riis.ui.controller;

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

import com.riis.service.PTOService;
import com.riis.shared.dto.PTODto;
import com.riis.ui.model.request.PTODetailsRequestModel;
import com.riis.ui.model.response.PTORest;


@RestController
@RequestMapping("pto")
public class PTOController {
	@Autowired
	PTOService ptoService;
	
	@GetMapping(path="/{id}")
	public PTORest getPTORequest(@PathVariable String id) {
		PTORest returnValue = new PTORest();
		
		PTODto ptoDto = ptoService.findPTO(Integer.valueOf(id));
		BeanUtils.copyProperties(ptoDto, returnValue);
		
		return returnValue;
	}

	@PostMapping
	public PTORest createPTORequest(@RequestBody PTODetailsRequestModel ptoDetails) {
		PTORest returnValue = new PTORest();
		
		PTODto ptoDto = new PTODto();
		BeanUtils.copyProperties(ptoDetails, ptoDto);
		
		PTODto createPto = ptoService.createPTO(ptoDto);
		BeanUtils.copyProperties(createPto, returnValue);
		
		return returnValue;
	}

	@PutMapping(path="/{id}")
	public PTORest updatePTORequest(@PathVariable String id, @RequestBody PTODetailsRequestModel ptoDetails) {
		PTORest returnValue = new PTORest();
		
		PTODto ptoDto = new PTODto();
		BeanUtils.copyProperties(ptoDetails, ptoDto);
		
		PTODto updatePto = ptoService.updatePTO(Integer.valueOf(id), ptoDto);
		BeanUtils.copyProperties(updatePto, returnValue);
		
		return returnValue;
	}

	@DeleteMapping
	public String deletePTORequest() {
		return "delete pto was called";
	}
	
//	@GetMapping
//	public List<PTORest> getPTORequests(@RequestParam(value = "empID", defaultValue = "37") int employeeID,
//			@RequestParam(value = "roleID", defaultValue = "2") int roleID){
//		List<PTORest> returnValue = new ArrayList<>();
//		
//		return returnValue;
//	}
	

}
