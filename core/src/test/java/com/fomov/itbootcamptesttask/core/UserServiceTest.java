package com.fomov.itbootcamptesttask.core;

import com.fomov.itbootcamptesttask.core.dto.UserRequestDTO;
import com.fomov.itbootcamptesttask.core.exception.UserAlreadyExistsException;
import com.fomov.itbootcamptesttask.core.service.impl.UserServiceImpl;
import com.fomov.itbootcamptesttask.data.enums.Role;
import com.fomov.itbootcamptesttask.data.model.User;
import com.fomov.itbootcamptesttask.data.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

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
}

