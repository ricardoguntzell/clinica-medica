# Clínica Médica - API

# Sobre o projeto

[//]: # ([API em produção no Railway]&#40;https://transito-api-production.up.railway.app "App em produção no Railway"&#41;)

Clínica Médica é uma aplicação back-end **em tempo de desenvolvimento**.

A aplicação consiste na exposição de uma API REST,
que possibilita toda a gestão de agendamentos de consultas e cadastro de médicos e pacientes.
Os dados são coletados inicialmente pela própria API.

- Cadastro de Pacientes e Médicos
- Listagem de Pacientes e Médicos
- Busca de Pacientes e Médicos
- Ativação/Inativação de Pacientes e Médicos
- Agendamento e gestão de Consultas

[//]: # (## Modelo de domínio Transito API)

[//]: # (<img src="src/main/resources/img/transito-modelo-dominio.png" alt="Modelo de Dominio">)

# Tecnologias utilizadas

## Back end

- Java 17
- Spring Boot
- JPA / Hibernate / Security / JWT / Lombok / Flyway
- Maven
- MySql

[//]: # (## Implantação em produção)

[//]: # ()
[//]: # (- Back end: Railway)

[//]: # (- Banco de dados: MySql)

# Como executar o projeto

## Back end

Pré-requisitos: Java 17

```bash
# clonar repositório
git clone https://github.com/ricardoguntzell/clinica-medica

# entrar na pasta do projeto raiz

# executar o projeto
./mvnw spring-boot:run
```

[//]: # (# Testes)

[//]: # ()
[//]: # (- Foi disponibilizado na folder "/postman-collections" os payloads para testar)

[//]: # (- Nota: Lembre-se de alterar a URL para seu ambiente local&#40;Ex: http://localhost:8080&#41;. Também vou deixar no ar o projeto em produção caso você queira testar a partir dele.)

[//]: # (  &#40;https://transito-api-production.up.railway.app&#41;)

# Autor

Ricardo Guntzell

- www.linkedin.com/in/ricardoguntzelljr
- https://github.com/ricardoguntzell
