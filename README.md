## 📘 `README.md` — Integração HubSpot com Java 21 + Spring Boot

```markdown
# 📡 Integração com HubSpot via OAuth2 - Java 21 + Spring Boot

Projeto backend com API REST desenvolvida em Java 21 utilizando Spring Boot, com integração à API do HubSpot via OAuth2 (Authorization Code Flow), criação de contatos e recebimento de webhooks.

---

## ✅ Funcionalidades Implementadas

- [x] Autenticação OAuth2 (authorization code flow)
- [x] Troca do código de autorização por token de acesso
- [x] Criação de contatos no CRM HubSpot
- [x] Recebimento de eventos via webhook (ex: contact.creation)
- [x] Arquitetura modular com boas práticas (Clean Code, SOLID)
- [x] Configuração de segurança com Spring Security
- [x] Documentação automática da API com Swagger/OpenAPI

---

## 🛠️ Tecnologias

- Java 21
- Spring Boot
- Spring Security
- OAuth2 Client
- Maven
- RestTemplate
- Swagger (springdoc-openapi)
- Postman (para testes)
- HubSpot Developer Platform

---

## 📂 Estrutura de Diretórios

```
src/main/java/com/meetime/hubspot
│
├── config
│   ├── OAuthConfig.java
│   ├── OAuthTokenService.java
│   └── SecurityConfig.java
│
├── controller
│   ├── OAuthController.java
│   ├── ContactController.java
│   └── WebhookController.java
│
└── service
    └── HubspotContactService.java
```

---

## ⚙️ Como executar o projeto localmente

### 1. Pré-requisitos

- Java 21
- Maven
- Conta de desenvolvedor no HubSpot + test account

### 2. Configurar variáveis no `application.properties`

```properties
hubspot.client_id=SUA_CLIENT_ID
hubspot.client_secret=SUA_CLIENT_SECRET
hubspot.redirect_uri=http://localhost:8080/oauth/callback
hubspot.scope=crm.objects.contacts.read crm.objects.contacts.write
```

### 3. Executar a aplicação

```bash
./mvnw spring-boot:run
```

### 4. Gerar URL de autorização

Acesse no navegador:

```
http://localhost:8080/oauth/authorize
```

Você será redirecionado ao HubSpot para login e autorização.

### 5. Receber o `access_token`

Após autorizar, a aplicação troca o `code` e retorna o `access_token` na tela. Use esse token para os próximos testes.

---

## 📬 Testar com Postman

### Criar Contato:

- **POST:** `http://localhost:8080/contacts`
- **Headers:**
  ```
  Authorization: Bearer SEU_ACCESS_TOKEN
  Content-Type: application/json
  ```
- **Body:**
```json
{
  "email": "exemplo@hubspot.com",
  "firstname": "Josevan",
  "lastname": "Oliveira"
}
```

### Simular Webhook:

- **POST:** `http://localhost:8080/webhooks`
- **Body:**
```json
[
  {
    "eventType": "contact.creation",
    "objectId": "12345",
    "propertyName": "email",
    "propertyValue": "teste@hubspot.com",
    "timestamp": 1684600000000
  }
]
```

---

## 📑 Endpoints REST

| Método | Endpoint               | Descrição                                   |
|--------|------------------------|---------------------------------------------|
| GET    | `/hello`               | Retorna mensagem simples (teste)           |
| GET    | `/oauth/authorize`     | Redireciona para o login do HubSpot        |
| GET    | `/oauth/callback`      | Callback para troca do `code` pelo token   |
| GET    | `/contacts`            | Lista contatos (requer token)              |
| POST   | `/contacts`            | Cria contato (requer token)                |
| POST   | `/webhooks`            | Recebe evento de webhook do HubSpot        |

---

## 📚 Swagger UI

A documentação interativa está disponível em:

👉 [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)  
👉 Ou diretamente em `/v3/api-docs`

---

## ⚠️ Observações

- Certifique-se de registrar sua aplicação no [HubSpot Developer Portal](https://developers.hubspot.com).
- O valor do `redirect_uri` precisa coincidir exatamente com o configurado no HubSpot.
- Lembre-se de incluir `/v3/api-docs/**` e `/swagger-ui/**` nas permissões do `SecurityConfig`.

---

## 📌 Melhorias Futuras

- Armazenar tokens com validade e refresh automático
- Validação de assinatura de Webhook (segurança)
- Persistência de contatos recebidos no webhook
- Implementação de testes unitários e integração
- Adicionar Dockerfile e configuração para deploy

---

## 📎 Repositório GitHub

**Repositório:**  
[https://github.com/josivantarcio/hubspot-meetime.git](https://github.com/josivantarcio/hubspot-meetime.git)

---

## 👨‍💻 Desenvolvido por

**Josevan Oliveira**  
Engenheiro de Software | Especialista em IA | Full Stack & Data Science  
```