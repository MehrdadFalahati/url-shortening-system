package com.mehrdad.falahati.common.domain.port.input.service;

import com.mehrdad.falahati.common.domain.dto.user.UserDto;

public interface UserSecurityApplicationService {
    UserDto getUserByUsername(String username);
}
