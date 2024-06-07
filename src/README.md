# App StockAgil
### Entrega GS

## Descrição
Esta entrega da GS é uma aplicação para conscientizar as pessoas a preservar os rios es mares para não poluirem e prejudicar a vida marinha.
### - Continuação do Desenvolvimento

## Equipe
- Jose Carnauba RM552367 | Eduardo Junio RM552374 | Danielly Pfander RM552391

## Distribução de Atividades

- Jose Carnaúba: Implementação das entidades JPA
- Eduardo Junio: Configuração do banco de dados e repositórios
- Jose Carnauba: Desenvolvimento dos endpoints REST
- Danielly Pfander: Documentação e testes

## Instruções para Rodar a Aplicação
1. Clone o repositório.
2. Configure o banco de dados no arquivo `application.properties`.
3. Rode o comando `GsApplication`.

## Endpoints Entrega Sprint2
- `POST /api/produto`: Cria um novo produto.
- `GET /api/produto/{id}`: Retorna um produto pelo ID.
- `GET /api/produto`: Retorna todos os produtos.
- `PUT /api/produto/{id}`: Atualiza um produto pelo ID.
- `DELETE /api/produto/{id}`: Deleta um produto pelo ID.

## Endpoints gs
- `GET /api/inciativas/pesquisar-nome?parteNome={}`: Retorna o nome da iniciativa.
- `GET /api/inciativas/pesquisar-nome?parteNome={}`: Retorna um produto por nome e parte do nome.
- `GET /api/produtos/nome-preco?nome={}&id={}&page=0&size=10`: Retorna um produto por dois parâmetros.
- `GET /api/produtos/por-data?dataInicio={}&dataFim={}`: Retorna por intervalo de datas.
