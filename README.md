Sistema CRUD de Usuários e Produtos

Projeto desenvolvido com o objetivo de praticar o desenvolvimento de aplicações web utilizando **Java + Spring Boot**, aplicando arquitetura MVC e boas práticas como DTO, Service, Repository e Mapper.

Objetivo
Este sistema foi criado para treinar conceitos fundamentais do desenvolvimento back-end com Spring Boot, incluindo:


Tecnologias spring initializr
- Java 21
- Maven
- Spring Boot DevTools
- Spring Data JPA
- Thymeleaf
- MySQL Driver
- Spring Web
- Validation
  
- Intellij
- Bootstrap
- Git & GitHub
- MySQL Workbench 8.0 CE
- Xampp

    


  Arquitetura do Projeto
- Controller - Responsável pelas requisições HTTP e respostas
- Service - Contém as regras de negócio da aplicação
- Repository - Comunicação com o banco de dados (Spring Data JPA)
- Entity - Representação das tabelas do banco de dados
- DTO- Transporte seguro de dados entre camadas
- Mapper - Conversão entre Entity e DTO
- ControllerAdvice - validação personalizadas

  Funcionalidades
- Cadastro de usuário
- Listagem de usuários
- Atualização de dados
- Exclusão de usuário
- Login de usuário

  Produtos
- Cadastro de produtos
- Listagem de produtos
- Atualização de produtos
- Exclusão de produtos

  Documentação
A documentação completa do projeto está disponível no repositório:
-docs/Documentacao.docx`

Fluxo da aplicação
1. Usuário acessa a aplicação
2. Controller recebe a requisição HTTP
3. Service processa a lógica de negócio
4. Repository acessa o banco de dados
5. Mapper converte Entity ↔ DTO
6. Controller retorna a resposta para a View (Thymeleaf)

Aprendizados
Durante o desenvolvimento deste projeto foram praticados:
- Desenvolvimento de API com Spring Boot
- Padrão MVC
- CRUD completo
- Uso de DTO e Mapper
- Integração com banco de dados
- Organização de código em camadas
- Versionamento com Git e GitHub

Autor
Rhudsonn Oliveira

  
