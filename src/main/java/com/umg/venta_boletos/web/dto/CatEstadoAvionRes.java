package com.umg.venta_boletos.web.dto;
import jakarta.validation.constraints.*;

public record CatEstadoAvionRes(
        Integer id, String codigo, String descripcion
) {}