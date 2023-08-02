package com.example.demo.security;

import com.example.demo.config.*;
import com.example.demo.view.LoginView;
import com.vaadin.flow.spring.security.VaadinWebSecurity;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@EnableWebSecurity
@Configuration
@RequiredArgsConstructor
public class SecurityConfig extends VaadinWebSecurity {

    private final UsersList users;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        super.configure(http);
        setLoginView(http, LoginView.class);
    }

    @Bean
    public UserDetailsService users() {
        String adminLogin = users.getUsers().get(0).getLogin();
        String adminPassword = users.getUsers().get(0).getPassword();

        String userLogin = users.getUsers().get(1).getLogin();
        String userPassword = users.getUsers().get(1).getLogin();

        UserDetails user = User.builder()
                .username(userLogin)
                .password("{noop}" + userPassword)
                .roles("USER")
                .build();
        UserDetails admin = User.builder()
                .username(adminLogin)
                .password("{noop}" + adminPassword)
                .roles("USER", "ADMIN")
                .build();
        return new InMemoryUserDetailsManager(user, admin);
    }
}