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

---

## 🛠️ Tecnologias

- Java 21
- Spring Boot
- Maven
- RestTemplate
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

## 📌 Melhorias Futuras

- Armazenar tokens com validade e refresh automático
- Validação de assinatura de Webhook (segurança)
- Persistência de contatos recebidos no webhook
- Implementação de testes unitários e integração

---

## 📎 Repositório GitHub

**Repositório:**  
[https://github.com/josivantarcio/hubspot-meetime.git](https://github.com/josivantarcio/hubspot-meetime.git)

---

## 👨‍💻 Desenvolvido por

**Josevan Oliveira**  
Engenheiro de Software | Especialista em IA | Full Stack & Data Science  
```