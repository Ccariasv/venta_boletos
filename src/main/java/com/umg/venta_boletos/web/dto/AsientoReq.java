package com.umg.venta_boletos.web.dto;

import jakarta.validation.constraints.*;

public record AsientoReq(
        @NotNull Long avionId,
        @NotBlank @Size(max=10) String numeroAsiento,
        @NotNull Long claseId
) {}