package com.umg.venta_boletos;

import com.umg.venta_boletos.security.JwtProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(JwtProperties.class)
public class VentaBoletosApplication {
    public static void main(String[] args) {
        SpringApplication.run(VentaBoletosApplication.class, args);
    }
}