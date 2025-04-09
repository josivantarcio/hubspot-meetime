package com.meetime.hubspot.config;

import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Service
public class OAuthTokenService {

	@Value("${hubspot.client_id}")
	private String clientId;

	@Value("${hubspot.client_secret}")
	private String clientSecret;

	@Value("${hubspot.redirect_uri}")
	private String redirectUri;

	private static final String TOKEN_URL = "https://api.hubapi.com/oauth/v1/token";

	/**
	 * Troca o código de autorização por um token de acesso.
	 *
	 * @param code Código de autorização recebido.
	 * @return Token de acesso ou exceção detalhada em caso de erro.
	 */
	public String exchangeAuthorizationCode(String code) {
		if (code == null || code.isEmpty()) {
			throw new IllegalArgumentException("O código de autorização não pode ser nulo ou vazio.");
		}

		RestTemplate restTemplate = new RestTemplate();

		// Monta o corpo da requisição como form-data
		MultiValueMap<String, String> requestBody = new LinkedMultiValueMap<>();
		requestBody.add("grant_type", "authorization_code");
		requestBody.add("client_id", clientId);
		requestBody.add("client_secret", clientSecret);
		requestBody.add("redirect_uri", redirectUri);
		requestBody.add("code", code);

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

		HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(requestBody, headers);

		ResponseEntity<Map<String, Object>> response;
		try {
			response = restTemplate.exchange(TOKEN_URL, HttpMethod.POST, entity,
					new ParameterizedTypeReference<Map<String, Object>>() {
					});
		} catch (Exception e) {
			throw new RuntimeException("Erro ao enviar requisição: " + e.getMessage());
		}

		if (response.getStatusCode() == HttpStatus.OK && response.getBody() != null) {
			return response.getBody().get("access_token").toString();
		} else {
			throw new RuntimeException("Erro ao trocar o código pelo token. Status: " + response.getStatusCode());
		}
	}
}