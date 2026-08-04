package com.brutallyhonestmirror.backend.controller;

import com.brutallyhonestmirror.backend.dto.RegisterRequest;
import com.brutallyhonestmirror.backend.dto.RegisterResponse;
import com.brutallyhonestmirror.backend.service.AuthService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService service;

    public AuthController(AuthService service) {
        this.service = service;
    }

    @PostMapping("/register")
    public RegisterResponse register(@RequestBody RegisterRequest request){
        String token = service.register(request);
        return new RegisterResponse(
                "Welcome, " + request.getEmail() + "! Your account has been created.",
                request.getEmail(),
                token
        );
    }
}
