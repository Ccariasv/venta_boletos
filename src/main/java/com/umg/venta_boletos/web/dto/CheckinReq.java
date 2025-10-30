package com.umg.venta_boletos.web.dto;

import jakarta.validation.constraints.*;
import java.time.LocalDate;

public record CheckinReq(Long boletoId, String equipajeFlag, java.math.BigDecimal pesoTotalKg) {}