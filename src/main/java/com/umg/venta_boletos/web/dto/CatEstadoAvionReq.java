package com.umg.venta_boletos.web.dto;

import jakarta.validation.constraints.*;

public record CatEstadoAvionReq(
        @NotBlank @Size(max=30) String codigo,
        @Size(max=200) String descripcion
) {}