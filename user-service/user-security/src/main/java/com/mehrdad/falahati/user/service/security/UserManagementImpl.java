package com.mehrdad.falahati.user.service.security;

import com.mehrdad.falahati.common.domain.dto.user.UserDto;
import com.mehrdad.falahati.common.security.UserManagement;
import com.mehrdad.falahati.common.security.entity.UserSecurityEntity;
import com.mehrdad.falahati.user.service.domain.port.input.service.UserApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserManagementImpl implements UserManagement {

    private final UserApplicationService userApplicationService;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDto user = userApplicationService.getUserByUsername(username);
        return new UserSecurityEntity(user);
    }
}
