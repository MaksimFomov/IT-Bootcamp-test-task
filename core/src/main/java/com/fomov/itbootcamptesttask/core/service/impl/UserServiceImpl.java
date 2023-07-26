package com.fomov.itbootcamptesttask.core.service.impl;

import com.fomov.itbootcamptesttask.core.service.UserService;
import com.fomov.itbootcamptesttask.data.model.User;
import com.fomov.itbootcamptesttask.data.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
	private final UserRepository userRepository;

	@Override
	@Transactional
	public User addUser(User user) {
		return userRepository.save(user);
	}

	@Override
	public List<User> getAllUsers() {
		return userRepository.findAllByOrderByEmailAsc();
	}
}
