package com.fomov.itbootcamptesttask.core.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRequestDTO {
	private String firstName;
	private String lastName;
	private String middleName;
	private String email;
	private String role;
}
