package com.meetime.hubspot.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/contacts")
public class ContactController {

    private final List<String> contacts = new ArrayList<>();

    @PostMapping
    public ResponseEntity<String> createContact(@RequestBody String contact) {
        contacts.add(contact);
        return ResponseEntity.ok("Contato criado com sucesso: " + contact);
    }

    @GetMapping
    public ResponseEntity<List<String>> getContacts() {
        return ResponseEntity.ok(contacts);
    }
}