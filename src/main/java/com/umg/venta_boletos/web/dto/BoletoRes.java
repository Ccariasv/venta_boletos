package com.umg.venta_boletos.web.dto;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;

public record BoletoRes(Long id, Long vueloId, Long pasajeroId, Long asientoId,
                        java.math.BigDecimal precio, java.time.LocalDate fechaEmision,
                        Integer estadoId) {}