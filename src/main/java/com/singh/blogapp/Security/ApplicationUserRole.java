package com.singh.blogapp.Security;

import com.google.common.collect.Sets;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

import static com.singh.blogapp.Security.ApplicationUserPermission.*;

public enum ApplicationUserRole {
   //Sets.newHashSet() help to set the permission base on role
   USER(Sets.newHashSet()),
   ADMIN(Sets.newHashSet(POST_READ,POST_WRITE,USER_READ,USER_WRITE)), // Imported from applicationUserPersmission
   ADMINTRANEE(Sets.newHashSet(POST_READ,USER_READ)); // Imported from applicationUserPersmission


   ApplicationUserRole(Set<ApplicationUserPermission> permissions) {
      this.permissions = permissions;
   }


   private final Set<ApplicationUserPermission> permissions;

   public  Set<ApplicationUserPermission> getPermissions(){
      return  permissions;
   }

   public Set<SimpleGrantedAuthority> getGrantedAuthorities(){
        Set<SimpleGrantedAuthority> Authority = getPermissions().stream()
              .map(permissions->new SimpleGrantedAuthority(permissions.getPermission()))
              .collect(Collectors.toSet());
        Authority.add(new SimpleGrantedAuthority("ROLE_"+this.name()));

        return Authority;
   }

}
