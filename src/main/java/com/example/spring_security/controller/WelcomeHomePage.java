package com.example.spring_security.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
public class WelcomeHomePage {
    

@GetMapping("/welcome")
public String getMethodName() {
    return "Welcome";
}

}
