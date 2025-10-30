package com.umg.venta_boletos.web.dto;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;

public record PagoReq(Long boletoId, Integer metodoPagoId, java.math.BigDecimal monto, String referenciaExt) {}