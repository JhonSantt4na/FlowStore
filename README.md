# 🛒 FlowStore Microservices - Sistema de E-commerce

[![Java](https://img.shields.io/badge/Java-21-blue.svg)](https://www.oracle.com/java/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.2.3-brightgreen)](https://spring.io/projects/spring-boot)
[![License](https://img.shields.io/badge/license-MIT-blue.svg)](LICENSE)
[![Progress](https://img.shields.io/badge/progress-5%25-yellow)](README.md)

**FlowStore** é um sistema de e-commerce desenvolvido com uma arquitetura de microserviços utilizando Java 21 e Spring Boot. O projeto demonstra boas práticas de desenvolvimento backend, como comunicação assíncrona via Apache Kafka, segurança com JWT, persistência em bancos PostgreSQL isolados, e orquestração com Docker. Organizado como um projeto Maven multi-módulo, o FlowStore é modular, escalável e segue princípios de DevOps e metodologias ágeis.
O objetivo é simular um ambiente de e-commerce real, com serviços independentes para autenticação, gerenciamento de produtos, pedidos, pagamentos, estoque e notificações, proporcionando uma base robusta para aprendizado e demonstração de habilidades técnicas.

---

## 🚀 Funcionalidades

Autenticação e Autorização: Cadastro e login de usuários com JWT e controle de acesso baseado em papéis (RBAC).
Gerenciamento de Produtos: Cadastro, atualização e consulta de produtos no catálogo.
Processamento de Pedidos: Criação e acompanhamento de pedidos com integração assíncrona.
Gestão de Estoque: Atualização de estoque em tempo real via eventos Kafka.
Notificações: Envio de alertas por e-mail ou SMS sobre status de pedidos.
Documentação: APIs documentadas com Swagger/OpenAPI para facilitar integração.

## 🛠️ Tecnologias Utilizadas


|                   🚀 Tecnologias                    | 🚀 + Padrões Utilizados                       |
|-----------------------------------------------------|-----------------------------------------------|
| ✅ **Linguagem: Java 21+**                          | ✅ **Lombok**                                 |
| ✅ **Framework: Spring Boot 3.5.x**                 | ✅ **Bean Validation**                        |
| ✅ **Camadas: `Controller → Service → Repository`** | ✅ **Actuador**                               |
| ✅ **Spring Security (JWT)**                        | ✅ **Cors Configuration**                     |
| ✅ **Spring Data JPA**                              | ✅ **Postman para testes**                    |
| ✅ **PostgreSQL (com Docker)**                      | ✅ **Padronização de logs com SLF4J**         |
| ✅ **Docker + Docker Compose**                      | ✅ **Maven (multi-módulo)**                   |
| ✅ **DTO com `record`**                             | ✅ **Apache Kafka**                           |
| ✅ **MapStruct para conversões**                    | ✅ **Swagger SpringDoc OpenAPI + Swagger UI** |

---

## 🔐 Autenticação e Autorização

A autenticação é centralizada no serviço primestore-auth, utilizando Spring Security com JWT e RBAC. As roles disponíveis são:

ROLE_USER: Clientes do e-commerce.
ROLE_ADMIN: Administradores do sistema.
ROLE_SELLER: Vendedores ou gerentes de estoque.

Fluxo de autenticação:

sequenceDiagram
participant Usuário
participant AuthService
participant OtherService
Usuário->>AuthService: POST /auth/signin (credenciais: username, password)
AuthService-->>Usuário: Retorna JWT (access_token)
Usuário->>OtherService: Requisição autenticada (Authorization: Bearer <token>)
OtherService-->>Usuário: Resposta com dados protegidos

---


📂 Estrutura do Projeto
O projeto é organizado em módulos Maven para maior modularidade e manutenção:

```bash
    primestore/
        ├── primestore-auth/                    # Serviço de autenticação e autorização (JWT)
        ├── primestore-catalog/                 # Vitrine (produtos, categorias)
        ├── primestore-order/                   # Núcleo da venda (Order, OrderPedido)
        ├── primestore-pay/                     # Processamento de pagamentos
        ├── primestore-stock/                   # Gerenciamento de estoque
        ├── primestore-notifier/                # Notificações (e-mail, SMS)
        ├── common-events/                      # Biblioteca compartilhada (DTOs, eventos Kafka)
        ├── docker-compose.yml                  # Configuração de Docker para PostgreSQL e Kafka
        └── pom.xml                             # Configuração Maven multi-módulo
```

📚 Documentação da API

Acesse a documentação de cada microserviço via Swagger UI:

Auth Service: http://localhost:8080/swagger-ui/index.html

Core Service: http://localhost:8081/swagger-ui/index.html

Versão da API: v1.0.0

Ferramenta: SpringDoc OpenAPI

---

## 🏗️ Melhorias Futuras

