package com.riis.io.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

public class UserEntity implements Serializable {

	private static final long serialVersionUID = 1369876620998898478L;

	@Id
	@GeneratedValue
	private int id;

	@Column(nullable = false, length = 20)
	private String Firstname;

	@Column(nullable = false, length = 40)
	private String Lastname;

	@Column(nullable = false, length = 40)
	private String email;

	@Column(nullable = false)
	private int RoleID;

	@Column(nullable = false, length = 50)
	private String Password;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getRoleID() {
		return RoleID;
	}

	public void setRoleID(int roleID) {
		RoleID = roleID;
	}

	public String getPassword() {
		return Password;
	}

	public void setPassword(String password) {
		Password = password;
	}

}
