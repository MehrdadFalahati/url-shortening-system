package com.mehrdad.falahati.common.security.entity;

import com.mehrdad.falahati.common.domain.dto.user.UserDto;
import com.mehrdad.falahati.common.domain.entity.Role;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

public class UserSecurityEntity implements UserDetails {

    private final String username;
    private final String password;
    private final boolean isEnabled;
    private final Collection<? extends GrantedAuthority> authorities;

    public UserSecurityEntity(UserDto user) {
        this.username = user.username();
        this.password = user.password();
        this.isEnabled = user.isEnable();
        this.authorities = getAuthorities(Set.of(user.role()));
    }

    public Set<SimpleGrantedAuthority> getAuthorities(Set<Role> roles) {
        return roles.stream().flatMap(r -> getAuthority(r).stream())
                .collect(Collectors.toSet());
    }

    public Set<SimpleGrantedAuthority> getAuthority(Role role) {
        Set<SimpleGrantedAuthority> authorities = role.getPermissions()
                .stream()
                .map(p -> new SimpleGrantedAuthority(p.getPermissionName()))
                .collect(Collectors.toSet());
        authorities.add(new SimpleGrantedAuthority("ROLE_" + role.name()));
        return authorities;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return isEnabled;
    }
}
