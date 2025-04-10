## 📄 `docs/documentacao.md`

```markdown
# Documentação Técnica - Integração com HubSpot

## 1. Visão Geral

Este projeto integra uma aplicação backend desenvolvida em Java 21 com Spring Boot à API da HubSpot utilizando OAuth2 (Authorization Code Flow). Ele permite realizar a autenticação segura, criação de contatos no CRM HubSpot e o recebimento de eventos via webhooks.

---

## 2. Arquitetura da Solução

O projeto segue uma arquitetura modular dividida em camadas:

- **Controller**: gerencia os endpoints públicos (REST)
- **Service**: camada de lógica de negócio e integração com APIs externas
- **Model**: representação de dados (DTOs)
- **Config**: classes de configuração de segurança e OAuth

O fluxo de autenticação utiliza o padrão OAuth2 com troca de código de autorização por token de acesso.

---

## 3. Tecnologias Utilizadas

- Java 21
- Spring Boot 3.x
- Spring Web
- Spring Security
- Maven
- HubSpot Developer Platform
- Postman (para testes)

---

## 4. Bibliotecas Utilizadas (Maven)

| Biblioteca                   | Finalidade                                   |
|------------------------------|----------------------------------------------|
| spring-boot-starter-web      | Criação de APIs REST                         |
| spring-boot-starter-security | Controle de autenticação e rotas protegidas  |
| spring-web                   | Comunicação com APIs externas (RestTemplate) |
| spring-boot-devtools         | Hot reload em ambiente de desenvolvimento    |
| spring-boot-maven-plugin     | Empacotamento e execução do projeto          |

---

## 5. Estrutura de Diretórios

```
src/main/java/com/meetime/hubspot
├── config         # Configurações OAuth2, tokens e segurança
├── controller     # Endpoints da aplicação (OAuth, contatos, webhooks)
├── service        # Serviços com regras de negócio e integração com HubSpot
├── model          # Objetos de transferência de dados (ex: OAuthTokenResponse)
└── resources      # Arquivos de configuração (application.properties)
```

---

## 6. Fluxo de Autenticação OAuth2

1. A aplicação gera a URL de autorização e redireciona o usuário.
2. O usuário faz login e autoriza o app na conta HubSpot.
3. A HubSpot retorna o código (`code`) no `redirect_uri`.
4. A aplicação troca o código por um `access_token` e `refresh_token`.
5. Os tokens são utilizados para consumir a API HubSpot.
6. O token pode ser renovado usando o `refresh_token`.

---

## 7. Endpoints Implementados

### Autenticação
- `GET /oauth/authorize` – Gera URL de autorização com base nas configs
- `GET /oauth/callback` – Recebe e troca o `code` pelo token
- `POST /oauth/refresh` – Gera um novo `access_token` usando o `refresh_token`

### Contatos
- `POST /contacts` – Criação de contato no CRM HubSpot (requer token válido)

### Webhooks
- `POST /webhooks` – Recebe eventos enviados pela HubSpot, como `contact.creation`

---

## 8. Testes com Postman

Todos os endpoints foram testados via Postman. É necessário enviar o `access_token` nos headers para os endpoints protegidos.

### Header padrão
```
Authorization: Bearer SEU_ACCESS_TOKEN
Content-Type: application/json
```

### Exemplo de criação de contato:
```json
{
  "email": "exemplo@hubspot.com",
  "firstname": "Josevan",
  "lastname": "Oliveira"
}
```

### Exemplo de webhook recebido:
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

## 9. Execução do Projeto

### Pré-requisitos

- Java 21
- Maven
- Conta de desenvolvedor no HubSpot
- Conta de teste vinculada para gerar o `access_token`

### Configuração

No arquivo `src/main/resources/application.properties`:

```properties
hubspot.client_id=SEU_CLIENT_ID
hubspot.client_secret=SEU_CLIENT_SECRET
hubspot.redirect_uri=http://localhost:8080/oauth/callback
hubspot.scope=crm.objects.contacts.read crm.objects.contacts.write
```

### Execução local

```bash
./mvnw spring-boot:run
```

---

## 10. Melhorias Futuras

- Armazenamento de tokens e contatos em banco PostgreSQL
- Agendamento automático de renovação de tokens
- Validação de assinatura de webhooks (HMAC SHA-256)
- Integração com WhatsApp API para envio de mensagens
- Testes unitários e de integração com JUnit e Mockito
- Interface web para dashboard de monitoramento (opcional)

---

## 11. Autor

Josevan Oliveira  
Engenheiro de Software | Especialista em IA | Full Stack & Data Science  
GitHub: https://github.com/josivantarcio
