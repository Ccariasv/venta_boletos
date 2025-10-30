package com.umg.venta_boletos.web.dto;

import jakarta.validation.constraints.*;

public record RutaReq(Long origenId, Long destinoId, Integer duracionMin, java.math.BigDecimal distanciaKm) {}