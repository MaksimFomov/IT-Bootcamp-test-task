package com.fomov.itbootcamptesttask.core.mapper;

import com.fomov.itbootcamptesttask.core.dto.UserResponseDTO;
import com.fomov.itbootcamptesttask.data.enums.Role;
import com.fomov.itbootcamptesttask.data.model.User;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserResponseMapper {
	@Mapping(target = "fullName", expression = "java(user.getFirstName() + \" \" + user.getLastName() + \" \" + user.getMiddleName())")
	@Mapping(source = "role", target = "role", qualifiedByName = "mapRole")
	UserResponseDTO toUserResponseDTO(User user);

	@IterableMapping(elementTargetType = UserResponseDTO.class)
	List<UserResponseDTO> toUserResponseDTOs(List<User> users);

	@Named("mapRole")
	default String mapRole(Role role) {
		return role.toString();
	}
}
