package com.riis.io.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Employees")
public class UserEntity implements Serializable {

	private static final long serialVersionUID = 1369876620998898478L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Column(name="Firstname", nullable = false, length = 20)
	private String Firstname;

	@Column(name="Lastname", nullable = false, length = 40)
	private String Lastname;

	@Column(name="email", nullable = false, length = 40, unique=true)
	private String email;

	@Column(name="RoleID", nullable = false)
	private int RoleID;

	@Column(name="Password", nullable = false, length = 50)
	private String Password;
	
	@Column(insertable = false, updatable = false)
	private int HoursBalance;

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
