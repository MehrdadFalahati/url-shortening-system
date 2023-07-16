package com.mehrdad.falahati.url.shortener.service.dataaccess.mapper;

import com.mehrdad.falahati.common.domain.valueobject.UserId;
import com.mehrdad.falahati.url.shortener.service.dataaccess.entity.UserEntity;
import com.mehrdad.falahati.url.shortener.service.domain.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserDataAccessMapper {

    public User entityToUser(UserEntity userEntity) {
        return User.builder()
                .id(new UserId(userEntity.getId()))
                .username(userEntity.getUsername())
                .password(userEntity.getPassword())
                .isEnabled(userEntity.getIsEnabled())
                .role(userEntity.getRole())
                .build();
    }
}
