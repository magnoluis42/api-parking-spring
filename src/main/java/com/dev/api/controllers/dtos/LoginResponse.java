package com.dev.api.controllers.dtos;

public record LoginResponse(String accessToken, Long expiresIn, String sub, String scope) {
}
