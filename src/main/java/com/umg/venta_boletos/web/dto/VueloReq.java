package com.umg.venta_boletos.web.dto;

import jakarta.validation.constraints.*;
import java.time.OffsetDateTime;

public record VueloReq(
        @NotNull Long rutaId,
        @NotNull Long avionId,
        @NotBlank @Size(max=10) String numeroVuelo,
        @NotNull OffsetDateTime salidaTs,
        @NotNull OffsetDateTime llegadaTs,
        @NotNull Integer estadoId
) {}
