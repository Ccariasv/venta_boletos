package com.umg.venta_boletos.web.dto;
import jakarta.validation.constraints.*;
import java.time.OffsetDateTime;
public record VueloRes(
        Long id, String numeroVuelo,
        Long rutaId, Long avionId,
        OffsetDateTime salidaTs, OffsetDateTime llegadaTs,
        Integer estadoId, String estadoCodigo
) {}