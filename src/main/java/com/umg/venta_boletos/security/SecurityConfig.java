package com.umg.venta_boletos.security;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.*;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableMethodSecurity
@EnableConfigurationProperties(JwtProperties.class) // <-- usa las props de application.properties
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtFilter;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth
                        // OpenAPI/Swagger
                        .requestMatchers("/v3/api-docs/**","/swagger-ui/**","/swagger-ui.html").permitAll()
                        // WebSocket (handshake + topics)
                        .requestMatchers("/ws/**", "/topic/**", "/app/**").permitAll()
                        // Auth endpoints
                        .requestMatchers("/api/auth/**").permitAll()
                        // GET públicos (lectura)
                        .requestMatchers(HttpMethod.GET, "/api/**").permitAll()
                        // Rutas administrativas (ej. catálogos CRUD)
                        .requestMatchers(HttpMethod.POST, "/api/**").hasAnyRole("ADMIN","AGENTE","CAJERO")
                        .requestMatchers(HttpMethod.PUT,  "/api/**").hasAnyRole("ADMIN","AGENTE","CAJERO")
                        .requestMatchers(HttpMethod.PATCH,"/api/**").hasAnyRole("ADMIN","AGENTE")
                        .requestMatchers(HttpMethod.DELETE,"/api/**").hasRole("ADMIN")
                        // Lo demás
                        .anyRequest().authenticated()
                )
                // No registramos DaoAuthenticationProvider manualmente: Spring usa el UserDetailsService bean + PasswordEncoder
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration cfg) throws Exception {
        return cfg.getAuthenticationManager();
    }
}
