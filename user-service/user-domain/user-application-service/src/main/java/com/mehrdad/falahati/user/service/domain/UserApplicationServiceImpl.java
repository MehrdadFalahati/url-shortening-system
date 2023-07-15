package com.mehrdad.falahati.user.service.domain;

import com.mehrdad.falahati.user.service.domain.dto.user.CreateUserCommand;
import com.mehrdad.falahati.user.service.domain.dto.user.CreateUserResponse;
import com.mehrdad.falahati.user.service.domain.dto.user.UserDto;
import com.mehrdad.falahati.user.service.domain.entity.User;
import com.mehrdad.falahati.common.domain.exception.NotFoundException;
import com.mehrdad.falahati.user.service.domain.exception.UserDomainException;
import com.mehrdad.falahati.user.service.domain.mapper.UserDataMapper;
import com.mehrdad.falahati.user.service.domain.port.input.service.UserApplicationService;
import com.mehrdad.falahati.user.service.domain.port.output.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

@Slf4j
@Service
@Validated
@RequiredArgsConstructor
public class UserApplicationServiceImpl implements UserApplicationService {

    private final UserDomainService userDomainService;
    private final UserDataMapper userDataMapper;
    private final UserRepository userRepository;

    @Override
    @Transactional
    public CreateUserResponse createUser(CreateUserCommand createUserCommand) {
        User user = userDataMapper.createUserCommandToUser(createUserCommand);
        userDomainService.initiateUser(user);
        User result = saveUser(user);
        return new CreateUserResponse(result.getId().getValue());
    }

    @Override
    @Transactional(readOnly = true)
    public UserDto getUserByUsername(String username) {
        return userRepository.findByUsername(username)
                .map(userDataMapper::userToUserDto)
                .orElseThrow(() -> new NotFoundException("User[username=" + username +"] not found!"));
    }

    private User saveUser(User user) {
        User result = userRepository.save(user);
        if (result == null) {
            log.error("Could not save user!");
            throw new UserDomainException("Could not save user!");
        }
        log.info("User[username={}] inserted to DB", result.getUsername());
        return result;
    }
}
