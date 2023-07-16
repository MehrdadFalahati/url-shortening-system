package com.mehrdad.falahati.common.domain.entity;

public enum Permission {
    URL_WRITE("url:write"),
    TRACKING_READ("tracking:read");

    private final String permissionName;

    Permission(String permissionName) {
        this.permissionName = permissionName;
    }

    public String getPermissionName() {
        return permissionName;
    }
}
