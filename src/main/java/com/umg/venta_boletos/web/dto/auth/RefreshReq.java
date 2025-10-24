package com.umg.venta_boletos.web.dto.auth;

import jakarta.validation.constraints.NotBlank;

public record RefreshReq(@NotBlank String refreshToken) {}
