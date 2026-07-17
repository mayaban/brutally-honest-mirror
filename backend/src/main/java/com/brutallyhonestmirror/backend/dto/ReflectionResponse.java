package com.brutallyhonestmirror.backend.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;


@Getter
public class ReflectionResponse {

    private  Long id;
    private String rawText;
    private String aiResponse;
    private Instant createdAt;

    public ReflectionResponse(Long id, String rawText, String aiResponse, Instant createdAt) {
        this.id = id;
        this.rawText = rawText;
        this.aiResponse = aiResponse;
        this.createdAt = createdAt;
    }
}
