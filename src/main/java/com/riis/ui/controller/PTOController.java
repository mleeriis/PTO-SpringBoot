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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.riis.service.PTOService;
import com.riis.shared.dto.PTODto;
import com.riis.ui.model.request.PTODetailsRequestModel;
import com.riis.ui.model.response.OperationStatusModel;
import com.riis.ui.model.response.PTORest;
import com.riis.ui.model.response.RequestOperationStatus;

@RestController
@RequestMapping("pto")
public class PTOController {
	@Autowired
	PTOService ptoService;

	@GetMapping(path = "/{id}")
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

	@PutMapping(path = "/{id}")
	public PTORest updatePTORequest(@PathVariable String id, @RequestBody PTODetailsRequestModel ptoDetails) {
		PTORest returnValue = new PTORest();

		PTODto ptoDto = new PTODto();
		BeanUtils.copyProperties(ptoDetails, ptoDto);

		PTODto updatePto = ptoService.updatePTO(Integer.valueOf(id), ptoDto);
		BeanUtils.copyProperties(updatePto, returnValue);

		return returnValue;
	}

	@DeleteMapping(path = "/{id}")
	public OperationStatusModel deletePTORequest(@PathVariable String id) {
		OperationStatusModel returnValue = new OperationStatusModel();
		returnValue.setOperationName("DELETE");
		ptoService.deletePTO(Integer.valueOf(id));
		returnValue.setOperationResult(RequestOperationStatus.SUCCESS.name());

		return returnValue;
	}

	@GetMapping
	public List<PTORest> getPTORequests(@RequestParam(value = "empID", defaultValue = "0") int employeeID,
			@RequestParam(value = "roleID", defaultValue = "2") int roleID,
			@RequestParam(value = "page", defaultValue = "0") int page,
			@RequestParam(value = "limit", defaultValue = "25") int limit) {
		List<PTORest> returnValue = new ArrayList<>();

		List<PTODto> ptoList = ptoService.getPTO(employeeID, page, limit);

		for (PTODto ptoDto : ptoList) {
			PTORest ptoEntry = new PTORest();
			BeanUtils.copyProperties(ptoDto, ptoEntry);
			returnValue.add(ptoEntry);
		}

		return returnValue;
	}

}
