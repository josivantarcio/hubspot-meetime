# Documentação Técnica - Integração com HubSpot

## 1. Visão Geral
Este projeto foi desenvolvido para integrar uma aplicação backend Java com a API do HubSpot utilizando OAuth2. O objetivo principal foi criar um fluxo seguro de autenticação, realizar o cadastro de contatos via API e tratar eventos via Webhooks.

## 2. Arquitetura da Solução

- Java 21 + Spring Boot (REST API)
- OAuth2 Authorization Code Flow
- Comunicação com API HubSpot via RestTemplate
- Estrutura organizada por camadas (Controller, Service, Config, Model)
- Spring Security para controle de rotas
- Testes via Postman

## 3. Tecnologias Utilizadas

- Java 21
- Spring Boot 3
- Maven
- RestTemplate
- HubSpot Developer Platform
- Postman

## 4. Fluxo de Autenticação OAuth2

1. O usuário acessa o endpoint `/oauth/authorize`
2. É redirecionado ao HubSpot para login/autorização
3. O HubSpot redireciona com o `code` para `/oauth/callback`
4. O backend troca o `code` pelo `access_token` e `refresh_token`

Tokens podem ser renovados com o endpoint `/oauth/refresh` usando o `refresh_token`.

## 5. Endpoints Implementados

### Autenticação OAuth2
- `GET /oauth/authorize` → Gera URL para autenticação
- `GET /oauth/callback` → Recebe e troca o code pelo token
- `POST /oauth/refresh` → Renova o token de acesso

### Contatos
- `POST /contacts` → Cria novo contato no CRM HubSpot (requer access_token)

### Webhooks
- `POST /webhooks` → Recebe eventos como `contact.creation`

## 6. Testes com Postman

Requisições foram validadas com o Postman. Token deve ser passado no header como:


Authorization: Bearer SEU_ACCESS_TOKEN

Exemplos de corpo JSON para criação de contatos e simulação de webhook estão no README.md.

## 7. Melhorias Futuras

- Persistência em PostgreSQL (tokens e contatos)
- Agendamento automático de renovação do token
- Validação HMAC dos webhooks recebidos
- Integração com WhatsApp API para notificações
- Testes unitários e integração (JUnit + Mockito)
- Dashboard para monitoramento (opcional futuro)

## 8. Execução Local

Pré-requisitos:
- Java 21 e Maven instalados
- Conta de desenvolvedor HubSpot e test account
- Token e escopos configurados no `application.properties`

Executar o projeto:

./mvnw spring-boot:run

## 9. Autor

Josevan Oliveira  
Engenheiro de Software | Especialista em IA | Full Stack & Data Science  
GitHub: https://github.com/josivantarcio

