package com.meetime.hubspot.controller;

import com.meetime.hubspot.config.OAuthConfig;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/oauth")
public class OAuthController {

    private final OAuthConfig oAuthConfig;

    public OAuthController(OAuthConfig oAuthConfig) {
        this.oAuthConfig = oAuthConfig;
    }

    @GetMapping("/authorize")
    public ResponseEntity<String> generateAuthorizationUrl() {
        return ResponseEntity.ok(oAuthConfig.getAuthorizationUrl());
    }
}