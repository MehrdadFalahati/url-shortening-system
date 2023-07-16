package com.mehrdad.falahati.user.service.domain.port.input.service;

import com.mehrdad.falahati.common.domain.port.input.service.UserSecurityApplicationService;
import com.mehrdad.falahati.user.service.domain.dto.user.CreateUserCommand;
import com.mehrdad.falahati.user.service.domain.dto.user.CreateUserResponse;
import jakarta.validation.Valid;


public interface UserApplicationService extends UserSecurityApplicationService {
    CreateUserResponse createUser(@Valid CreateUserCommand createUserCommand);
}
