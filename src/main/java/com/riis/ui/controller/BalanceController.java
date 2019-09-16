package com.riis.ui.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.riis.service.BalanceService;
import com.riis.shared.dto.BalanceDto;
import com.riis.ui.model.response.OperationStatusModel;
import com.riis.ui.model.response.RequestOperationStatus;

@RestController
@RequestMapping("balance")
public class BalanceController {
	@Autowired
	BalanceService balanceService;
	
	@GetMapping(path = "/{id}")
	public int getBalance(@PathVariable String id) {
		int balance = balanceService.getBalance(Integer.valueOf(id));
		return balance;
	}
	
	@PostMapping
	public BalanceDto createBalance(@RequestBody BalanceDto newBalanceDetails) {
		BalanceDto returnValue = balanceService.createBalance(newBalanceDetails);
		
		return returnValue;
	}
	
	@PutMapping(path = "/{id}")
	public OperationStatusModel updateBalance(@PathVariable String id, @RequestBody BalanceDto newBalanceDetails) {
		OperationStatusModel returnValue = new OperationStatusModel();
		returnValue.setOperationName("UPDATE");
		balanceService.updateBalance(Integer.valueOf(id), newBalanceDetails);
		returnValue.setOperationResult(RequestOperationStatus.SUCCESS.name());
		
		return returnValue;
	}
	
	@DeleteMapping
	public String deleteBalance() {
		return "Delete balance";
	}

}
