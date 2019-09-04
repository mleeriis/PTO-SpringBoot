package com.riis.ui.model.request;

public class UserDetailsRequestModel {
	private String firstName;
	private String lastName;
	private String email;
	private int roleID;
	private String password;

	public String getFirstname() {
		return firstName;
	}

	public void setFirstname(String firstname) {
		firstName = firstname;
	}

	public String getLastname() {
		return lastName;
	}

	public void setLastname(String lastname) {
		lastName = lastname;
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

}
