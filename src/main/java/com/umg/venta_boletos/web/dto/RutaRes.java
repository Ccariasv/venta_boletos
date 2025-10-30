package com.umg.venta_boletos.web.dto;

public record RutaRes(Long id, Long origenId, Long destinoId, Integer duracionMin, java.math.BigDecimal distanciaKm) {}