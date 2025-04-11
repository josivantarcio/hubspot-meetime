# 📊 HubSpot Meetime Integration

Integração corporativa com a plataforma **HubSpot**, utilizando autenticação OAuth2, controle de permissões com **Spring Security**, documentação interativa via **Swagger** e integração pronta para automações e webhooks.

---

## 🚀 Visão Geral

Este projeto visa facilitar a integração de sistemas externos com o HubSpot, garantindo uma comunicação segura, escalável e de fácil manutenção. É voltado para empresas que desejam:

- Automatizar sincronização de contatos.
- Enviar e receber eventos por Webhooks.
- Controlar acessos via roles (admin, operador, etc.).
- Garantir rastreabilidade das requisições.

---

## 🧰 Tecnologias Utilizadas

- ⚙️ Java 21
- 🌱 Spring Boot 3.x
- 🔐 Spring Security + OAuth2
- 🛠️ Spring Data JPA
- 🧪 Swagger/OpenAPI 3.0
- 🐘 PostgreSQL (ou H2 para testes)
- 🐳 Docker
- 🧼 Maven

---

## 📦 Funcionalidades

- [x] Autenticação OAuth2 com HubSpot
- [x] Criação e consulta de contatos
- [x] Recebimento de webhooks
- [x] Proteção de rotas via roles
- [x] Documentação automática da API
- [x] Integração com banco de dados relacional

---

## ⚙️ Como Executar Localmente

### 1. Clonar o projeto

```bash
git clone https://github.com/josivantarcio/hubspot-meetime.git
cd hubspot-meetime
```

### 2. Configurar `application.properties`

Edite o arquivo `src/main/resources/application.properties` com suas credenciais do HubSpot:

```properties
spring.security.oauth2.client.registration.hubspot.client-id=SEU_CLIENT_ID
spring.security.oauth2.client.registration.hubspot.client-secret=SEU_CLIENT_SECRET
spring.security.oauth2.client.registration.hubspot.redirect-uri=http://localhost:8080/login/oauth2/code/hubspot
```

### 3. Executar

```bash
mvn spring-boot:run
```

A aplicação estará disponível em:  
📍 `http://localhost:8080`

---

## 📑 Documentação da API

Após rodar o projeto, acesse a interface Swagger:

🔗 [`http://localhost:8080/swagger-ui/index.html`](http://localhost:8080/swagger-ui/index.html)

---

## 📊 Casos de Uso

> Exemplo de aplicação na **carcinicultura (criação de camarões):**
>
> - Captura de leads via formulários integrados ao HubSpot.
> - Notificações automáticas para equipe técnica ao receber novo contato comercial.
> - Painel com status de negociação integrado a sistemas legados via API.
> - Aplicações móveis sincronizadas com dados do CRM via endpoints REST seguros.

---

## 🤝 Contribuições

Contribuições são muito bem-vindas!  
Abra uma issue ou envie um Pull Request para sugerir melhorias.

---

## 📄 Licença

Distribuído sob a licença **MIT**.  
Consulte o arquivo [`LICENSE`](LICENSE) para mais detalhes.

---

## 📬 Contato

Desenvolvido por [**Josevan Oliveira**](https://www.linkedin.com/in/josevan-oliveira/)  
Email: josevanoliveira.dev@gmail.com
```
