package com.mehrdad.falahati.user.service.application.rest;

import com.mehrdad.falahati.common.domain.dto.user.UserDto;
import com.mehrdad.falahati.user.service.application.UserAuthenticationHelper;
import com.mehrdad.falahati.user.service.application.rest.dto.LoginRequest;
import com.mehrdad.falahati.user.service.application.rest.dto.LoginResponse;
import com.mehrdad.falahati.user.service.domain.dto.user.CreateUserCommand;
import com.mehrdad.falahati.user.service.domain.dto.user.CreateUserResponse;
import com.mehrdad.falahati.user.service.domain.port.input.service.UserApplicationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserApplicationService userApplicationService;
    private final PasswordEncoder passwordEncoder;
    private final UserAuthenticationHelper userAuthenticationHelper;

    @PostMapping
    public ResponseEntity<CreateUserResponse> register(@RequestBody CreateUserCommand createUserCommand) {
        log.info("Creating user with username = {}", createUserCommand.getUsername());
        createUserCommand.setPassword(passwordEncoder.encode(createUserCommand.getPassword()));
        CreateUserResponse createUserResponse = userApplicationService.createUser(createUserCommand);
        return ResponseEntity.status(HttpStatus.CREATED).body(createUserResponse);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
        userAuthenticationHelper.authenticate(loginRequest.username(), loginRequest.password());

        UserDto user = userApplicationService.getUserByUsername(loginRequest.username());
        return ResponseEntity.ok(
                LoginResponse.builder()
                        .userId(user.id())
                        .token(userAuthenticationHelper.generateToken(user))
                        .build());
    }

}
