package com.umg.venta_boletos.web.dto;

import jakarta.validation.constraints.*;

public record TurnoTripulacionReq(Long empleadoId, Long vueloId, Integer rolId) {}