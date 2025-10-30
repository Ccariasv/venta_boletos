package com.umg.venta_boletos.web.dto;
import java.math.BigDecimal;
import java.time.LocalDate;
public record PagoRes(Long id, Long boletoId, Integer metodoPagoId, java.math.BigDecimal monto,
                      java.time.LocalDate fechaPago, String referenciaExt) {}