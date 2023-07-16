package com.mehrdad.falahati.url.shortener.service.security;

import com.mehrdad.falahati.common.domain.dto.user.UserDto;
import com.mehrdad.falahati.common.domain.port.input.service.UserSecurityApplicationService;
import com.mehrdad.falahati.common.security.UserManagement;
import com.mehrdad.falahati.common.security.entity.UserNamesPrincipal;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserManagementImpl implements UserManagement {

    private final UserSecurityApplicationService userApplicationService;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDto user = userApplicationService.getUserByUsername(username);
        return new UserNamesPrincipal(user);
    }
}
