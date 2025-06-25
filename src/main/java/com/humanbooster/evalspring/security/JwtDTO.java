package com.humanbooster.evalspring.security;

public record JwtDTO(String token) {
    public String getToken() {
            return token;
        }
}