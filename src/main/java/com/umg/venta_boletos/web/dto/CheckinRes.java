package com.umg.venta_boletos.web.dto;
import java.time.LocalDate;
public record CheckinRes(Long id, Long boletoId, java.time.LocalDate fechaCheckin,
                         String equipajeFlag, java.math.BigDecimal pesoTotalKg) {}