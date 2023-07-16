package com.mehrdad.falahati.url.shortener.service.domain;

import com.mehrdad.falahati.common.domain.dto.user.UserDto;
import com.mehrdad.falahati.common.domain.exception.NotFoundException;
import com.mehrdad.falahati.common.domain.port.input.service.UserSecurityApplicationService;
import com.mehrdad.falahati.url.shortener.service.domain.mapper.UserDataMapper;
import com.mehrdad.falahati.url.shortener.service.domain.port.output.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserSecurityApplicationServiceImpl implements UserSecurityApplicationService {

    private final UserRepository userRepository;
    private final UserDataMapper userDataMapper;
    @Override
    @Transactional(readOnly = true)
    public UserDto getUserByUsername(String username) {
        return userRepository.findByUsername(username)
                .map(userDataMapper::userToUserDto)
                .orElseThrow(() -> new NotFoundException("User[username=" + username +"] not found!"));
    }
}
