package com.umg.venta_boletos.web.dto;
import java.time.LocalDate;
public record CheckinRes(
        Long id, Long boletoId, LocalDate fechaCheckin,
        String equipajeFlag, Double pesoTotalKg
) {}