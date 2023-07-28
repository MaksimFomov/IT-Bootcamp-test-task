package com.fomov.itbootcamptesttask.data.model;

import com.fomov.itbootcamptesttask.data.enums.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Long id;

	@Column(name = "first_name", length = 20, nullable = false)
	@Size(max = 20, message = "First name must be no more than 20 characters")
	private String firstName;

	@Column(name = "last_name", length = 40, nullable = false)
	@Size(max = 20, message = "Last name must be no more than 40 characters")
	private String lastName;

	@Column(name = "middle_name", length = 40, nullable = false)
	@Size(max = 20, message = "Middle name must be no more than 40 characters")
	private String middleName;

	@Column(name = "email", length = 50, unique = true, nullable = false)
	@Email(message = "Email must be valid")
	@Size(max = 50, message = "Email must be no more than 50 characters")
	private String email;

	@Column(name = "role", nullable = false)
	@Enumerated(EnumType.STRING)
	private Role role;
}
