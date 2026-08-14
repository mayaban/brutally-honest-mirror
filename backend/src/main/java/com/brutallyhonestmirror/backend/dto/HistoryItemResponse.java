package com.brutallyhonestmirror.backend.dto;

import lombok.Getter;

import java.time.Instant;

@Getter
public class HistoryItemResponse {

    private final Long id;
    private final String rawText;
    private final String aiResponse;
    private final Instant createdAt;

    public HistoryItemResponse(Long id, String rawText, String aiResponse, Instant createdAt) {
        this.id = id;
        this.rawText = rawText;
        this.aiResponse = aiResponse;
        this.createdAt = createdAt;
    }
}
