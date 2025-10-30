package com.umg.venta_boletos.web.dto;

import jakarta.validation.constraints.*;

public record AsientoReq(
        Long avionId,
        String numeroAsiento,
        Long claseId
) {}