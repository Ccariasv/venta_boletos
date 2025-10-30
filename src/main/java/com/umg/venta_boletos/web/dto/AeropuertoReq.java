package com.umg.venta_boletos.web.dto;

import jakarta.validation.constraints.*;

public record AeropuertoReq(
        @NotBlank @Size(max=100) String nombre,
        @Size(max=100) String ciudad,
        @Size(max=100) String pais,
        @NotBlank @Size(min=3, max=3) String codigoIata
) {}