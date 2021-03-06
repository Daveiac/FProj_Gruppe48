package data;

import java.io.Serializable;

public class Person implements Serializable{
	/*
	 * A class that represents an entry in the Person table in the database.
	 */

	/**
	 * 
	 */
	private static final long serialVersionUID = 368839089505185389L;
	private String email;
	private int phone;
	private String firstName;
	private String lastName;
	private String username;
	private String password;

	public Person(String email, int phone, String firstName, String lastName,
			String username, String password) {
		super();
		this.email = email;
		this.phone = phone;
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public int getPhone() {
		return phone;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	@Override
	public String toString() {
		return "Person [username=" + username + ", password=" + password + "]";
	}



}
