package com.umg.venta_boletos.common;

import org.springframework.http.*;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Map<String,Object>> notFound(NotFoundException ex){
        Map<String,Object> body = new LinkedHashMap<>();
        body.put("status", 404);
        body.put("error", "Not Found");
        body.put("message", Optional.ofNullable(ex.getMessage()).orElse("Recurso no encontrado"));
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(body);
    }

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<Map<String,Object>> business(BusinessException ex){
        Map<String,Object> body = new LinkedHashMap<>();
        body.put("status", 409);
        body.put("error", "Conflict");
        body.put("message", Optional.ofNullable(ex.getMessage()).orElse("Regla de negocio violada"));
        return ResponseEntity.status(HttpStatus.CONFLICT).body(body);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String,Object>> validation(MethodArgumentNotValidException ex){
        var fieldErrors = ex.getBindingResult().getFieldErrors().stream()
                .map(fe -> {
                    Map<String,Object> m = new LinkedHashMap<>();
                    m.put("field", fe.getField());
                    m.put("message", Optional.ofNullable(fe.getDefaultMessage()).orElse("Validación inválida"));
                    // Map.of no admite null; LinkedHashMap sí. Si rejectedValue es null no hay problema.
                    m.put("rejectedValue", fe.getRejectedValue());
                    return m;
                })
                .collect(Collectors.toList());

        var globalErrors = ex.getBindingResult().getGlobalErrors().stream()
                .map(ge -> {
                    Map<String,Object> m = new LinkedHashMap<>();
                    m.put("object", ge.getObjectName());
                    m.put("message", Optional.ofNullable(ge.getDefaultMessage()).orElse("Validación inválida"));
                    return m;
                })
                .collect(Collectors.toList());

        Map<String,Object> body = new LinkedHashMap<>();
        body.put("status", 400);
        body.put("error", "Bad Request");
        body.put("message", "Validation failed");
        body.put("fieldErrors", fieldErrors);
        body.put("globalErrors", globalErrors);

        return ResponseEntity.badRequest().body(body);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String,Object>> generic(Exception ex){
        Map<String,Object> body = new LinkedHashMap<>();
        body.put("status", 500);
        body.put("error", "Internal Server Error");
        body.put("message", Optional.ofNullable(ex.getMessage()).orElse("Error inesperado"));
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(body);
    }
}
