# 🏋️ Spring Boot Essentials Security

API REST para gerenciamento de alunos e avaliações físicas, com autenticação segura via JWT e controle de acesso com Spring Security.

---

## 📋 Sobre o Projeto

Sistema backend desenvolvido com foco em boas práticas de desenvolvimento, oferecendo endpoints para cadastro de usuários, autenticação e gerenciamento de avaliações físicas de alunos.

---

## 🚀 Tecnologias Utilizadas

- **Java 17+**
- **Spring Boot**
- **Spring Security** — autenticação e autorização
- **JWT (JSON Web Token)** — geração e validação de tokens
- **Spring Data JPA** — persistência de dados
- **MySQL** — banco de dados relacional
- **Lombok** — redução de boilerplate

---

## 📌 Endpoints Principais

### Auth
| Método | Endpoint | Descrição |
|--------|----------|-----------|
| POST | `/v1/auth/register` | Cadastro de usuário |
| POST | `/v1/auth/login` | Login e geração do token JWT |

### Avaliações Físicas
| Método | Endpoint | Descrição |
|--------|----------|-----------|
| POST | `/v1/avaliacoes` | Criar avaliação física |
| GET | `/v1/avaliacoes` | Listar todas as avaliações |
| GET | `/v1/avaliacoes/page/{page}/size/{size}` | Listar com paginação |

---

## ⚙️ Como Rodar Localmente

### Pré-requisitos
- Java 17+
- MySQL rodando localmente
- Maven

### Passos

1. Clone o repositório:
```bash
git clone https://github.com/jonasPereira533/spring-boot-security.git
```

2. Configure o banco de dados no `application.properties`:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/seu_banco
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha
```

3. Rode a aplicação:
```bash
./mvnw spring-boot:run
```

4. Acesse em: `http://localhost:8080`

---

## 🔐 Autenticação

A API utiliza JWT. Para acessar endpoints protegidos:

1. Faça login em `/v1/auth/login` e copie o token retornado
2. Nas requisições, adicione o header:
```
Authorization: Bearer {seu_token}
```

---

## 👨‍💻 Autor

**Jonas Pereira**  
[GitHub](https://github.com/jonasPereira533)
