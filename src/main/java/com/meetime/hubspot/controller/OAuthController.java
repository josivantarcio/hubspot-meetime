package com.meetime.hubspot.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.meetime.hubspot.config.OAuthConfig;
import com.meetime.hubspot.config.OAuthTokenService;

/**
 * Controlador para lidar com o fluxo OAuth do HubSpot.
 * - Geração da URL de autorização.
 * - Processamento do callback e troca de código por token.
 */
@RestController
@RequestMapping("/oauth")
public class OAuthController {

    private final OAuthConfig oAuthConfig; // Configuração OAuth (client_id, client_secret, redirect_uri, etc.)
    private final OAuthTokenService oAuthTokenService; // Serviço responsável por trocar o código pelo token.

    // Construtor para injeção de dependências
    public OAuthController(OAuthConfig oAuthConfig, OAuthTokenService oAuthTokenService) {
        this.oAuthConfig = oAuthConfig;
        this.oAuthTokenService = oAuthTokenService;
    }

    /**
     * Endpoint para gerar a URL de autorização do HubSpot OAuth.
     * - URL contém o client_id, redirect_uri e escopos.
     *
     * @return ResponseEntity com a URL de autorização.
     */
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

    /**
     * Endpoint para processar o callback de autorização do HubSpot.
     * - Recebe o código de autorização via query parameter.
     * - Troca o código pelo token de acesso usando o OAuthTokenService.
     *
     * @param code Código de autorização recebido do HubSpot.
     * @return ResponseEntity com o token de acesso ou erro.
     */
    @GetMapping("/callback")
    public ResponseEntity<String> processCallback(@RequestParam("code") String code) {
        try {
            // Troca o código de autorização pelo token de acesso
            String accessToken = oAuthTokenService.exchangeAuthorizationCode(code);
            return ResponseEntity.ok("Token de acesso obtido: " + accessToken);
        } catch (IllegalArgumentException e) {
            // Trata casos em que o código de autorização é inválido
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                                 .body("Parâmetro 'code' inválido: " + e.getMessage());
        } catch (Exception e) {
            // Trata outros erros genéricos
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body("Erro ao processar o callback: " + e.getMessage());
        }
    }
}