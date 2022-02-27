package com.orphan.api.user.mapper;

import com.orphan.api.user.dto.UserDto;
import com.orphan.common.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {

    UserMapper INSTANCE= Mappers.getMapper(UserMapper.class);

    @Mapping(target = "roles", source = "roles")
    UserDto userToUserDto(User user);

    @Mapping(target = "roles", source = "roles")
    User userDtoToUser(UserDto userDto);
}
