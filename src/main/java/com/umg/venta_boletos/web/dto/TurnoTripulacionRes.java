package com.umg.venta_boletos.web.dto;

public record TurnoTripulacionRes(
        Long id,
        Long empleadoId, String empleadoNombre,
        Long vueloId, String numeroVuelo,
        Integer rolId, String rolCodigo
) {}