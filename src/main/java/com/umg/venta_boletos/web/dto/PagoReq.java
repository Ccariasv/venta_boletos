package com.umg.venta_boletos.web.dto;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;

public record PagoReq(
        @NotNull Long boletoId,
        @NotNull Integer metodoPagoId,
        @NotNull BigDecimal monto,
        @Size(max=100) String referenciaExt,
        LocalDate fechaPago
) {}