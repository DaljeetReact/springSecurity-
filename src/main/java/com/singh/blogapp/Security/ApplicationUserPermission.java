package com.singh.blogapp.Security;

public enum ApplicationUserPermission {

    // all permissions are defined here
    STUDENT_READ("student:read"),
    STUDENT_WRITE("student:write"),
    COURSE_READ("course:read"),
    COURSE_WRITE("course:write");

    private final  String permission;

    ApplicationUserPermission(String permission) { //contractor
        this.permission = permission;
    }

    public String getPermission() { //getter
        return permission;
    }
}
