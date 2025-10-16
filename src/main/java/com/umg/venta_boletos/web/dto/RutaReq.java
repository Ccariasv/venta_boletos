package com.umg.venta_boletos.web.dto;

import jakarta.validation.constraints.*;

public record RutaReq(
        @NotNull Long aOrigenId,
        @NotNull Long aDestinoId,
        @NotNull Integer duracionMin,
        Double distanciaKm
) {}