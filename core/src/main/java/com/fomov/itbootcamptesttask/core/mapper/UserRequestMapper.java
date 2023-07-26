package com.fomov.itbootcamptesttask.core.mapper;

import com.fomov.itbootcamptesttask.core.dto.UserRequestDTO;
import com.fomov.itbootcamptesttask.data.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserRequestMapper {
	User toUser(UserRequestDTO userRequestDTO);
}
