package com.umg.venta_boletos.web.dto;

import jakarta.validation.constraints.*;

public record AvionReq(String modelo, String fabricante, Integer capacidadTotal, Integer estadoId, String matricula) {}