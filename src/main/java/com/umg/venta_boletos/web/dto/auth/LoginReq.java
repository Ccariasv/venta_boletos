package com.umg.venta_boletos.web.dto.auth;

import jakarta.validation.constraints.*;

public record LoginReq(
        @NotBlank String username,
        @NotBlank String password
) {}
