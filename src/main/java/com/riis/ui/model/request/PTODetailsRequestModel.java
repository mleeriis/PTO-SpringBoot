package com.riis.ui.model.request;

import java.sql.Date;

public class PTODetailsRequestModel {
	private int EmployeeID;
	private Date StartDate;
	private Date EndDate;
	private int Status;

	public int getEmployeeID() {
		return EmployeeID;
	}

	public void setEmployeeID(int employeeID) {
		EmployeeID = employeeID;
	}

	public Date getStartDate() {
		return StartDate;
	}

	public void setStartDate(String startDate) {
		StartDate = Date.valueOf(startDate);
	}

	public Date getEndDate() {
		return EndDate;
	}

	public void setEndDate(String endDate) {
		EndDate = Date.valueOf(endDate);
	}

	public int getStatus() {
		return Status;
	}

	public void setStatus(int status) {
		Status = status;
	}
}
