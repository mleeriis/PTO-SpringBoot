package com.riis.shared.dto;

import java.io.Serializable;

public class UserDto implements Serializable {

	private static final long serialVersionUID = -5537125870292466195L;
	private int id;
	private String firstname;
	private String lastname;
	private String email;
	private int roleID;

	private String password;
	private BalanceDto balance;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getRoleID() {
		return roleID;
	}

	public void setRoleID(int roleID) {
		this.roleID = roleID;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public BalanceDto getBalance() {
		return balance;
	}

	public void setBalance(BalanceDto balance) {
		this.balance = balance;
	}

}
