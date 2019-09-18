package com.riis.shared.dto;

import java.io.Serializable;

public class BalanceDto implements Serializable {
	private static final long serialVersionUID = 6363515336460393750L;

	private int Id;
//	private int EmployeeID;
	private int HoursBalance;

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

//	public int getEmployeeID() {
//		return EmployeeID;
//	}
//
//	public void setEmployeeID(int employeeID) {
//		EmployeeID = employeeID;
//	}

	public int getHoursBalance() {
		return HoursBalance;
	}

	public void setHoursBalance(int hoursBalance) {
		HoursBalance = hoursBalance;
	}
}
