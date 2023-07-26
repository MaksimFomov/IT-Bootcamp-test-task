package com.fomov.itbootcamptesttask.core.facade.impl;

import com.fomov.itbootcamptesttask.core.facade.UserFacade;
import com.fomov.itbootcamptesttask.core.mapper.UserRequestMapper;
import com.fomov.itbootcamptesttask.core.mapper.UserResponseMapper;
import com.fomov.itbootcamptesttask.core.service.UserService;
import com.fomov.itbootcamptesttask.core.dto.UserRequestDTO;
import com.fomov.itbootcamptesttask.core.dto.UserResponseDTO;
import com.fomov.itbootcamptesttask.data.model.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserFacadeImpl implements UserFacade {
	private final UserService userService;
	private final UserRequestMapper userRequestMapper;
	private final UserResponseMapper userResponseMapper;

	@Override
	public UserResponseDTO addUser(UserRequestDTO userRequestDTO) {
		User user = userService.addUser(userRequestMapper.toUser(userRequestDTO));
		return userResponseMapper.toUserResponseDTO(user);
	}

	@Override
	public List<UserResponseDTO> getAllUsers() {
		List<User> users = userService.getAllUsers();
		return userResponseMapper.toUserResponseDTOs(users);
	}
}
