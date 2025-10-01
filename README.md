# Guia de Execução do Projeto

Este guia fornece os passos necessários para rodar a aplicação "gestao_de_remedios" localmente.

---

## Pré-requisitos

Certifique-se de que você tem o seguinte instalado na sua máquina:

-   [Docker](https://www.docker.com/products/docker-desktop) e [Docker Compose](https://docs.docker.com/compose/install/)
-   [Java Development Kit (JDK) 17](https://www.oracle.com/java/technologies/downloads/#jdk17) ou superior
-   Maven Wrapper (`mvnw` e `mvnw.cmd`), que já estão incluídos no projeto.

---

## Passos para Executar a Aplicação

### Passo 1: Iniciar o Banco de Dados

A aplicação depende de um banco de dados PostgreSQL. O arquivo `docker-compose.yaml` facilita a configuração e execução do banco de dados em um contêiner Docker.

Abra o terminal na raiz do projeto e execute o seguinte comando para iniciar o serviço do PostgreSQL e o pgAdmin:

```bash
docker-compose up -d

Aguarde alguns segundos para que os contêineres sejam iniciados e o banco de dados esteja pronto. Você pode verificar o status com o comando docker-compose ps.
Passo 2: Construir o Projeto
Utilize o Maven Wrapper (mvnw) para construir o projeto, o que garantirá que todas as dependências sejam baixadas e o pacote executável seja criado.
Execute o seguinte comando no terminal, na raiz do projeto:
# Para Linux/macOS
./mvnw clean install

# Para Windows
mvnw.cmd clean install

Este comando criará um arquivo JAR executável no diretório target/.
Passo 3: Configurar o Banco de Dados (Opcional)
A aplicação está configurada para se conectar a um banco de dados local com as seguintes credenciais, conforme o arquivo application.properties:
 * URL: jdbc:postgresql://localhost:5432/remediosdb
 * Usuário: postgres
 * Senha: postgres
O docker-compose.yaml já configura o PostgreSQL com estas credenciais, portanto este passo não é necessário, a menos que você queira usar um banco de dados diferente.
Passo 4: Executar a Aplicação
Depois que o banco de dados estiver em execução e o projeto estiver construído, você pode iniciar a aplicação Spring Boot.
Execute o seguinte comando no terminal, na raiz do projeto:
# Para Linux/macOS
./mvnw spring-boot:run

# Para Windows
mvnw.cmd spring-boot:run

A aplicação será iniciada e estará disponível na porta 8080, conforme especificado no arquivo application.properties.
Importante: Assim que a API é iniciada, ela cria automaticamente um usuário administrador. Isso é feito para garantir que existam credenciais válidas para o login na primeira utilização, já que a lógica do sistema permite que apenas usuários com permissões restritas recebam credenciais de acesso prontas.
As credenciais do usuário admin são:
 * Email: admin@remedios.com
 * Senha: admin123
Passo 5: Testar a API
A API agora está em execução. Você pode interagir com ela usando um cliente HTTP como o Postman, Insomnia ou cURL.
Aqui estão algumas rotas de exemplo, conforme documentado no README.md:
 * Login: POST /api/login
 * Usuários: GET /users (requer autenticação)
 * Clientes: POST /customers
 * Remédios: GET /medicines
Você pode acessar a documentação do Swagger UI em http://localhost:8080/swagger-ui.html para ver todos os endpoints disponíveis.

