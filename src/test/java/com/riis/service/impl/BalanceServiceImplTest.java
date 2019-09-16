package com.riis.service.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.riis.io.entity.BalanceEntity;
import com.riis.io.repositories.BalanceRepository;
import com.riis.shared.dto.BalanceDto;
import com.riis.ws.exceptions.BalanceServiceException;

class BalanceServiceImplTest {

	@InjectMocks
	BalanceServiceImpl balanceService;

	@Mock
	BalanceRepository balanceRepository;

	private BalanceEntity balanceEntityStub = new BalanceEntity();
	private BalanceDto balanceDtoStub = new BalanceDto();

	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);

		balanceEntityStub.setId(1);
		balanceEntityStub.setEmployeeID(1);
		balanceEntityStub.setHoursBalance(120);
		
		balanceDtoStub.setId(1);
		balanceDtoStub.setEmployeeID(1);
		balanceDtoStub.setHoursBalance(120);
	}

	@Test
	void successfullyGetBalance() {
		when(balanceRepository.findByEmployeeID(anyInt())).thenReturn(balanceEntityStub);

		int returnedHoursBalance = balanceService.getBalance(1);

		assertNotNull(returnedHoursBalance);
		assertEquals(balanceEntityStub.getHoursBalance(), returnedHoursBalance);
	}

	@Test
	void throwExceptionWhenEmployeeIDDoesNotExist() {
		when(balanceRepository.findByEmployeeID(anyInt())).thenReturn(null);
		
		assertThrows(BalanceServiceException.class, () -> {balanceService.getBalance(1);});
		assertThrows(BalanceServiceException.class, () -> {balanceService.updateBalance(1, balanceDtoStub);});
	}
	
	@Test
	void successfullyCreateBalanceEntryForEmployeeID() {
		when(balanceRepository.save(any(BalanceEntity.class))).thenReturn(balanceEntityStub);
		
		BalanceDto createdBalanceDto = balanceService.createBalance(balanceDtoStub);
		
		assertNotNull(createdBalanceDto);
		assertEquals(balanceDtoStub.getId(), createdBalanceDto.getId());
		assertEquals(balanceDtoStub.getEmployeeID(), createdBalanceDto.getEmployeeID());
		assertEquals(balanceDtoStub.getHoursBalance(), createdBalanceDto.getHoursBalance());
	}
	
	@Test
	void throwExceptionWhenBalanceAlreadyCreatedForEmployeeID() {
		when(balanceRepository.findByEmployeeID(anyInt())).thenReturn(balanceEntityStub);
		
		assertThrows(BalanceServiceException.class, () -> {balanceService.createBalance(balanceDtoStub);});
	}

}
