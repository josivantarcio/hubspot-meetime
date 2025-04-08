package com.meetime.hubspot.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OAuthConfig {

    @Value("${hubspot.client_id}")
    private String clientId;

    @Value("${hubspot.redirect_uri}")
    private String redirectUri;

    private static final String AUTH_URL = "https://app.hubspot.com/oauth/authorize";

    public String getAuthorizationUrl() {
        return AUTH_URL + "?client_id=" + clientId + "&redirect_uri=" + redirectUri + "&scope=contacts";
    }
}