package com.mehrdad.falahati.url.shortener.service.dataaccess.adapter;

import com.mehrdad.falahati.common.domain.valueobject.UserId;
import com.mehrdad.falahati.url.shortener.service.dataaccess.mapper.UserDataAccessMapper;
import com.mehrdad.falahati.url.shortener.service.dataaccess.repository.UserJpaRepository;
import com.mehrdad.falahati.url.shortener.service.domain.entity.User;
import com.mehrdad.falahati.url.shortener.service.domain.port.output.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {

    private final UserJpaRepository userJpaRepository;
    private final UserDataAccessMapper userDataAccessMapper;

    @Override
    public Optional<User> findById(UserId userId) {
        return userJpaRepository.findById(userId.getValue())
                .map(userDataAccessMapper::entityToUser);
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return userJpaRepository.findByUsername(username)
                .map(userDataAccessMapper::entityToUser);
    }
}
