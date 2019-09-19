package com.riis.io.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "CurrentBalance")
public class BalanceEntity implements Serializable {
	private static final long serialVersionUID = 3944534264044658731L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int Id;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "EmployeeID", referencedColumnName="id")
	private UserEntity employee;

	@Column(name = "HoursBalance")
	private int HoursBalance;

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public int getHoursBalance() {
		return HoursBalance;
	}

	public void setHoursBalance(int hoursBalance) {
		HoursBalance = hoursBalance;
	}

	public UserEntity getEmployee() {
		return employee;
	}

	public void setEmployee(UserEntity employee) {
		this.employee = employee;
	}
}
