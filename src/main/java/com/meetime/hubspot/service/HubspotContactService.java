package com.meetime.hubspot.service;

import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class HubspotContactService {

    private static final String HUBSPOT_CONTACTS_URL = "https://api.hubapi.com/crm/v3/objects/contacts";

    public String createContact(Map<String, String> contactData, String accessToken) {
        RestTemplate restTemplate = new RestTemplate();

        Map<String, Object> properties = new HashMap<>();
        properties.putAll(contactData);

        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("properties", properties);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(accessToken);

        HttpEntity<Map<String, Object>> request = new HttpEntity<>(requestBody, headers);

        ResponseEntity<String> response = restTemplate.postForEntity(HUBSPOT_CONTACTS_URL, request, String.class);

        if (response.getStatusCode().is2xxSuccessful()) {
            return "Contato criado com sucesso no HubSpot.";
        } else {
            throw new RuntimeException("Erro ao criar contato: " + response.getBody());
        }
    }
}
