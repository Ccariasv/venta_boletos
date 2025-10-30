package com.umg.venta_boletos.web.dto;

public record UsuarioRes(
        Long id,
        String username,
        Integer rolId,
        Long empleadoId,
        Boolean activo
) {}