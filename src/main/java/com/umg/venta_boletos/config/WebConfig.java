package com.umg.venta_boletos.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.*;

@Configuration
public class WebConfig {
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        var cfg = new CorsConfiguration();
        cfg.addAllowedOriginPattern("*");   // endurecer en prod
        cfg.addAllowedHeader("*");
        cfg.addAllowedMethod("*");
        cfg.setAllowCredentials(true);
        var src = new UrlBasedCorsConfigurationSource();
        src.registerCorsConfiguration("/**", cfg);
        return src;
    }
}
