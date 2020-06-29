package com.example.toDo.entity;

public enum ERole {
    ROLE_USER("ROLE_USER"),
    ROLE_ADMIN("ROLE_ADMIN");

    private String roleStr;

    ERole(String roleStr) {
        this.roleStr = roleStr;
    }

    public String getRoleStr() {
        return roleStr;
    }
}
