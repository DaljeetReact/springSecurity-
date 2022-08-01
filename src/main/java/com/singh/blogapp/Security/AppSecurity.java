package com.singh.blogapp.Security;

import com.singh.blogapp.auth.ApplicationUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.concurrent.TimeUnit;

import static com.singh.blogapp.Security.ApplicationUserRole.USER;

@Configuration //this notation will automatically configure the class
@EnableWebSecurity // to enable the web security
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class AppSecurity extends WebSecurityConfigurerAdapter {  // exten class to override method from websecurity


    private final PasswordEncoder passwordEncoder;
    private  final ApplicationUserService applicationUserServiceic;

    @Autowired
    public AppSecurity(PasswordEncoder passwordEncoder,ApplicationUserService applicationUserService) {
        this.passwordEncoder = passwordEncoder;
        this.applicationUserServiceic = applicationUserService;
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
                .loginPage("/login").permitAll() // custom login page
                .defaultSuccessUrl("/posts" ,true)
                .and()
                .rememberMe() //last for two week
                    .tokenValiditySeconds((int) TimeUnit.DAYS.toSeconds(21))
                    .key("mySecureKey")
                .and()
                .logout()
                    .logoutUrl("/logout")
                    .clearAuthentication(true)
                    .invalidateHttpSession(true)
                    .deleteCookies("JSESSIONID","remember-me")
                    .logoutSuccessUrl("/login");

    }

    @Override
    protected  void  configure(AuthenticationManagerBuilder auth) throws  Exception{
        auth.authenticationProvider(daoAuthenticationProvider());
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider(){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder);
        provider.setUserDetailsService(applicationUserServiceic);
        return provider;
    }


}
