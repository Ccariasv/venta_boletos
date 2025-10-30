package com.umg.venta_boletos.web.dto;

import jakarta.validation.constraints.*;

public record EmpleadoReq(
        String nombres,
        String apellidos,
        String puesto,
        String email,
        String telefono
) {}