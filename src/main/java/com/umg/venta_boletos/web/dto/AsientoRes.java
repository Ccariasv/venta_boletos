package com.umg.venta_boletos.web.dto;

public record AsientoRes(
        Long id,
        Long avionId, String avionMatricula,
        String numeroAsiento,
        Long claseId, String claseNombre
) {}