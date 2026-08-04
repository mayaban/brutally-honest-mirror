package com.brutallyhonestmirror.backend.dto;

import lombok.Getter;

@Getter
public class RegisterResponse {

    private String message;
    private String email;
    private String token;

    public RegisterResponse(String message, String email, String token) {
        this.message = message;
        this.email = email;
        this.token = token;
    }
}
