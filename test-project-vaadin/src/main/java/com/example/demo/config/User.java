package com.example.demo.config;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class User {
    private String login;
    private String password;
}
