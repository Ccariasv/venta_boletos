package com.umg.venta_boletos.web.dto;

public record AeropuertoRes(
        Long id, String nombre, String ciudad, String pais, String codigoIata
) {}