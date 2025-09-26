# Descrição

O objetivo deste sistema é o desenvolvimento de uma **API REST** para gerenciar o cadastro, consulta e controle de **remédios, clientes e pedidos**.  
A aplicação visa oferecer uma solução eficiente para o gerenciamento de farmácias, clínicas e plataformas online de medicamentos, automatizando processos como:

- Cadastro de remédios  
- Registro de clientes  
- Controle de vendas e pedidos  
- Autenticação de usuários  

A API permitirá que farmácias e plataformas online gerenciem seu catálogo de medicamentos, associando informações como **nome, categoria, fabricante e preço**, além de possibilitar a realização de pedidos por clientes.  

Cada pedido incluirá um ou mais itens de remédios, com a quantidade e o preço unitário, permitindo um controle eficiente das transações.  

Além disso, a API será responsável pela **autenticação de usuários**, com papéis:  

- **ADMIN**: pode criar e gerenciar usuários, clientes e remédios.  
- **Atendente**: pode apenas gerenciar pedidos e clientes.  

A solução fornecerá um controle completo de remédios e pedidos, permitindo também a **consulta de dados sobre remédios cadastrados, clientes e pedidos realizados**, o que pode ser integrado com sistemas gráficos (web ou mobile) ou utilizados como base para futuras funcionalidades.  

---

## Entidades

- **Usuários**
- **Clientes**
- **Remédios**
- **Pedidos**

---

## Definição inicial das rotas

### Usuários
- `GET /users` – Lista todos os usuários cadastrados (**somente para ADMIN**)  
- `POST /users` – Cria um novo usuário (**somente para ADMIN**)  
- `GET /users/{id}` – Retorna os detalhes de um usuário específico  
- `PUT /users/{id}` – Atualiza os dados de um usuário específico  
- `DELETE /users/{id}` – Exclui ou desativa um usuário (**somente para ADMIN**)  

### Clientes
- `GET /customers` – Lista todos os clientes cadastrados  
- `POST /customers` – Cria um novo cliente  
- `GET /customers/{id}` – Retorna os detalhes de um cliente específico  
- `PUT /customers/{id}` – Atualiza as informações de um cliente  
- `DELETE /customers/{id}` – Exclui ou desativa um cliente  

### Remédios
- `GET /medicines` – Lista todos os remédios cadastrados  
- `POST /medicines` – Cria um novo remédio  
- `GET /medicines/{id}` – Retorna os detalhes de um remédio específico  
- `PUT /medicines/{id}` – Atualiza os dados de um remédio  
- `DELETE /medicines/{id}` – Exclui ou desativa um remédio  

### Pedidos
- `GET /orders` – Lista todos os pedidos realizados  
- `POST /orders` – Cria um novo pedido, incluindo remédios e quantidades  
- `GET /orders/{id}` – Retorna os detalhes de um pedido específico  
- `PUT /orders/{id}` – Atualiza o status de um pedido (ex: **confirmado, pago, cancelado**)  
- `DELETE /orders/{id}` – Exclui um pedido  

### Autenticação
- `POST /auth/login` – Realiza o login do usuário e retorna o token para autenticação  
- `GET /auth/me` – Retorna os dados do usuário autenticado  
