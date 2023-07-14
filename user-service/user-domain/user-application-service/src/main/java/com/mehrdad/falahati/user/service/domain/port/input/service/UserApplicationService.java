package com.mehrdad.falahati.user.service.domain.port.input.service;

import com.mehrdad.falahati.user.service.domain.dto.user.CreateUserCommand;
import com.mehrdad.falahati.user.service.domain.dto.user.CreateUserResponse;
import com.mehrdad.falahati.user.service.domain.dto.user.UserDto;
import jakarta.validation.Valid;


public interface UserApplicationService {
    CreateUserResponse createUser(@Valid CreateUserCommand createUserCommand);
    UserDto getUserByUsername(String username);
}
