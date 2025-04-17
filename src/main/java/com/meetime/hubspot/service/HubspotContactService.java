package com.meetime.hubspot.service;

import com.meetime.hubspot.dto.ContactDTO;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Map;

@Service
public class HubspotContactService {

    private static final String HUBSPOT_CONTACTS_URL = "https://api.hubapi.com/crm/v3/objects/contacts";
    private static final String HUBSPOT_PING_URL = "https://api.hubapi.com/crm/v3/objects/contacts?limit=1";
    private final RestTemplate restTemplate;

    @Autowired
    public HubspotContactService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public boolean testConnection(String accessToken) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(accessToken);

        HttpEntity<Void> request = new HttpEntity<>(headers);

        try {
            ResponseEntity<String> response = restTemplate.exchange(
                HUBSPOT_PING_URL,
                HttpMethod.GET,
                request,
                String.class
            );
            return response.getStatusCode().is2xxSuccessful();
        } catch (Exception e) {
            return false;
        }
    }

    public String createContact(ContactDTO contactDTO, String accessToken) {
        Map<String, Object> properties = new HashMap<>();
        properties.put("firstname", contactDTO.getFirstName());
        properties.put("lastname", contactDTO.getLastName());
        properties.put("email", contactDTO.getEmail());
        properties.put("phone", contactDTO.getPhone());
        properties.put("company", contactDTO.getCompany());
        properties.put("website", contactDTO.getWebsite());

        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("properties", properties);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(accessToken);

        HttpEntity<Map<String, Object>> request = new HttpEntity<>(requestBody, headers);

        try {
            ResponseEntity<String> response = restTemplate.postForEntity(HUBSPOT_CONTACTS_URL, request, String.class);

            if (response.getStatusCode().is2xxSuccessful()) {
                return "Contato criado com sucesso no HubSpot.";
            } else {
                throw new RuntimeException("Erro ao criar contato no HubSpot: " + response.getBody());
            }
        } catch (Exception e) {
            throw new RuntimeException("Erro ao comunicar com o HubSpot: " + e.getMessage());
        }
    }
}
