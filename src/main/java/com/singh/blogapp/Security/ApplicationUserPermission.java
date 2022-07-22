package com.singh.blogapp.Security;

public enum ApplicationUserPermission {

    // all permissions are defined here
    USER_READ("user:read"),
    USER_WRITE("user:write"),
    POST_READ("post:read"),
    POST_WRITE("post:write");

    private final  String permission;

    ApplicationUserPermission(String permission) { //contractor
        this.permission = permission;
    }

    public String getPermission() { //getter
        return permission;
    }
}
