package com.riis.ui.model.response;

public class UserRest {
	private int id;
	private String firstname;
	private String lastname;
	private String email;
	private int roleID;
	private int hoursBalance;

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

	public int getHoursBalance() {
		return hoursBalance;
	}

	public void setHoursBalance(int HoursBalance) {
		this.hoursBalance = HoursBalance;
	}

}
