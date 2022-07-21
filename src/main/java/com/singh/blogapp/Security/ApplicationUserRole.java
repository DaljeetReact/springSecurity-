package com.singh.blogapp.Security;

import com.google.common.collect.Sets;

import java.util.Set;

import static com.singh.blogapp.Security.ApplicationUserPermission.*;

public enum ApplicationUserRole {
   //Sets.newHashSet() help to set the permission base on role
   STUDENT(Sets.newHashSet()),
   ADMIN(Sets.newHashSet(COURSE_READ,COURSE_WRITE,STUDENT_READ,STUDENT_WRITE)); // Imported from applicationUserPersmission

   private final Set<ApplicationUserPermission> permissions;

   ApplicationUserRole(Set<ApplicationUserPermission> permissions) {
      this.permissions = permissions;
   }
}
