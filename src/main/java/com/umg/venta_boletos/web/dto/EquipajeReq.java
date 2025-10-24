package com.umg.venta_boletos.web.dto;

import jakarta.validation.constraints.*;

public record EquipajeReq(
        @NotNull Long checkinId,
        @Size(max=200) String descripcion,
        @NotNull Double pesoKg
) {}