package com.mehrdad.falahati.user.service.security;

import com.mehrdad.falahati.user.service.domain.dto.user.UserDto;
import com.mehrdad.falahati.user.service.domain.port.input.service.UserApplicationService;
import com.mehrdad.falahati.user.service.security.entity.UserSecurityEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserManagement implements UserDetailsService {

    private final UserApplicationService userApplicationService;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDto user = userApplicationService.getUserByUsername(username);
        return new UserSecurityEntity(user);
    }
}
