package com.umg.venta_boletos.web.dto;

public record RutaRes(
        Long id,
        Long aOrigenId, String aOrigenNombre, String aOrigenIata,
        Long aDestinoId, String aDestinoNombre, String aDestinoIata,
        Integer duracionMin, Double distanciaKm
) {}