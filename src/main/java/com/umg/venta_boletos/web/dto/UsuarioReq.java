package com.umg.venta_boletos.web.dto;

import jakarta.validation.constraints.*;

public record UsuarioReq(
        @NotBlank @Size(max=50) String username,
        @NotBlank @Size(max=256) String passwordHash,
        @NotNull Integer rolId,
        Long empleadoId,
        @NotBlank @Size(min=1, max=1) @Pattern(regexp = "[SN]") String activoFlag
) {}