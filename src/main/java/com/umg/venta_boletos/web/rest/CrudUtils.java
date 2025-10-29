package com.umg.venta_boletos.web.rest;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class CrudUtils {
    public static ResponseStatusException notFound() {
        return new ResponseStatusException(HttpStatus.NOT_FOUND);
    }
    public static <T> Page<T> pass(Page<T> page){ return page; } // helper opcional
}
