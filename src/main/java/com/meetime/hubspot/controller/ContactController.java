package com.meetime.hubspot.controller;

import com.meetime.hubspot.dto.ContactDTO;
import com.meetime.hubspot.service.HubspotContactService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;

@RestController
@RequestMapping("/contacts")
@Tag(name = "Contacts", description = "API para gerenciamento de contatos no HubSpot")
public class ContactController {

	private final HubspotContactService contactService;

	public ContactController(HubspotContactService contactService) {
		this.contactService = contactService;
	}

	@GetMapping("/test-connection")
	@Operation(summary = "Testa a conexão com o HubSpot")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "Conexão bem sucedida"),
		@ApiResponse(responseCode = "401", description = "Não autorizado"),
		@ApiResponse(responseCode = "500", description = "Erro na conexão")
	})
	public ResponseEntity<String> testConnection(@RequestHeader("Authorization") String authorization) {
		if (authorization == null || !authorization.startsWith("Bearer ")) {
			return ResponseEntity.badRequest().body("Token de autorização inválido. Formato esperado: Bearer <token>");
		}
		
		String accessToken = authorization.replace("Bearer ", "").trim();
		if (accessToken.isEmpty()) {
			return ResponseEntity.badRequest().body("Token de autorização não pode estar vazio");
		}

		try {
			boolean isConnected = contactService.testConnection(accessToken);
			if (isConnected) {
				return ResponseEntity.ok("Conexão com o HubSpot estabelecida com sucesso!");
			} else {
				return ResponseEntity.status(500).body("Não foi possível estabelecer conexão com o HubSpot");
			}
		} catch (HttpClientErrorException e) {
			return ResponseEntity.status(e.getStatusCode()).body("Erro na comunicação com o HubSpot: " + e.getMessage());
		} catch (HttpServerErrorException e) {
			return ResponseEntity.status(e.getStatusCode()).body("Erro no servidor do HubSpot: " + e.getMessage());
		}
	}

	@PostMapping
	@Operation(summary = "Cria um novo contato no HubSpot")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "Contato criado com sucesso"),
		@ApiResponse(responseCode = "400", description = "Dados inválidos"),
		@ApiResponse(responseCode = "401", description = "Não autorizado"),
		@ApiResponse(responseCode = "500", description = "Erro interno do servidor")
	})
	public ResponseEntity<String> createContact(
			@RequestHeader("Authorization") String authorization,
			@Valid @RequestBody ContactDTO contactDTO) {
		
		if (authorization == null || !authorization.startsWith("Bearer ")) {
			return ResponseEntity.badRequest().body("Token de autorização inválido. Formato esperado: Bearer <token>");
		}
		
		String accessToken = authorization.replace("Bearer ", "").trim();
		if (accessToken.isEmpty()) {
			return ResponseEntity.badRequest().body("Token de autorização não pode estar vazio");
		}

		try {
			String result = contactService.createContact(contactDTO, accessToken);
			return ResponseEntity.ok(result);
		} catch (HttpClientErrorException e) {
			return ResponseEntity.status(e.getStatusCode()).body("Erro na comunicação com o HubSpot: " + e.getMessage());
		} catch (HttpServerErrorException e) {
			return ResponseEntity.status(e.getStatusCode()).body("Erro no servidor do HubSpot: " + e.getMessage());
		}
	}
}
