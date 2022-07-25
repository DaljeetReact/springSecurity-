package com.singh.blogapp.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import static com.singh.blogapp.Security.ApplicationUserPermission.POST_READ;
import static com.singh.blogapp.Security.ApplicationUserPermission.POST_WRITE;
import  static  com.singh.blogapp.Security.ApplicationUserRole.*;

@Configuration //this notation will automatically configure the class
@EnableWebSecurity // to enable the web security
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class AppSecurity extends WebSecurityConfigurerAdapter {  // exten class to override method from websecurity


    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AppSecurity(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    @Bean
    protected UserDetailsService userDetailsService() {
        UserDetails user = User.builder()
                .username("singh")
                .password(passwordEncoder.encode("singh"))
//                .roles(USER.name()) //remove role base permissions
                .authorities(USER.getGrantedAuthorities()) //adding Authorities to //Role_User
                .build();

        UserDetails user1 = User.builder()
                .username("daljeet")
                .password(passwordEncoder.encode("singh"))
//                .roles(ADMIN.name())
                .authorities(ADMIN.getGrantedAuthorities()) //adding Authorities to //Role_ADMIN
                .build();

        UserDetails user2 = User.builder()
                .username("gurjeet")
                .password(passwordEncoder.encode("singh"))
//                .roles(ADMINTRANEE.name())
                .authorities(ADMINTRANEE.getGrantedAuthorities()) //adding Authorities to //Role_ADMINTRANEE
                .build();


        return new InMemoryUserDetailsManager(
                user,user1,user2
        );
    }

    // TO SEE the list of method to be overide just clik  ( CRTL + O )
    @Override
    protected void configure(HttpSecurity http) throws Exception { // from this method we will override the http requests
        http.csrf().disable().
                authorizeRequests()
                .antMatchers("/","index","/css/*","/js/*")// give the match you want to permit
                .permitAll()// to permit all the requestes
                .antMatchers("/api/**").hasRole(USER.name())
//                .antMatchers(HttpMethod.DELETE,"/management/api/**").hasAnyAuthority(POST_WRITE.getPermission())
//                .antMatchers(HttpMethod.POST,"/management/api/**").hasAnyAuthority(POST_WRITE.getPermission())
//                .antMatchers(HttpMethod.PUT,"/management/api/**").hasAnyAuthority(POST_WRITE.getPermission())
//                .antMatchers(HttpMethod.GET,"/management/api/**").hasAnyRole(ADMIN.name(),ADMINTRANEE.name())
//                // now only admin can access the whole users
                .anyRequest()
                .authenticated()
                .and()
                .formLogin() // enable basic authentication
                .loginPage("/login").permitAll(); // custom login page


    }


}
