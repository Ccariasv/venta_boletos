package com.umg.venta_boletos.web.dto;

import jakarta.validation.constraints.*;

public record AvionReq(
        @NotBlank @Size(max=100) String modelo,
        @Size(max=100) String fabricante,
        @NotNull Integer capacidadTotal,
        @NotNull Integer estadoId,
        @NotBlank @Size(max=20) String matricula
) {}