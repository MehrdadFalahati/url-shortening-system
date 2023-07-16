package com.mehrdad.falahati.user.service.domain.mapper;

import com.mehrdad.falahati.common.domain.dto.user.UserDto;
import com.mehrdad.falahati.user.service.domain.dto.user.CreateUserCommand;
import com.mehrdad.falahati.user.service.domain.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserDataMapper {

    public User createUserCommandToUser(CreateUserCommand createUserCommand) {
        return User.builder()
                .firstName(createUserCommand.getFirstName())
                .lastName(createUserCommand.getLastName())
                .username(createUserCommand.getUsername())
                .password(createUserCommand.getPassword())
                .phoneNumber(createUserCommand.getPhoneNumber())
                .build();
    }

    public UserDto userToUserDto(User user) {
        return UserDto.builder()
                .id(user.getId().getValue().toString())
                .username(user.getUsername())
                .password(user.getPassword())
                .role(user.getRole())
                .isEnable(user.getEnabled())
                .build();
    }
}
