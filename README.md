```markdown
# PessoaDataDogServiceApp

## Descrição

Este é um projeto de prova de conceito (POC) para estudo de microsserviço integrado com o DataDog. O serviço permite gerenciar entidades de "Pessoa" (Pessoa), oferecendo operações CRUD básicas. Utiliza Spring Boot, JPA com H2 como banco de dados em memória, e integrações com DataDog para monitoramento, logs e métricas.

## Tecnologias Utilizadas

- **Java 21**
- **Spring Boot 3.0.5**
- **Spring Data JPA**
- **H2 Database** (em memória, simulando PostgreSQL)
- **Lombok** (para reduzir boilerplate)
- **Spring Boot Actuator** (para endpoints de monitoramento)
- **Micrometer Registry DataDog** (para exportação de métricas)
- **Logstash Logback Encoder** (para logs estruturados em JSON)
- **DataDog Trace API** (para tracing)
- **Docker** (para containerização)
- **Maven** (gerenciamento de dependências e build)

## Pré-requisitos

- Java 21 instalado
- Maven 3.6+ instalado
- Docker e Docker Compose (opcional, para execução em containers)

## Estrutura do Projeto

```
src/
├── main/
│   ├── java/br/com/rvm/poc/pessoaDataDogServiceApp/
│   │   ├── PessoaDataDogServiceAppApplication.java  # Classe principal
│   │   ├── controller/
│   │   │   └── PessoaController.java                # Endpoints REST
│   │   ├── model/
│   │   │   └── Pessoa.java                          # Entidade Pessoa
│   │   ├── repository/
│   │   │   └── PessoaRepository.java                # Repositório JPA
│   │   └── service/
│   │       └── PessoaService.java                   # Lógica de negócio
│   └── resources/
│       ├── application.yaml                          # Configuração padrão
│       └── application-docker.yml                    # Configuração para Docker
└── test/
└── java/br/com/rvm/poc/pessoaDataDogServiceApp/
└── PessoaDataDogServiceAppApplicationTests.java  # Testes básicos
```

## Como Executar Localmente

1. Clone o repositório e navegue até a pasta do projeto.

2. Execute o comando para compilar e executar:
   ```
mvn spring-boot:run
   ```

3. A aplicação estará disponível em `http://localhost:8080`.

4. Console H2: Acesse `http://localhost:8080/h2-console` com as credenciais:
   - JDBC URL: `jdbc:h2:mem:pessoa-db;DB_CLOSE_DELAY=-1;MODE=PostgreSQL`
   - Username: `sa`
   - Password: (vazio)

## Como Executar com Docker

1. Certifique-se de que o arquivo `dd-java-agent.jar` esteja na raiz do projeto (baixado do DataDog).

2. Execute o Docker Compose:
   ```
docker-compose up --build
   ```

3. A aplicação estará disponível em `http://localhost:8080`.

4. O DataDog Agent será iniciado em um container separado para coletar métricas, logs e traces.

## Endpoints da API

A API oferece os seguintes endpoints para gerenciar pessoas:

- **POST /pessoas**: Criar uma nova pessoa.
  - Corpo: JSON com `cpf`, `nome`, `email`.

- **PUT /pessoas/{cpf}**: Atualizar uma pessoa existente.
  - Corpo: JSON com `nome`, `email`.

- **DELETE /pessoas/{cpf}**: Deletar uma pessoa pelo CPF.

- **GET /pessoas/{cpf}**: Buscar uma pessoa pelo CPF.

- **GET /pessoas**: Listar todas as pessoas.

Exemplo de JSON para Pessoa:
```json
{
  "cpf": "12345678900",
  "nome": "João Silva",
  "email": "joao.silva@example.com"
}
```

## Configuração

- **application.yaml**: Configuração padrão para execução local.
- **application-docker.yml**: Configuração para execução em Docker (DataDog desabilitado por padrão).

Para habilitar a exportação de métricas para DataDog, ajuste as propriedades em `application.yaml`:
```yaml
management:
  metrics:
    export:
      datadog:
        enabled: true
  datadog:
    metrics:
      export:
        enabled: true
```

## Monitoramento com DataDog

- **Tracing**: Habilitado via Java Agent.
- **Métricas**: Exportadas via Micrometer.
- **Logs**: Estruturados em JSON para integração com DataDog Logs.

Certifique-se de configurar a chave da API do DataDog nas variáveis de ambiente ou no `docker-compose.yml`.

## Build e Testes

- Para compilar: `mvn clean compile`
- Para executar testes: `mvn test`
- Para empacotar: `mvn clean package`

## Contribuição

Este é um projeto de estudo. Sinta-se à vontade para contribuir com melhorias ou correções.

## Licença

Este projeto é para fins de prova de conceito (POC) para estudo e não possui licença específica.

Readme criado por [RVM], utilizando o https://mdeditor.net/pt/?utm_source=chatgpt.com#app
```