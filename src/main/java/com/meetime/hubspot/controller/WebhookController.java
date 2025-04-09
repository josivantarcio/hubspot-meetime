package com.meetime.hubspot.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/webhooks")
public class WebhookController {

    @PostMapping
    public ResponseEntity<String> receiveWebhook(@RequestBody List<Map<String, Object>> events) {
        for (Map<String, Object> event : events) {
            String eventType = (String) event.get("eventType");
            if ("contact.creation".equalsIgnoreCase(eventType)) {
                System.out.println("🔔 Novo contato criado:");
                System.out.println(event);
               
            } else {
                System.out.println("📌 Evento ignorado: " + eventType);
            }
        }

        return new ResponseEntity<>("Webhook recebido com sucesso", HttpStatus.OK);
    }
}
