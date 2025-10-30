package com.umg.venta_boletos.web.dto;
import jakarta.validation.constraints.*;

public record CatEstadoBoletoReq(String codigo, String descripcion) {}