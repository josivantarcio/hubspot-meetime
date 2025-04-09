package com.meetime.hubspot.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/webhooks")
public class WebhookController {

    @PostMapping("/contact")
    public ResponseEntity<String> processWebhook(@RequestBody Map<String, Object> webhookData) {
        System.out.println("Evento recebido: " + webhookData);
        return ResponseEntity.ok("Webhook processado com sucesso.");
    }
}