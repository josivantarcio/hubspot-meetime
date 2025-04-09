package com.meetime.hubspot.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.*;

import java.util.HashMap;
import java.util.Map;

@Service
public class OAuthTokenService {

    @Value("${hubspot.client_id}")
    private String clientId;

    @Value("${hubspot.client_secret}")
    private String clientSecret;

    @Value("${hubspot.redirect_uri}")
    private String redirectUri;

    private static final String TOKEN_URL = "https://api.hubapi.com/oauth/v1/token";

    public String exchangeAuthorizationCode(String code) {
        RestTemplate restTemplate = new RestTemplate();

        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("grant_type", "authorization_code");
        requestBody.put("client_id", clientId);
        requestBody.put("client_secret", clientSecret);
        requestBody.put("redirect_uri", redirectUri);
        requestBody.put("code", code);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        HttpEntity<Map<String, String>> entity = new HttpEntity<>(requestBody, headers);

        ResponseEntity<Map> response = restTemplate.exchange(TOKEN_URL, HttpMethod.POST, entity, Map.class);

        if (response.getStatusCode() == HttpStatus.OK) {
            return response.getBody().get("access_token").toString();
        } else {
            throw new RuntimeException("ERRO ao TROCAR o código de autorização pelo token. Favor verificar novamente");
        }
    }
}