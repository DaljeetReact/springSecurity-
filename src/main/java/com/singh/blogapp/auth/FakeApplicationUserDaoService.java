package com.singh.blogapp.auth;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static com.singh.blogapp.Security.ApplicationUserRole.*;

@Repository("fake")
public class FakeApplicationUserDaoService implements ApplicationUserDao {
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public FakeApplicationUserDaoService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public Optional<ApplicationUser> selectApplicationUserByUsername(String username) {
        return getApplicationUsers()
                .stream().filter(applicationUser -> username.equals(applicationUser.getUsername()))
                .findFirst();
    }

    private List<ApplicationUser> getApplicationUsers(){
        List<ApplicationUser> applicationUsers = Lists.newArrayList(
             new ApplicationUser(
                     ADMIN.getGrantedAuthorities(),
                     passwordEncoder.encode("singh"),
                     "daljeet",true,true,true,true
             ),
            new ApplicationUser(
                    ADMINTRANEE.getGrantedAuthorities(),
                    passwordEncoder.encode("singh"),
                    "gurjeet",true,true,true,true
            ),
            new ApplicationUser(
                    USER.getGrantedAuthorities(),
                    passwordEncoder.encode("singh"),
                    "singh",true,true,true,true
            )

        );

        return  applicationUsers;

    }
}