package com.example.skillmatcherbackend.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class JWTResponse {
    private String id;
    private String accessToken;
    private String refreshToken;
    private String expiresAt;
}