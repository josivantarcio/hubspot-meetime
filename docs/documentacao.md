# 📄 Documentação Técnica - Integração com HubSpot

## 1. Visão Geral

Este projeto foi desenvolvido para integrar uma aplicação backend em Java com a API do HubSpot utilizando o protocolo OAuth2. A solução oferece autenticação segura, criação de contatos, recebimento de eventos via Webhook e estrutura modular com boas práticas de desenvolvimento.

---

## 2. Arquitetura da Solução

- Java 21 + Spring Boot (REST API)
- OAuth2 Authorization Code Flow
- Comunicação com a API HubSpot via `RestTemplate`
- Estrutura modular com pacotes: `controller`, `service`, `config`
- Spring Security para controle de rotas e proteção da aplicação
- Integração com API externa do WhatsApp (futuro)
- Persistência planejada com Spring Data + PostgreSQL

---

## 3. Tecnologias Utilizadas

- Java 21  
- Spring Boot 3  
- Spring Security  
- Spring Data (planejado)  
- Maven  
- RestTemplate  
- HubSpot Developer Platform  
- Postman  
- Docker (futuro)

---

## 4. Fluxo de Autenticação OAuth2

1. O usuário acessa o endpoint `/oauth/authorize`
2. É redirecionado ao HubSpot para login/autorização
3. O HubSpot redireciona com o `code` para `/oauth/callback`
4. O backend troca o `code` por um `access_token` e `refresh_token`
5. Tokens podem ser renovados via endpoint `/oauth/refresh`

---

## 5. Endpoints Implementados

### 🔐 Autenticação OAuth2

- `GET /oauth/authorize` → Gera a URL de autenticação
- `GET /oauth/callback` → Recebe e troca o `code` pelo token
- `POST /oauth/refresh` → Gera novo token de acesso via `refresh_token`

### 👥 Contatos

- `POST /contacts` → Cria novo contato no HubSpot (requer `access_token`)

### 📩 Webhooks

- `POST /webhooks` → Recebe eventos do HubSpot, como `contact.creation`

---

## 6. Testes com Postman

As requisições foram testadas utilizando o Postman.  
O `access_token` deve ser passado no header:

```http
Authorization: Bearer SEU_ACCESS_TOKEN
```

Exemplos de payload para criação de contatos e simulação de Webhooks estão disponíveis no arquivo [`README.md`](./README.md).

---

## 7. Melhorias Futuras

- ✅ Endpoint de refresh de token via OAuth2
- 🗃️ Persistência dos tokens e contatos usando PostgreSQL
- 🔁 Agendamento automático de renovação de token
- 🔒 Validação HMAC das assinaturas dos Webhooks recebidos
- 📲 Integração com API do WhatsApp para envio de mensagens
- 🧪 Testes unitários e de integração com JUnit + Mockito
- 📊 Dashboard de monitoramento de eventos e métricas (opcional futuro)
- 🐳 Dockerização para facilitar o deploy

---

## 8. Execução Local

**Pré-requisitos:**
- Java 21
- Maven
- Conta de desenvolvedor HubSpot (com app configurado)
- Test Account do HubSpot

**Configurar `application.properties`:**

```properties
hubspot.client_id=SUA_CLIENT_ID
hubspot.client_secret=SUA_CLIENT_SECRET
hubspot.redirect_uri=http://localhost:8080/oauth/callback
hubspot.scope=crm.objects.contacts.read crm.objects.contacts.write
```

**Executar a aplicação:**

```bash
./mvnw spring-boot:run
```

---

## 9. Autor

**Josevan Oliveira**  
Engenheiro de Software | Especialista em IA | Full Stack & Data Science  
🔗 GitHub: [https://github.com/josivantarcio](https://github.com/josivantarcio)