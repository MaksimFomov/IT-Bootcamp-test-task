package com.fomov.itbootcamptesttask.core.facade;

import com.fomov.itbootcamptesttask.core.dto.UserRequestDTO;
import com.fomov.itbootcamptesttask.core.dto.UserResponseDTO;

import java.util.List;

public interface UserFacade {
	UserResponseDTO addUser(UserRequestDTO userRequestDTO);
	List<UserResponseDTO> getAllUsers();
}
