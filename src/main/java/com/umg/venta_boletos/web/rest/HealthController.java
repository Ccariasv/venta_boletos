package com.umg.venta_boletos.web.rest;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class HealthController {
    @GetMapping("/ping")
    public String ping() { return "pong"; }
}