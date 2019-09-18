package com.riis.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.riis.io.entity.BalanceEntity;
import com.riis.io.repositories.BalanceRepository;
import com.riis.service.BalanceService;
import com.riis.shared.dto.BalanceDto;
import com.riis.ui.model.response.ErrorMessages;
import com.riis.ws.exceptions.BalanceServiceException;

@Service
public class BalanceServiceImpl implements BalanceService {
	@Autowired
	BalanceRepository balanceRepository;

	@Override
	public int getBalance(int employeeID) {
		BalanceEntity foundBalanceEntry = balanceRepository.findByEmployeeID(employeeID);
		
		if(foundBalanceEntry == null)
			throw new BalanceServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());
		
		return foundBalanceEntry.getHoursBalance();
	}

	@Override
	public void updateBalance(int employeeID, BalanceDto newBalanceDetails) {
		BalanceEntity foundBalanceEntry = balanceRepository.findByEmployeeID(employeeID);
		
		if(foundBalanceEntry == null)
			throw new BalanceServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());
		
		balanceRepository.updateHoursBalance(employeeID, newBalanceDetails.getHoursBalance());
	}

	@Override
	public BalanceDto createBalance(BalanceDto balanceDetails) {
//		if(balanceRepository.findByEmployeeID(balanceDetails.getEmployeeID()) != null)
//			throw new BalanceServiceException(ErrorMessages.RECORD_ALREADY_EXISTS.getErrorMessage());
		
		BalanceEntity balanceEntity = new BalanceEntity();
		BeanUtils.copyProperties(balanceDetails, balanceEntity);
		
//		balanceEntity.setEmployeeID(balanceDetails.getEmployeeID());
		balanceEntity.setHoursBalance(balanceDetails.getHoursBalance());
		
		BalanceEntity storedBalanceDetails = balanceRepository.save(balanceEntity);
		
		BalanceDto returnValue = new BalanceDto();
		BeanUtils.copyProperties(storedBalanceDetails, returnValue);
		
		return returnValue;
	}
	

}
