package com.mehrdad.falahati.user.service.dataaccess.mapper;

import com.mehrdad.falahati.common.domain.valueobject.UserId;
import com.mehrdad.falahati.user.service.dataaccess.entity.UserEntity;
import com.mehrdad.falahati.user.service.domain.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserDataAccessMapper {


    public UserEntity userToEntity(User user) {
        return UserEntity.builder()
                .id(user.getId().getValue())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .username(user.getUsername())
                .password(user.getPassword())
                .phoneNumber(user.getPhoneNumber())
                .role(user.getRole())
                .isEnabled(user.getEnabled())
                .build();
    }

    public User entityToUser(UserEntity userEntity) {
        return User.builder()
                .id(new UserId(userEntity.getId()))
                .username(userEntity.getUsername())
                .password(userEntity.getPassword())
                .firstName(userEntity.getFirstName())
                .lastName(userEntity.getLastName())
                .phoneNumber(userEntity.getPhoneNumber())
                .isEnabled(userEntity.getIsEnabled())
                .role(userEntity.getRole())
                .build();
    }
}
