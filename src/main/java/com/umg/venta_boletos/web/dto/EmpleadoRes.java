package com.umg.venta_boletos.web.dto;

public record EmpleadoRes(
        Long id, String nombres, String apellidos, String puesto, String email, String telefono
) {}