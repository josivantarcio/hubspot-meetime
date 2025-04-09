package com.meetime.hubspot.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/contacts")
public class ContactController {

    private final Map<String, Map<String, String>> contacts = new HashMap<>();

    /**
     * Gera um ID para o contato e armazena o contato em um mapa de memoria
     * @param contact
     * @return contactId
     */
    @PostMapping
    public ResponseEntity<String> createContact(@RequestBody Map<String, String> contact) {
        String contactId = "ID_" + (contacts.size() + 1);
        contacts.put(contactId, contact);
        return ResponseEntity.ok("Contato criado com ID: " + contactId);
    }

    /**
     * todos os contatos sao apresentados
     * @return contactId
     */
    @GetMapping
    public ResponseEntity<Map<String, Map<String, String>>> getContacts() {
        return ResponseEntity.ok(contacts);
    }

    /**
     * faz a verificação de apenas um contato e se existir, atualiza os dados apenas do ID
     * @param id
     * @param updatedData
     * @return mensagem sucesso ou erro 404
     */
    @PutMapping("/{id}")
    public ResponseEntity<String> updateContact(@PathVariable String id, @RequestBody Map<String, String> updatedData) {
        if (contacts.containsKey(id)) {
            contacts.get(id).putAll(updatedData);
            return ResponseEntity.ok("Contato atualizado com sucesso.");
        } else {
            return ResponseEntity.status(404).body("Contato não encontrado.");
        }
    }

    /**
     * verifica se o contactId existe no mapa da memoria. remove o contato do mapa se ele for encontrado
     * @param id
     * @return erro 404 se o contactId não existir
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteContact(@PathVariable String id) {
        if (contacts.containsKey(id)) {
            contacts.remove(id);
            return ResponseEntity.ok("Contato excluído com sucesso.");
        } else {
            return ResponseEntity.status(404).body("Contato não encontrado.");
        }
    }
}