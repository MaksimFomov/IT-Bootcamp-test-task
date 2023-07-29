package com.fomov.itbootcamptesttask.core;

import com.fomov.itbootcamptesttask.core.exception.UserAlreadyExistsException;
import com.fomov.itbootcamptesttask.core.exception.UserNotFoundException;
import com.fomov.itbootcamptesttask.core.service.impl.UserServiceImpl;
import com.fomov.itbootcamptesttask.data.enums.Role;
import com.fomov.itbootcamptesttask.data.model.User;
import com.fomov.itbootcamptesttask.data.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {
	@InjectMocks
	private UserServiceImpl userService;

	@Mock
	private UserRepository userRepository;

	@Test
	public void testAddUser_Success() {
		User user = new User(1L, "Maksim", "Fomov", "Aleksandrovich", "maksimfomov26@gmail.com", Role.ROLE_ADMINISTRATOR);

		when(userRepository.existsByEmail(user.getEmail())).thenReturn(false);
		when(userRepository.save(user)).thenReturn(user);

		User result = userService.addUser(user);

		assertEquals(user, result);
	}

	@Test(expected = UserAlreadyExistsException.class)
	public void testAddUser_UserAlreadyExistsException() {
		User user = new User(1L, null, "Fomov", "Aleksandrovich", "maksimfomov26@gmail.com", Role.ROLE_ADMINISTRATOR);

		when(userRepository.existsByEmail(user.getEmail())).thenReturn(true);

		userService.addUser(user);
	}

	@Test
	public void testGetAllUsers_Success() {
		int page = 0;
		int pageSize = 10;

		List<User> users = new ArrayList<>();
		users.add(new User(1L, "Maksim", "Fomov", "Aleksandrovich", "maksimfomov26@gmail.com", Role.ROLE_ADMINISTRATOR));
		users.add(new User(2L, "Ivan", "Ivanov", "Petrovich", "ivanov@gmail.com", Role.ROLE_CUSTOMER_USER));

		Pageable pageable = PageRequest.of(page, pageSize);
		Page<User> usersPage = new PageImpl<>(users, pageable, users.size());

		when(userRepository.findAll(pageable)).thenReturn(usersPage);

		Page<User> result = userService.getAllUsers(pageable);

		assertEquals(usersPage, result);
	}

	@Test(expected = UserNotFoundException.class)
	public void testGetAllUsers_UserNotFoundException() {
		int page = 0;
		int pageSize = 10;

		Pageable pageable = PageRequest.of(page, pageSize);

		when(userRepository.findAll(pageable)).thenReturn(Page.empty());

		userService.getAllUsers(pageable);
	}
}

