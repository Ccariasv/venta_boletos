package com.umg.venta_boletos.security;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "security.jwt")
public record JwtProperties(
        String secret,
        int expirationMinutes,       // access token (minutos)
        int refreshExpirationDays    // refresh token (días)
) {
    public long accessTtlSeconds() { return expirationMinutes * 60L; }
    public long refreshTtlSeconds() { return refreshExpirationDays * 24L * 3600L; }
}
