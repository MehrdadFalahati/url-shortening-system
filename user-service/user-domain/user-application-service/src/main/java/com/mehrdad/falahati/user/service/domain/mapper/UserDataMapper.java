package com.mehrdad.falahati.user.service.domain.mapper;

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
}
