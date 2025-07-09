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
