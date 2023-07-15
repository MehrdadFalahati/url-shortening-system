package com.mehrdad.falahati.user.service.application;

import com.mehrdad.falahati.user.service.domain.dto.user.UserDto;
import com.mehrdad.falahati.user.service.domain.exception.UserDomainException;
import com.mehrdad.falahati.user.service.security.util.JwtTokenUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserAuthenticationHelper {
    private final AuthenticationManager authenticationManager;
    private final JwtTokenUtil tokenUtil;

    public void authenticate(String username, String password) {
        try {
            authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new UserDomainException("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new UserDomainException("INVALID_CREDENTIALS", e);
        }
    }

    public String generateToken(UserDto user) {
        return tokenUtil.generateAccessToken(user);
    }
}
