package com.umg.venta_boletos.web.dto;
import jakarta.validation.constraints.*;

public record CatEstadoVueloReq(String codigo, String descripcion) {}