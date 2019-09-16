package com.riis.service;

import com.riis.shared.dto.BalanceDto;

public interface BalanceService {
	int getBalance(int employeeID);
	void updateBalance(int employeeID, BalanceDto newBalanceDetails);
	BalanceDto createBalance(BalanceDto balanceDetails);

}
