package com.umg.venta_boletos.web.dto;

import jakarta.validation.constraints.*;

public record PasajeroReq(
        @NotBlank @Size(max=100) String nombres,
        @NotBlank @Size(max=100) String apellidos,
        @Email @Size(max=100) String email,
        @Size(max=20) String telefono,
        @NotBlank @Size(max=50) String documentoIdentidad
) {}
