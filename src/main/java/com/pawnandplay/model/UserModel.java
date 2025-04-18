package com.pawnandplay.model;

import java.time.LocalDate;

public class UserModel {
	private String userId;
	private String firstName;
	private String lastName;
	private String username;
	private String email;
	private String number;
	private LocalDate dob;
	private String password;
	
	public UserModel() {}
	
	public UserModel(String firstName, String lastName, String username, String email, String number, LocalDate dob, String password) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.number = number;
		this.username = username;
		this.dob = dob;
		this.password = password;
	}
	
	public UserModel(String username, String password) {
		this.username= username;
		this.password = password;
	}

	public UserModel(String id, String firstName, String lastName, String userName, String email, String number, LocalDate dob,
			String password) {
		super();
		this.userId = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = userName;
		this.email = email;
		this.number = number;
		this.dob = dob;
		this.password = password;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}


	public LocalDate getDob() {
		return dob;
	}

	public void setDob(LocalDate dob) {
		this.dob = dob;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
