package com.umg.venta_boletos.web.dto;

import jakarta.validation.constraints.*;

public record CatMetodoPagoReq(String codigo, String descripcion) {}