package com.mehrdad.falahati.url.shortener.service.domain.mapper;

import com.mehrdad.falahati.common.domain.dto.user.UserDto;
import com.mehrdad.falahati.url.shortener.service.domain.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserDataMapper {

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
