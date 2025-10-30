package com.umg.venta_boletos.web.dto;

import jakarta.validation.constraints.*;

public record EquipajeReq(Long checkinId, String descripcion, java.math.BigDecimal pesoKg) {}