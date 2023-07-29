package com.fomov.itbootcamptesttask.core.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class UserRequestDTO {
	@NotBlank(message = "First name is required")
	@Size(max = 20, message = "First name should not exceed 20 characters")
	private String firstName;

	@NotBlank(message = "Last name is required")
	@Size(max = 40, message = "Last name should should not exceed 40 characters")
	private String lastName;

	@NotBlank(message = "Middle name is required")
	@Size(max = 40, message = "Middle name should not exceed 40 characters")
	private String middleName;

	@Email(message = "Email should be valid")
	@NotBlank(message = "Email is required")
	@Size(max = 50, message = "Email should not exceed 50 characters")
	private String email;

	private String role;

	public UserRequestDTO(String firstName, String lastName, String middleName, String email, String role) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.middleName = middleName;
		this.email = email;
		this.role = role;
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

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
}
