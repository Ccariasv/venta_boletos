package com.umg.venta_boletos.ws;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class EchoWsController {
    private final SimpMessagingTemplate ws;
    public EchoWsController(SimpMessagingTemplate ws){ this.ws = ws; }

    // Cliente envía a /app/echo con un texto; servidor reenvía a /topic/echo
    @MessageMapping("/echo")
    public void echo(String payload){
        ws.convertAndSend("/topic/echo", payload);
    }
}
