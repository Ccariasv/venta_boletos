package com.umg.venta_boletos.web.dto.auth;

public record TokenRes(
        String accessToken,
        String tokenType,
        long expiresInSeconds,
        String refreshToken
) {}
