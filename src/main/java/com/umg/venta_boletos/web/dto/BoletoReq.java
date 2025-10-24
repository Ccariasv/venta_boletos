package com.umg.venta_boletos.web.dto;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;

public record BoletoReq(
        @NotNull Long vueloId,
        @NotNull Long pasajeroId,
        @NotNull Long asientoId,
        @NotNull BigDecimal precio,
        @NotNull Integer estadoId
) {}