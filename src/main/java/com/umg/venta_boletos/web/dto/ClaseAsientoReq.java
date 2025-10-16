package com.umg.venta_boletos.web.dto;

import jakarta.validation.constraints.*;

public record ClaseAsientoReq(
        @NotBlank @Size(max=50) String nombreClase,
        @Size(max=200) String descripcion
) {}