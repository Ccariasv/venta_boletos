package com.umg.venta_boletos.web.dto;

import jakarta.validation.constraints.*;

public record TurnoTripulacionReq(
        @NotNull Long empleadoId,
        @NotNull Long vueloId,
        @NotNull Integer rolId
) {}