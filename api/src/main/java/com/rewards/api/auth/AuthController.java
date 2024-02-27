package com.rewards.api.auth;

import com.rewards.api.User.User;
import com.rewards.api.User.UserService;
import com.rewards.api.auth.dto.JwtRequest;
import com.rewards.api.auth.dto.JwtResponse;
import com.rewards.api.auth.dto.UserDetailsDto;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@Controller
@RestController
public class AuthController {
    @Autowired
    private AuthService authService;

    @Autowired
    private JwtHelper helper;

    @Autowired
    private UserService userService;


    @GetMapping("/hello")
    public String hellowrold() {
        return "Helllo there";
    }

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(@RequestBody JwtRequest request) {
        try {
        User userDetails = userService.findByClerkId(request.getUserId());
        String token = this.helper.generateToken(userDetails);
        JwtResponse jwtResponse = new JwtResponse(userDetails.getUserId(), token);
        return new ResponseEntity<>(jwtResponse, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @ExceptionHandler(BadCredentialsException.class)
    public String exceptionHandler() {
        return "Credentials Invalid !!";
    }

}
