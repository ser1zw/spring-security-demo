package com.example.demo.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/api/loginstatus")
public class LoginCheckController {
    @GetMapping
    public Map<String, Boolean> check(Principal principal) {
        return Map.of("isLoggedIn", (principal != null));
    }
}
