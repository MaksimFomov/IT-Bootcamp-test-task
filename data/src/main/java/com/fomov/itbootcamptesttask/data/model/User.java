package com.fomov.itbootcamptesttask.data.model;

import com.fomov.itbootcamptesttask.data.enums.Role;
import jakarta.persistence.*;
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
	private String firstName;

	@Column(name = "last_name", length = 40, nullable = false)
	private String lastName;

	@Column(name = "middle_name", length = 40, nullable = false)
	private String middleName;

	@Column(name = "email", length = 50, unique = true, nullable = false)
	private String email;

	@Column(name = "role", nullable = false)
	@Enumerated(EnumType.STRING)
	private Role role;
}
