package com.nikitalipatov.handmadeshop.core.models;

public enum Permission {
    AUTH_USER("authUser"),
    ADMIN("admin");

    private final String permission;

    Permission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}