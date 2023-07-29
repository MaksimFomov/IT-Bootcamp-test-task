package com.fomov.itbootcamptesttask.web;

import com.fomov.itbootcamptesttask.core.dto.UserRequestDTO;
import com.fomov.itbootcamptesttask.core.dto.UserResponseDTO;
import com.fomov.itbootcamptesttask.core.exception.UserAlreadyExistsException;
import com.fomov.itbootcamptesttask.core.facade.UserFacade;
import com.fomov.itbootcamptesttask.web.controller.UserController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserControllerTest {
	@InjectMocks
	private UserController userController;

	@Mock
	private UserFacade userFacade;

	@Test
	public void testAddUser_Success() {
		UserRequestDTO userRequestDTO = new UserRequestDTO("Maksim", "Fomov", "Aleksandrovich", "maksimfomov26@gmail.com", "ROLE_ADMINISTRATOR");
		UserResponseDTO userResponseDTO = new UserResponseDTO("Fomov Maksim Aleksandrovich ", "maksimfomov26@gmail.com",  "ROLE_ADMINISTRATOR");

		when(userFacade.addUser(userRequestDTO)).thenReturn(userResponseDTO);

		ResponseEntity<?> responseEntity = userController.addUser(userRequestDTO);

		assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
		assertEquals(userResponseDTO, responseEntity.getBody());
	}

	@Test
	public void testAddUser_UserAlreadyExistsException() {
		UserRequestDTO userRequestDTO = new UserRequestDTO("Maksim", "Fomov", "Aleksandrovich", "maksimfomov26@gmail.com", "ROLE_ADMINISTRATOR");

		when(userFacade.addUser(userRequestDTO)).thenThrow(new UserAlreadyExistsException("User with this email already exists"));

		ResponseEntity<?> responseEntity = userController.addUser(userRequestDTO);

		assertEquals(HttpStatus.CONFLICT, responseEntity.getStatusCode());
		assertEquals("User with this email already exists", responseEntity.getBody());
	}

	@Test
	public void testAddUser_InvalidUserRequestDTO() {
		UserRequestDTO invalidUserRequestDTO = new UserRequestDTO("Maksim", "Fomov", "Aleksandrovich", "maks@mail.ru", "ROLE_ADMINISTRATORd");

		ResponseEntity<?> responseEntity = userController.addUser(invalidUserRequestDTO);

		assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
	}

	@Test
	public void testGetAllUsers() {
		int page = 0;
		int pageSize = 10;
		List<UserResponseDTO> users = Arrays.asList(
				new UserResponseDTO("Fomov Maksim Aleksandrovich ", "maksimfomov26@gmail.com",  "ROLE_ADMINISTRATOR"),
				new UserResponseDTO("Ivanov Ivan Aleksandrovich ", "ivanov@gmail.com",  "ROLE_ADMINISTRATOR")
		);

		Pageable pageable = PageRequest.of(page, pageSize, Sort.by("email").ascending());
		when(userFacade.getAllUsers(pageable)).thenReturn(users);

		ResponseEntity<?> responseEntity = userController.getAllUsers(page);

		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		assertEquals(users, responseEntity.getBody());
	}

	@Test
	public void testGetAllUsers_EmptyList() {
		int page = 0;
		int pageSize = 10;
		List<UserResponseDTO> emptyList = Collections.emptyList();

		Pageable pageable = PageRequest.of(page, pageSize, Sort.by("email").ascending());
		when(userFacade.getAllUsers(pageable)).thenReturn(emptyList);

		ResponseEntity<?> responseEntity = userController.getAllUsers(page);

		assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
		assertEquals("Users not found for page " + pageable.getPageNumber(), responseEntity.getBody());
	}
}

