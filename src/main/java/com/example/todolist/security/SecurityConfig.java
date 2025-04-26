package com.example.todolist.security;

import com.example.todolist.entities.enums.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Lazy
    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserDetailsService userDetailsService() {
        UserDetails admin = User
                .withUsername("a")
                .password(passwordEncoder.encode("a"))
                .authorities(Role.ADMIN.name())
                .build();
        UserDetails user = User
                .withUsername("u")
                .password(passwordEncoder.encode("u"))
                .authorities(Role.USER.name())
                .build();
        return new InMemoryUserDetailsManager(admin, user);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity security) throws Exception {
        return security
                .authorizeHttpRequests(request ->
                        request
                                .requestMatchers("/",
                                        "/error",
                                        "/register",
                                        "/register-form",
                                        "/*.css")
                                .permitAll()
                                .requestMatchers("/todos")
                                .hasAnyAuthority(Role.USER.name(), Role.ADMIN.name())
                                .requestMatchers("/users")
                                .hasAuthority(Role.ADMIN.name())
                                .anyRequest()
                                .authenticated())
                .formLogin(formLogin ->
                        formLogin
                                .successForwardUrl("/")
                                .permitAll())
                .logout(logoutConfigurer ->
                        logoutConfigurer
                                .logoutSuccessUrl("/")
                                .permitAll())
                .build();
    }
}
