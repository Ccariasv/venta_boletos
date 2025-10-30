package com.umg.venta_boletos.web.auth;

public record TokenResponse(
        String accessToken,
        String refreshToken,
        String tokenType
) {}
