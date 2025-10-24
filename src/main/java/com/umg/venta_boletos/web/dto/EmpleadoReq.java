package com.umg.venta_boletos.web.dto;

import jakarta.validation.constraints.*;

public record EmpleadoReq(
        @NotBlank @Size(max=100) String nombres,
        @NotBlank @Size(max=100) String apellidos,
        @NotBlank @Size(max=50) String puesto,
        @Size(max=100) String email,
        @Size(max=20) String telefono
) {}