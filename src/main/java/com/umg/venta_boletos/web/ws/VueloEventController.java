package com.umg.venta_boletos.web.ws;

import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Slf4j
@Controller
public class VueloEventController {

    private final SimpMessagingTemplate messagingTemplate;

    public VueloEventController(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    // Cliente envía un mensaje a /app/vuelo.update
    @MessageMapping("/vuelo.update")
    public void receiveUpdate(@Payload String msg) {
        log.info("📡 Evento recibido: {}", msg);

        // Reenviar a todos los suscritos al tema
        messagingTemplate.convertAndSend("/topic/vuelos", msg);
    }
}
