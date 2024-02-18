package com.rewards.api.auth;

import com.rewards.api.auth.dto.UserDetailsDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

//@Controller
@RestController
public class AuthController {

    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @GetMapping("/hello")
    public String hellowrold() {
        return "Helllo there";
    }

    @GetMapping("/all-users")
    public List<UserDetailsDto> getAllUsers() {
        return this.authService.getListOfAllUsers();
    }
}
