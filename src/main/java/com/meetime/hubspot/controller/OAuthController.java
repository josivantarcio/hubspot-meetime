package com.meetime.hubspot.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.meetime.hubspot.config.OAuthConfig;
import com.meetime.hubspot.config.OAuthTokenService;
import com.meetime.hubspot.config.OAuthTokenResponse;

@RestController
@RequestMapping("/oauth")
public class OAuthController {

	private final OAuthConfig oAuthConfig;
	private final OAuthTokenService oAuthTokenService;

	public OAuthController(OAuthConfig oAuthConfig, OAuthTokenService oAuthTokenService) {
		this.oAuthConfig = oAuthConfig;
		this.oAuthTokenService = oAuthTokenService;
	}

	@GetMapping("/authorize")
	public ResponseEntity<String> generateAuthorizationUrl() {
		try {
			String authorizationUrl = oAuthConfig.getAuthorizationUrl();
			return ResponseEntity.ok("Acesse a seguinte URL para autorizar o aplicativo: " + authorizationUrl);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Erro ao gerar a URL de autorização: " + e.getMessage());
		}
	}

	@GetMapping("/callback")
	public ResponseEntity<?> processCallback(@RequestParam("code") String code) {
		try {
			OAuthTokenResponse tokenResponse = oAuthTokenService.exchangeAuthorizationCode(code);
			return ResponseEntity.ok(tokenResponse);
		} catch (IllegalArgumentException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Parâmetro 'code' inválido: " + e.getMessage());
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Erro ao processar o callback: " + e.getMessage());
		}
	}
}
