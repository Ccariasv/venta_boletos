package com.umg.venta_boletos.web.dto;

import jakarta.validation.constraints.*;

public record RolSistemaReq(
        String codigo,
        String descripcion
) {}