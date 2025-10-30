package com.umg.venta_boletos.web.dto;

import jakarta.validation.constraints.*;

public record PasajeroReq(String nombres, String apellidos, String email, String telefono, String documentoIdentidad) {}