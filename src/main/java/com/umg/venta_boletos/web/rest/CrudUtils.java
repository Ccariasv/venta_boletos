package com.umg.venta_boletos.web.rest;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class CrudUtils {
    public static ResponseStatusException notFound() {
        return new ResponseStatusException(HttpStatus.NOT_FOUND);
    }
}
