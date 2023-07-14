package com.mehrdad.falahati.user.service.application.rest;

import com.mehrdad.falahati.user.service.application.rest.dto.LoginRequest;
import com.mehrdad.falahati.user.service.application.rest.dto.LoginResponse;
import com.mehrdad.falahati.user.service.domain.dto.user.CreateUserCommand;
import com.mehrdad.falahati.user.service.domain.dto.user.CreateUserResponse;
import com.mehrdad.falahati.user.service.domain.dto.user.UserDto;
import com.mehrdad.falahati.user.service.domain.entity.User;
import com.mehrdad.falahati.user.service.domain.exception.UserDomainException;
import com.mehrdad.falahati.user.service.domain.port.input.service.UserApplicationService;
import com.mehrdad.falahati.user.service.security.util.JwtTokenUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserApplicationService userApplicationService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenUtil tokenUtil;

    @PostMapping
    public ResponseEntity<CreateUserResponse> register(@RequestBody CreateUserCommand createUserCommand) {
        log.info("Creating user with username = {}", createUserCommand.getUsername());
        createUserCommand.setPassword(passwordEncoder.encode(createUserCommand.getPassword()));
        CreateUserResponse createUserResponse = userApplicationService.createUser(createUserCommand);
        return ResponseEntity.status(HttpStatus.CREATED).body(createUserResponse);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
        authenticate(loginRequest.username(), loginRequest.password());

        UserDto result = userApplicationService.getUserByUsername(loginRequest.username());
        return ResponseEntity.ok(
                LoginResponse.builder()
                        .userId(result.id())
                        .token(tokenUtil.generateAccessToken(result))
                        .build());
    }

    private void authenticate(String username, String password) {
        try {
            authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new UserDomainException("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new UserDomainException("INVALID_CREDENTIALS", e);
        }
    }
}
