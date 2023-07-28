package com.fomov.itbootcamptesttask.web.controller;

import com.fomov.itbootcamptesttask.core.dto.UserRequestDTO;
import com.fomov.itbootcamptesttask.core.dto.UserResponseDTO;
import com.fomov.itbootcamptesttask.core.facade.UserFacade;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	@PostMapping
	public ResponseEntity<UserResponseDTO> addUser(@RequestBody UserRequestDTO userRequestDTO) {
		logger.info("Received request to add a new user: {}", userRequestDTO);

		UserResponseDTO userResponseDTO = userFacade.addUser(userRequestDTO);
		logger.info("User successfully added: {}", userResponseDTO);

		return ResponseEntity.status(HttpStatus.CREATED).body(userResponseDTO);
	}

	@GetMapping
	public ResponseEntity<List<UserResponseDTO>> getAllUsers(@RequestParam(defaultValue = "0") int page) {
		logger.info("Received request to get all users, page: {}", page);

		int pageSize = 10;

		Pageable pageable = PageRequest.of(page, pageSize, Sort.by("email").ascending());
		List<UserResponseDTO> users = userFacade.getAllUsers(pageable);

		logger.info("Returning {} users for page {}", users.size(), page);

		return new ResponseEntity<>(users, HttpStatus.OK);

	}

}

