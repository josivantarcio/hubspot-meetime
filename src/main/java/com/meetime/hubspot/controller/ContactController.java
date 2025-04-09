package com.meetime.hubspot.controller; 

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("/contacts")
public class ContactController {

    // Simula um banco de dados armazenando contatos em memória usando um HashMap
    private final Map<String, Map<String, String>> contacts = new HashMap<>();

    /**
     * Método para criar um novo contato
     * - Verifica se os dados enviados estão corretos
     * - Gera um ID único para o contato e o armazena no mapa
     * - Retorna uma resposta com o ID do novo contato
     * @param contact Um mapa contendo informações do contato (ex: email, nome, sobrenome)
     * @return ResponseEntity contendo uma mensagem de sucesso ou erro
     */
    @PostMapping
    public ResponseEntity<String> createContact(@RequestBody Map<String, String> contact) {
        String contactId = "ID_" + (contacts.size() + 1); 
        contacts.put(contactId, contact); 
        return ResponseEntity.ok("Contato criado com ID: " + contactId); 
    }

    /**
     * Método para listar todos os contatos armazenados
     * - Retorna um mapa contendo todos os contatos registrados
     * @return ResponseEntity contendo a lista de contatos
     */
    @GetMapping
    public ResponseEntity<Map<String, Map<String, String>>> getContacts() {
        return ResponseEntity.ok(contacts);
    }

    /**
     * Método para atualizar um contato existente
     * - Verifica se o ID fornecido existe no mapa
     * - Atualiza os dados do contato e mantém informações anteriores
     * - Retorna sucesso ou um erro 404 se o contato não for encontrado
     * @param id 
     * @param updatedData 
     * @return ResponseEntity 
     */
    @PutMapping("/{id}")
    public ResponseEntity<String> updateContact(@PathVariable String id, @RequestBody Map<String, String> updatedData) {
        if (contacts.containsKey(id)) { // Verifica se o contato existe antes de atualizar
            contacts.get(id).putAll(updatedData); // Atualiza os dados no mapa
            return ResponseEntity.ok("Contato atualizado com sucesso.");
        } else {
            return ResponseEntity.status(404).body("Contato não encontrado.");
        }
    }

    /**
     * Método para excluir um contato
     * - Verifica se o contato existe no mapa
     * - Remove o contato do armazenamento caso seja encontrado
     * - Retorna sucesso ou erro 404 se o contato não existir
     * @param id 
     * @return ResponseEntity 
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteContact(@PathVariable String id) {
        if (contacts.containsKey(id)) { // Confirma se o contato existe antes da exclusão
            contacts.remove(id); 
            return ResponseEntity.ok("Contato excluído com sucesso.");
        } else {
            return ResponseEntity.status(404).body("Contato não encontrado."); 
        }
    }
}