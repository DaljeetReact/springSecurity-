package com.singh.blogapp.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration //this notation will automatically configure the class
@EnableWebSecurity // to enable the web security
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
                .roles("ADMIN")
                .build();

        return new InMemoryUserDetailsManager(
                user
        );
    }

    // TO SEE the list of method to be overide just clik  ( CRTL + O )
    @Override
    protected void configure(HttpSecurity http) throws Exception { // from this method we will override the http requests
        http.authorizeRequests()
                .antMatchers("/","index","/css/*","/js/*")// give the match you want to permit
                .permitAll()// to permit all the requestes
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic();
    }


}
