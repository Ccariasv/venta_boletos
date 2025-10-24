package com.umg.venta_boletos.web.dto;

import jakarta.validation.constraints.*;
import java.time.LocalDate;

public record CheckinReq(
        @NotNull Long boletoId,
        @NotBlank @Pattern(regexp = "[SN]") String equipajeFlag,
        Double pesoTotalKg,
        LocalDate fechaCheckin
) {}