# O Repositório Mudou! (Este está um Pouco Poeirento) 🧹
Olá! Se você chegou até aqui, é porque está de olho em algo que já foi superado por uma versão mais nova e brilhante.

Peço mil desculpas pela poeira e teias de aranha, mas este repositório está oficialmente aposentado e desatualizado.
E atualizarei o link do currículo, (caso tenha vindo por lá).
---
👉 A versão principal e ativa deste projeto foi movida para o novo endereço:

[Novo Repositório Principal (Clique Aqui para o Futuro!)](https://github.com/Gammon64/AsheOs)
Agradeço a compreensão! Por favor, use e contribua apenas no novo repositório. Vemo-nos por lá! 👋

___
# Boas vindas ao Ocorrências API!

O projeto consiste em uma API REST para o cadastro de ocorrências, clientes e endereços em um banco PostgreSQL, com a
possibilidade de upload de fotos em um servidor de arquivos S3.

## Como rodar e testar o projeto

### Pré-requisitos

- Java SDK
- Maven
- Docker
- Docker Compose

### Como rodar o projeto

Em um terminal, acesse a pasta do projeto e execute o comando:

```shell
docker compose up -d
```

O comando irá subir o banco de dados PostgreSQL, o servidor de arquivos S3 e o servidor Spring.

### Como acessar a documentação da API

O projeto foi documentado com o Swagger, para acessar a documentação da API, basta
acessar o [Swagger UI](http://localhost:8080/swagger-ui/index.html).

Para enviar requisições, é necessário autenticar-se, para isso, envie uma requisição POST para o endpoint
`/usuario/cadastrar` com o corpo da requisição contendo o login e senha do usuário:

```shell
curl -X 'POST' \
'http://localhost:8080/usuario/cadastrar' \
-H 'accept: */*' \
-H 'Content-Type: application/json' \
-d '{
"login": "admin",
"senha": "admin"
}'
```

ou pelo Swagger UI:
usuario-controller -> POST /usuario/cadastrar -> Try it out

```json
{
  "login": "admin",
  "senha": "admin"
}
```

Após criado o usuário, autentique-se no endpoint `/login` e copie o token gerado.

```shell
curl -X 'POST' \
  'http://localhost:8080/usuario/autenticar' \
  -H 'accept: */*' \
  -H 'Content-Type: application/json' \
  -d '{
  "login": "admin",
  "senha": "admin"
}'
```

ou pelo Swagger UI:
usuario-controller -> POST /usuario/cadastrar -> Try it out

```json
{
  "login": "admin",
  "senha": "admin"
}
```

Com o token em mãos, adicione-o como parametro de autorização 'Bearer token' no cabeçalho das requisições. Pelo Swagger
UI, clique no botão 'Authorize' e insira o token no campo 'Value'.

## Divirta-se 😁

---

## Cronograma de desenvolvimento do projeto:

Decidi adotar essa ordem de prioridade de acordo com o meu conhecimento, assim, agilizando o desenvolvimento do projeto
para um ponto funcional e dando tempo de estudar novos conceitos.

### Parte 1

- **[OK]** Planejar e esquematizar o projeto.
- **[OK]** Criação do projeto.
- **[OK]** Configuração dos ambientes Docker, banco de dados e servidor de arquivos.
- **[OK]** Configuração do Flyway.

### Parte 2

- **[OK]** Configuração do CRUD para Cliente.
- **[OK]** Configuração do CRUD para Endereço.

### Parte 3

- Configuração do CRUD para Ocorrências.
- Testes unitários para Ocorrências.
- Serviço para upload e download de Foto Ocorrência.

### Parte 4

- **[OK]** Configurar autenticação.

### Parte 5

- **[OK]** Configuração do CRUD para Ocorrências.
- **[OK]** Serviço para upload e download de Foto Ocorrência.
- **[OK]** Endpoint para filtrar ocorrências.
- **[OK]** Testes unitários para Cliente.
- **[OK]** Testes unitários para Endereço.
- **[OK]** Configurar Compose para subir o Spring.
- **[OK]** Ajustes finais e documentação.
- **[OK]** Subir o projeto para o BitBucket.
