## 📄 `README.md`

```markdown
# Integração com HubSpot via OAuth2 - Java 21 + Spring Boot

Este projeto tem como objetivo integrar uma aplicação backend em Java com a API do HubSpot, utilizando o fluxo de autenticação OAuth2. A aplicação permite autenticar usuários, criar contatos e receber eventos via webhooks.

## Funcionalidades

- Autenticação via OAuth2 (Authorization Code Flow)
- Troca de code por access_token e refresh_token
- Criação de contatos no CRM HubSpot
- Endpoint para renovação de token via refresh_token
- Recebimento de notificações por webhook
- Estrutura modular e segura com Spring Boot

## Tecnologias Utilizadas

- Java 21
- Spring Boot 3.x
- Spring Web (RestTemplate)
- Spring Security
- Maven

## Bibliotecas Utilizadas

| Biblioteca                   | Finalidade                                  |
|------------------------------|---------------------------------------------|
| spring-boot-starter-web      | Criação de endpoints REST                   |
| spring-boot-starter-security | Controle de rotas e autenticação            |
| spring-web                   | Comunicação HTTP com a API do HubSpot       |
| spring-boot-devtools         | Hot reload para desenvolvimento local       |
| spring-boot-maven-plugin     | Empacotamento e execução do projeto         |

## Estrutura do Projeto

```
src/main/java/com/meetime/hubspot
├── config         # Configurações de OAuth2 e segurança
├── controller     # Endpoints REST (OAuth, contatos, webhooks)
├── service        # Lógica de negócio (HubSpot API, refresh)
├── model          # Representação de dados (OAuthTokenResponse)
└── resources
```

## Configuração

No arquivo `src/main/resources/application.properties`, defina:

```properties
hubspot.client_id=SEU_CLIENT_ID
hubspot.client_secret=SEU_CLIENT_SECRET
hubspot.redirect_uri=http://localhost:8080/oauth/callback
hubspot.scope=crm.objects.contacts.read crm.objects.contacts.write
```

## Execução

```bash
./mvnw spring-boot:run
```

## Endpoints

| Método | Rota               | Descrição                                        |
|--------|--------------------|--------------------------------------------------|
| GET    | /oauth/authorize   | Gera a URL de autorização com o HubSpot          |
| GET    | /oauth/callback    | Recebe o código e troca por access_token         |
| POST   | /oauth/refresh     | Gera novo access_token com base no refresh_token |
| POST   | /contacts          | Cria um novo contato no CRM HubSpot              |
| POST   | /webhooks          | Recebe notificações de eventos (webhooks)        |

## Testes com Postman

### Criar contato

```http
POST http://localhost:8080/contacts
Authorization: Bearer SEU_ACCESS_TOKEN
Content-Type: application/json
```

```json
{
  "email": "exemplo@hubspot.com",
  "firstname": "Josevan",
  "lastname": "Oliveira"
}
```

### Simular webhook

```http
POST http://localhost:8080/webhooks
Content-Type: application/json
```

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

## Autor

Josevan Oliveira  
GitHub: https://github.com/josivantarcio
```
