package com.fomov.itbootcamptesttask.core.service.impl;

import com.fomov.itbootcamptesttask.core.exception.UserAlreadyExistsException;
import com.fomov.itbootcamptesttask.core.exception.UserNotFoundException;
import com.fomov.itbootcamptesttask.core.service.UserService;
import com.fomov.itbootcamptesttask.data.model.User;
import com.fomov.itbootcamptesttask.data.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
	private final UserRepository userRepository;

	@Override
	@Transactional
	public User addUser(User user) {
		if (userRepository.existsByEmail(user.getEmail())) {
			throw new UserAlreadyExistsException("User with this email already exists");
		}

		return userRepository.save(user);
	}

	@Override
	public Page<User> getAllUsers(Pageable pageable) {
		Page<User> usersPage = userRepository.findAll(pageable);

		if (usersPage.isEmpty()) {
			throw new UserNotFoundException("Users not found for page " + pageable.getPageNumber());
		}

		return usersPage;
	}
}
