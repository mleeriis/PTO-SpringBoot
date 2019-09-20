package com.riis.io.entity;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Requests")
@SecondaryTables({
    @SecondaryTable(name="Employees"),
    @SecondaryTable(name="CurrentBalance")
})
public class PTOEntity implements Serializable {

	private static final long serialVersionUID = -5961917022589138570L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int Id;
	
	@Column(name="EmployeeID")
	private int EmployeeID;
	
	@Column(name="StartDate")
	private Date StartDate;
	
	@Column(name="EndDate")
	private Date EndDate;
	
	@Column(name="Status")
	private int Status;
	
	@Column(insertable = false, updatable = false, table="Employees")
	private String Firstname;
	
	@Column(insertable = false, updatable = false, table="Employees")
	private String Lastname;
	
	@Column(insertable = false, updatable = false, table="CurrentBalance")
	private Integer HoursBalance;
	
	public int getId() {
		return Id;
	}
	public void setId(int id) {
		Id = id;
	}
	public int getEmployeeID() {
		return EmployeeID;
	}
	public void setEmployeeID(int employeeID) {
		EmployeeID = employeeID;
	}
	public Date getStartDate() {
		return StartDate;
	}
	public void setStartDate(Date startDate) {
		StartDate = startDate;
	}
	public Date getEndDate() {
		return EndDate;
	}
	public void setEndDate(Date endDate) {
		EndDate = endDate;
	}
	public int getStatus() {
		return Status;
	}
	public void setStatus(int status) {
		Status = status;
	}
	public String getFirstname() {
		return Firstname;
	}
	public void setFirstname(String firstname) {
		Firstname = firstname;
	}
	public String getLastname() {
		return Lastname;
	}
	public void setLastname(String lastname) {
		Lastname = lastname;
	}
	public Integer getHoursBalance() {
		return HoursBalance;
	}
	public void setHoursBalance(Integer hoursBalance) {
		HoursBalance = hoursBalance;
	}

}
