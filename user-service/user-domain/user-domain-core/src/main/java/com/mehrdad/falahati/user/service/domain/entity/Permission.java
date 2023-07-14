package com.mehrdad.falahati.user.service.domain.entity;

public enum Permission {
    URL_WRITE("url:write");

    private final String permissionName;

    Permission(String permissionName) {
        this.permissionName = permissionName;
    }

    public String getPermissionName() {
        return permissionName;
    }
}
