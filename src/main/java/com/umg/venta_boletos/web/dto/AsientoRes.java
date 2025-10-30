package com.umg.venta_boletos.web.dto;

public record AsientoRes(
        Long id,
        Long avionId,
        String numeroAsiento,
        Long claseId
) {}