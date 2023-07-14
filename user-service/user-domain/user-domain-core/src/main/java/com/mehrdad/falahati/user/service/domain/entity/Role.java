package com.mehrdad.falahati.user.service.domain.entity;


import java.util.Set;

import static com.mehrdad.falahati.user.service.domain.entity.Permission.*;


public enum Role {
    USER(Set.of(URL_WRITE));

    private final Set<Permission> permissions;

    Role(Set<Permission> permissions) {
        this.permissions = permissions;
    }

    public Set<Permission> getPermissions() {
        return permissions;
    }
}
