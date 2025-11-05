# Escola Backend - API Java

Projeto Java para gerenciamento de alunos, turmas e usuários, com autenticação JWT.  

Tecnologias: Java 17+, Jakarta EE (JPA, REST), Hibernate, MySQL, Jersey, Jetty.

---

## Configuração do Banco

1. Crie o banco no MySQL:

\`\`\`sql
CREATE DATABASE escola;
\`\`\`

2. Ajuste \`persistence.xml\` com usuário e senha.

3. Tabelas serão criadas automaticamente (\`hibernate.hbm2ddl.auto=update\`).

---

## Estrutura do Projeto

\`\`\`
src/main/java
├─ entity   	-> Entidades JPA (Aluno, Turma, Usuario)
├─ dao      	-> Persistência (AlunoDao, TurmaDao, UsuarioDao)
├─ service  	-> Regras de negócio
├─ config   	-> Configuração JAX-RS (JaxRsApplication)
└─ controller  -> Endpoints

\`\`\`

---

## Execução

\`\`\`bash
mvn clean install
java -jar target/backend.jar
\`\`\`

API disponível em: \`http://localhost:8080/api/`

---

## Endpoints CRUD

### Usuario

**Exemplos via curl:**

**Incluir/Atualizar**

curl -X POST http://localhost:8080/api/usuario/salvar \\  
     -H "Content-Type: application/json" \\  
     -d '{"email": "teste@teste.com", "nome":"xxx", "senha":"12345"}'

**login**

curl -X POST http://localhost:8080/api/usuario/login \\  
     -H "Content-Type: application/json" \\  
     -d '{"email": "teste@teste.com", "senha":"12345"}'

### Turma

**Exemplos via curl:**

**Incluir**

curl -X POST http://localhost:8080/api/turma/salvar \\  
     -H "Authorization: Bearer \<token-aqui\>" \\  
     -H "Content-Type: application/json" \\  
     -d '{"descricao": "2025.1N"}'

**Atualizar**

curl -X POST http://localhost:8080/api/turma/salvar \\  
     -H "Authorization: Bearer \<token-aqui\>" \\  
     -H "Content-Type: application/json" \\  
     -d '{"id": 1, "descricao": "2025.2N"}'

**Consultar**

curl -X GET http://localhost:8080/api/turma/1 \\  
     -H "Authorization: Bearer \<token-aqui\>"

---

### Aluno

> Senhas são armazenadas como hash SHA-256.

**Incluir**

curl -X POST http://localhost:8080/api/aluno/salvar \
     -H "Authorization: Bearer \<token-aqui\>" \
     -H "Content-Type: application/json" \
     -d '{"nome": "Zezinho", "turma" : {"id":1}}'

**Atualizar**

curl -X POST http://localhost:8080/api/aluno/salvar \
     -H "Authorization: Bearer \<token-aqui\>" \
     -H "Content-Type: application/json" \
     -d '{"id": 1, "nome": "Maria", "turma" : {"id":1}}'

**Consultar**

curl -X GET http://localhost:8080/api/aluno/1 \
     -H "Authorization: Bearer \<token-aqui\>"

---

## Exemplos de JSON de Entidades

**Aluno:**

\`\`\`json
{
  "id": 1,
  "nome": "Maria",
  "turma": {
    "id": 1,
    "descricao": "Turma de Java"
  }
}
\`\`\`

**Turma:**

\`\`\`json
{
  "id": 1,
  "descricao": "Turma de Java"
}
\`\`\`

**Usuário:**

\`\`\`json
{
  "email": "user@ex.com",
  "nome": "João",
  "senha": "hashSHA256aqui"
}
\`\`\`

---

## Testando a API

- Use \`curl\`, Postman ou Insomnia.
- Inclua \`Authorization: Bearer <TOKEN>\` para endpoints protegidos.
- Respostas em JSON.

---

## Autor

Reinaldo José de Freitas 
reinaldo.freitas@rj.senac.br

---

## Licença

MIT License
EOL
