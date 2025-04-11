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
