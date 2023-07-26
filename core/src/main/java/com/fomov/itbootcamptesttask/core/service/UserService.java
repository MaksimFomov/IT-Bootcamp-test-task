package com.fomov.itbootcamptesttask.core.service;

import com.fomov.itbootcamptesttask.data.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {
	User addUser(User user);
	Page<User> getAllUsers(Pageable pageable);
}
