package com.meetime.hubspot.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/contacts")
public class ContactController {

    private final Map<String, Map<String, String>> contacts = new HashMap<>();

    @PostMapping
    public ResponseEntity<String> createContact(@RequestBody Map<String, String> contact) {
        String contactId = "ID_" + (contacts.size() + 1);
        contacts.put(contactId, contact);
        return ResponseEntity.ok("Contato criado com ID: " + contactId);
    }

    @GetMapping
    public ResponseEntity<Map<String, Map<String, String>>> getContacts() {
        return ResponseEntity.ok(contacts);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateContact(@PathVariable String id, @RequestBody Map<String, String> updatedData) {
        if (contacts.containsKey(id)) {
            contacts.get(id).putAll(updatedData);
            return ResponseEntity.ok("Contato atualizado com sucesso.");
        } else {
            return ResponseEntity.status(404).body("Contato não encontrado.");
        }
    }
}