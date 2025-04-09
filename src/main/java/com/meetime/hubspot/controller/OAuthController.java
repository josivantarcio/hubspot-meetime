package com.meetime.hubspot.controller;

import org.springframework.http.HttpStatus; // add0804 19:35
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam; //add0804 19:35
import org.springframework.web.bind.annotation.RestController;

import com.meetime.hubspot.config.OAuthConfig;
import com.meetime.hubspot.config.OAuthTokenService;

@RestController
@RequestMapping("/oauth")
public class OAuthController {

    private final OAuthConfig oAuthConfig;
    private final OAuthTokenService oAuthTokenService; //add0804 19:37 p controller

    public OAuthController(OAuthConfig oAuthConfig, OAuthTokenService oAuthTokenService) {
        this.oAuthConfig = oAuthConfig;
        this.oAuthTokenService = oAuthTokenService;
    }

    @GetMapping("/authorize")
    public ResponseEntity<String> generateAuthorizationUrl() {
        return ResponseEntity.ok(oAuthConfig.getAuthorizationUrl());
    }

    @GetMapping("/callback")
    public ResponseEntity<String> processCallback(@RequestParam("code") String code) {
        try {
            String accessToken = oAuthTokenService.exchangeAuthorizationCode(code);
            return ResponseEntity.ok("Token de acesso obtido: " + accessToken);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                                 .body("Erro ao processar callback: " + e.getMessage());
        }
    }
}
