package com.meetime.hubspot.controller;

import com.meetime.hubspot.service.HubspotContactService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/contacts")
public class ContactController {

	private final HubspotContactService contactService;

	public ContactController(HubspotContactService contactService) {
		this.contactService = contactService;
	}

	@PostMapping
	public ResponseEntity<String> createContact(@RequestHeader("Authorization") String authorization,
			@RequestBody Map<String, String> contactData) {
		String accessToken = authorization.replace("Bearer ", "");
		String result = contactService.createContact(contactData, accessToken);
		return ResponseEntity.ok(result);
	}
}
