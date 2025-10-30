package com.umg.venta_boletos.web.dto;

import jakarta.validation.constraints.*;
import java.time.OffsetDateTime;

public record VueloReq(Long rutaId, Long avionId, String numeroVuelo,
                       java.time.OffsetDateTime salidaTs, java.time.OffsetDateTime llegadaTs,
                       Integer estadoId) {}