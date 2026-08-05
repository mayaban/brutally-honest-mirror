package com.brutallyhonestmirror.backend.dto;

import lombok.Getter;

@Getter
public class LoginResponse {

    private final String message;
    private final String email;
    private final String token;

    public LoginResponse(String message, String email, String token) {
        this.message = message;
        this.email = email;
        this.token = token;
    }
}
