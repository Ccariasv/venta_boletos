package com.umg.venta_boletos.web.dto;

import jakarta.validation.constraints.*;

public record UsuarioReq(
        String username,
        String password,     // opcional en update; el servicio decide si re-hashear
        Integer rolId,       // RolSistema.id
        Long empleadoId,     // Empleado.id (nullable)
        Boolean activo       // nullable: si es null no se toca en update
) {}