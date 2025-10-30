package com.umg.venta_boletos.web.dto;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;

public record BoletoReq(Long vueloId, Long pasajeroId, Long asientoId,
                        java.math.BigDecimal precio, Integer estadoId) {}