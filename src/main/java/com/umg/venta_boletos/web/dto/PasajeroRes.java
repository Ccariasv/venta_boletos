package com.umg.venta_boletos.web.dto;

public record PasajeroRes(
        Long id, String nombres, String apellidos, String email, String telefono, String documentoIdentidad
) {}