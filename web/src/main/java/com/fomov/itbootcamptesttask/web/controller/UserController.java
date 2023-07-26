package com.fomov.itbootcamptesttask.web.controller;

import com.fomov.itbootcamptesttask.core.dto.UserRequestDTO;
import com.fomov.itbootcamptesttask.core.dto.UserResponseDTO;
import com.fomov.itbootcamptesttask.core.facade.UserFacade;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/users")
public class UserController {
	private final UserFacade userFacade;

	@PostMapping("/add")
	public ResponseEntity<UserResponseDTO> addUser(@RequestBody UserRequestDTO userRequestDTO) {
		UserResponseDTO userResponseDTO = userFacade.addUser(userRequestDTO);
		return ResponseEntity.status(HttpStatus.CREATED).body(userResponseDTO);
	}

	@GetMapping("/getAll")
	public ResponseEntity<List<UserResponseDTO>> getAllUsers(@RequestParam(defaultValue = "0") int page) {
		int pageSize = 10;

		Pageable pageable = PageRequest.of(page, pageSize, Sort.by("email").ascending());
		List<UserResponseDTO> users = userFacade.getAllUsers(pageable);

		return new ResponseEntity<>(users, HttpStatus.OK);

	}

}

