📋 Descrição do Projeto

Este projeto implementa um sistema de fórum onde usuários podem criar tópicos de discussão relacionados a cursos específicos. Desenvolvido como desafio do programa Oracle Next Education, a API segue rigorosamente as regras de negócio apresentadas pelo instrutor Eric Monné da Alura.

🎯 Funcionalidades Implementadas

🔐 Sistema de Autenticação
Registro de usuários com senhas criptografadas

Login com JWT (JSON Web Tokens)

Tokens Bearer para autenticação stateless

Proteção de endpoints sensíveis

💬 Gestão de Tópicos

Criação de tópicos (apenas usuários autenticados)

Listagem de todos os tópicos

Relação tópico-usuário-curso

Validação de cursos existentes

👥 Gestão de Usuários

Sistema de permissoes baseado em autenticação

Cada usuário gerencia apenas seus próprios conteúdos

Dados protegidos e criptografados

🛣️ Endpoints da API

Autenticação
Método	Endpoint	Descrição	Autenticação
POST	/auth/registro	Registrar novo usuário	❌
POST	/auth	Fazer login	❌
Tópicos
Método	Endpoint	Descrição	Autenticação
GET	/topicos	Listar todos os tópicos	✅
POST	/topicos	Criar novo tópico	✅
🚀 Tecnologias Utilizadas
Java 21

Spring Boot 3.5.4

Spring Security - Autenticação e autorização

JWT - Tokens de autenticação

Spring Data JPA - Persistência de dados

H2 Database - Banco em memória para desenvolvimento

Maven - Gerenciamento de dependências

BCrypt - Criptografia de senhas

📦 Estrutura do Projeto

text
src/
├── main/
│   ├── java/com/example/demo/
│   │   ├── controller/           # Controladores REST
│   │   │   ├── AuthController.java
│   │   │   └── TopicoController.java
│   │   ├── model/               # Entidades JPA
│   │   │   ├── Usuario.java
│   │   │   ├── Topico.java
│   │   │   └── Curso.java
│   │   ├── repository/          # Interfaces de repositório
│   │   │   ├── UsuarioRepository.java
│   │   │   ├── TopicoRepository.java
│   │   │   └── CursoRepository.java
│   │   ├── security/            # Configurações de segurança
│   │   │   ├── SecurityConfig.java
│   │   │   ├── TokenService.java
│   │   │   └── TokenFilter.java
│   │   ├── dto/                 # Objetos de transferência de dados
│   │   │   ├── LoginDTO.java
│   │   │   ├── TokenDTO.java
│   │   │   ├── TopicoDTO.java
│   │   │   └── TopicoForm.java
│   │   └── DemoApplication.java # Classe principal
│   └── resources/
│       ├── application.properties
│       └── import.sql           # Dados iniciais
🛠️ Como Executar

Pré-requisitos
Java 21 ou superior

Maven 3.6+

IDE de sua preferência (IntelliJ, Eclipse, VS Code)

🔧 Configuração
application.properties
properties
# Banco de dados H2
spring.h2.console.enabled=true
spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.username=sa
spring.datasource.password=

# JWT
forum.jwt.secret=minhaChaveSuperSecretaComPeloMenos256Bits123!
forum.jwt.expiration=86400000

# JPA
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
🧪 Testando a API
1. Registrar usuário
http
POST http://localhost:8080/auth/registro
Content-Type: application/json

{
  "nome": "João Silva",
  "email": "joao@email.com",
  "senha": "123456"
}
2. Fazer login
http
POST http://localhost:8080/auth
Content-Type: application/json

{
  "email": "joao@email.com",
  "senha": "123456"
}
3. Criar tópico (com token)
http
POST http://localhost:8080/topicos
Content-Type: application/json
Authorization: Bearer seu-token-jwt-aqui

{
  "titulo": "Dúvida sobre Spring Boot",
  "mensagem": "Como configurar JWT corretamente?",
  "nomeCurso": "Java"
}
4. Listar tópicos
http
GET http://localhost:8080/topicos
Authorization: Bearer seu-token-jwt-aqui

🛡️ Segurança
HTTPS pronto para produção

Senhas criptografadas com BCrypt

Tokens JWT com expiração configurável

CORS configurado

Validação de entrada de dados

Proteção contra SQL Injection

Autenticação obrigatória para endpoints sensíveis

📋 Regras de Negócio Implementadas
✅ Apenas usuários autenticados podem criar tópicos
✅ Cada tópico pertence a um usuário específico
✅ Cada tópico está associado a um curso
✅ Data de criação automática (não informada pelo usuário)
✅ Validação de cursos existentes no banco de dados
✅ Proteção contra operações não autorizadas

🚀 Próximas Melhorias
Implementar PUT e DELETE para tópicos

Buscar tópico por ID

Sistema de respostas para tópicos

Paginação de resultados

Documentação com Swagger

Testes automatizados

Deploy em nuvem
