package com.fomov.itbootcamptesttask.web;

import com.fomov.itbootcamptesttask.core.dto.UserRequestDTO;
import com.fomov.itbootcamptesttask.core.dto.UserResponseDTO;
import com.fomov.itbootcamptesttask.core.exception.UserAlreadyExistsException;
import com.fomov.itbootcamptesttask.core.exception.UserNotFoundException;
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
import org.springframework.validation.BindingResult;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
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

		BindingResult bindingResult = mock(BindingResult.class);
		when(bindingResult.hasErrors()).thenReturn(false);

		ResponseEntity<?> responseEntity = userController.addUser(userRequestDTO, bindingResult);

		assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
	}

	@Test
	public void testAddUser_UserAlreadyExistsException() {
		UserRequestDTO userRequestDTO = new UserRequestDTO("Maksim", "Fomov", "Aleksandrovich", "maksimfomov26@gmail.com", "ROLE_ADMINISTRATOR");

		when(userFacade.addUser(userRequestDTO)).thenThrow(new UserAlreadyExistsException("User with this email already exists"));

		BindingResult bindingResult = mock(BindingResult.class);
		when(bindingResult.hasErrors()).thenReturn(false);

		ResponseEntity<?> responseEntity = userController.addUser(userRequestDTO, bindingResult);

		assertEquals(HttpStatus.CONFLICT, responseEntity.getStatusCode());
	}

	@Test
	public void testAddUser_InvalidUserRequestDTO() {
		UserRequestDTO invalidUserRequestDTO = new UserRequestDTO("Maksim", "", "Aleksandrovich", "maks@mail.ru", "ROLE_ADMINISTRATOR");

		BindingResult bindingResult = mock(BindingResult.class);
		when(bindingResult.hasErrors()).thenReturn(true);

		ResponseEntity<?> responseEntity = userController.addUser(invalidUserRequestDTO, bindingResult);

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
	}

	@Test
	public void testGetAllUsers_EmptyList() {
		int page = 0;
		int pageSize = 10;

		Pageable pageable = PageRequest.of(page, pageSize, Sort.by("email").ascending());
		when(userFacade.getAllUsers(pageable)).thenThrow(new UserNotFoundException("Users not found for page " + pageable.getPageNumber()));

		ResponseEntity<?> responseEntity = userController.getAllUsers(page);

		assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());}
}

