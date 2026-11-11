# EstoquePro API — Projeto 2/9 do portfólio Java

Sistema de estoque em **Java 21 + Spring Boot 3.5.7**, com API REST, PostgreSQL, Flyway, validação, testes e CI. Voltado à demonstração de habilidades para vagas Java júnior/estágio.

## Funcionalidades
- Cadastro de produtos com SKU único, nome, preço e estoque mínimo
- Consulta e paginação de produtos
- Entrada e saída de mercadorias com histórico e motivo
- Bloqueio de saída com saldo insuficiente
- Consulta de produtos com estoque baixo
- Controle de concorrência otimista com `@Version`
- Transações de banco com `@Transactional`

## Executar
Pré-requisitos: **JDK 21+**, Maven e Docker Compose.

```bash
docker compose up -d
mvn clean test
mvn spring-boot:run
```

A API inicia em `http://localhost:8080` e o PostgreSQL local na porta **5433**. Credenciais padrão são **somente para desenvolvimento local**. Para personalizar, use `DB_URL`, `DB_USER` e `DB_PASSWORD`.

## Endpoints
| Método | Rota | Uso |
| --- | --- | --- |
| POST | `/api/v1/products` | Cadastrar produto |
| GET | `/api/v1/products` | Listar com paginação |
| GET | `/api/v1/products/{id}` | Detalhes |
| GET | `/api/v1/products/low-stock` | Estoque baixo |
| POST | `/api/v1/products/{id}/movements` | Entrada ou saída |
| GET | `/api/v1/products/{id}/movements` | Histórico paginado |

### Exemplos
```bash
curl -X POST http://localhost:8080/api/v1/products -H 'Content-Type: application/json' -d '{"sku":"MOU-001","name":"Mouse sem fio","price":79.90,"minStock":5}'
curl -X POST http://localhost:8080/api/v1/products/1/movements -H 'Content-Type: application/json' -d '{"type":"ENTRY","quantity":20,"reason":"Compra do fornecedor"}'
curl -X POST http://localhost:8080/api/v1/products/1/movements -H 'Content-Type: application/json' -d '{"type":"EXIT","quantity":3,"reason":"Venda"}'
```

## Arquitetura
- `domain`: entidades e regras de saldo
- `repository`: interfaces Spring Data JPA
- `service`: transações e casos de uso
- `api`: endpoints, DTOs e tratamento de erros
- `resources/db/migration`: SQL versionado com Flyway
- `test`: testes de domínio e serviço com JUnit e Mockito
- `.github/workflows`: testes Maven em cada push e pull request

## Pontos de atenção
A validação do GitHub Actions executa testes unitários, **não testes integrados com PostgreSQL**. Em concorrência, o bloqueio otimista evita atualizações perdidas, mas o cliente deve tratar `409 Conflict` e tentar novamente quando apropriado. A consulta de estoque baixo carrega produtos em memória: em produção, substituir por consulta SQL paginada. Não há autenticação, autorização ou deploy de produção.

## Próximos passos
Testcontainers, Swagger/OpenAPI, autenticação, filtros avançados, categorias e paginação de estoque baixo.

> Projeto educacional; não use como solução de estoque de produção sem revisões adicionais de segurança, desempenho e testes.
