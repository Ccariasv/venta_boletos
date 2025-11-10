package com.umg.venta_boletos.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        // Rutas donde los clientes se suscriben
        config.enableSimpleBroker("/topic", "/queue");
        // Prefijo para los mensajes que envía el cliente al servidor
        config.setApplicationDestinationPrefixes("/app");
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        // Punto de conexión WebSocket (se usará en React/Android)
        registry.addEndpoint("/ws")
                .setAllowedOriginPatterns("*") // permitir CORS de cualquier origen
                .withSockJS(); // fallback si el navegador no soporta WS
    }
}
