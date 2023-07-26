package com.fomov.itbootcamptesttask.core.service;

import com.fomov.itbootcamptesttask.data.model.User;

import java.util.List;

public interface UserService {
	User addUser(User user);
	List<User> getAllUsers();
}
