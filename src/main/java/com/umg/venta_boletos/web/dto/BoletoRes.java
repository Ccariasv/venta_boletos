package com.umg.venta_boletos.web.dto;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;

public record BoletoRes(
        Long id,
        Long vueloId, String numeroVuelo,
        Long pasajeroId, String pasajeroNombre,
        Long asientoId, String asientoNumero,
        BigDecimal precio, LocalDate fechaEmision,
        Integer estadoId, String estadoCodigo
) {}