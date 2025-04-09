package com.meetime.hubspot.service;

import org.springframework.stereotype.Service;

@Service
public class OAuthService {

    public String generateAuthorizationUrl() {
        String clientId = "546c6248-9948-4471-9bc7-516239afa271";
        String redirectUri = "http://localhost:8080/oauth/callback";
        String scopes = "crm.objects.contacts.read%20crm.objects.contacts.write%20oauth"; // Escopos separados por espaço (%20)

        // Monta a URL de autorização do HubSpot
        return "https://app.hubspot.com/oauth/authorize"
            + "?client_id=" + clientId
            + "&redirect_uri=" + redirectUri
            + "&scope=" + scopes;
    }
}