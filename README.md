# FIAP - Sistema de Reserva e Avaliação de Restaurantes

Este projeto foi elaborado cumprindo os objetivos da FASE 03 Pós Arquitetura e Desenvolvimento Java da FIAP.

## Objetivos

Desenvolver um  Sistema de Reserva e Avaliação de Restaurantes permitindo os seguintes pontos:

- Cadastro de Restaurantes
- Reserva de Mesas
- Avaliações e Comentários
- Busca de Restaurantes
- Gerenciamento de Reservas
  
## Documentação da API

#### Realiza o Cadastro de Restaurante

```http
  POST /restaurantes/criar-restaurante
```
| Parâmetro   | Tipo       | Descrição                           |
| :---------- | :--------- | :---------------------------------- |
| `nome ` | `string` |**Obrigatório**. Deve receber o Nome do Restaurante. Aceita caracteres numéricos e especiais.|
| `endereco ` | `object` | **Obrigatório**. Deve receber o Endereço do Restaurante, contendo os seguintes campos:|
| ↳`cep ` | `string` | **Obrigatório**. Deve receber o CEP do Restaurante respeitando o seguinte modelo: xxxxxx-xxx.|
| ↳`logradouro ` | `string` | **Obrigatório**. Deve receber o Logradouro do Restaurante. |
| ↳`numero ` | `string` | **Obrigatório**. Deve receber o Número do Endereço do Restaurante.|
| ↳`complemento ` | `string` | Deve receber o Complemento do Endereço do Restaurante.|
| ↳`bairro ` | `string` | **Obrigatório**.Deve receber o Bairro do Restaurante.|
| ↳`cidade ` | `string` | **Obrigatório**.Deve receber a Cidade do Restaurante.|
| ↳`uf ` | `string` | **Obrigatório**.Deve receber o UF do Restaurante.|
| `tipoCulinaria ` | `string` | **Obrigatório**.Deve receber o Tipo da Culinária do Restaurante. É um Enum que deve receber algum dos segintes valores: PORTUGUESA, BRASILEIRA, CHINESA, MEXICANA, ESPANHOLA, TAILANDESA, ITALIANA, FRANCESA, JAPONESA.|
| `horarioDeAbertura ` | `string` | **Obrigatório**.Deve receber o Horário de Abertura do Restaurante, respeitando o seguinte modelo: HH:MM ou HH:MM:SS.|
| `horarioDeFechamento ` | `string` | **Obrigatório**.Deve receber o Horário de Fechamento do Restaurante, respeitando o seguinte modelo: HH:MM ou HH:MM:SS.|
| `capacidade ` | `integer` | **Obrigatório**.Deve receber a Capacidade do Restaurante. Aceita apenas valores numéricos.|

![CriarRestaurante1](https://github.com/matheushajer/gerenciador-reservas-restaurante/assets/76411830/67554b13-6764-4ef7-b754-ca81a2cc6048)

![CriarRestaurante2](https://github.com/matheushajer/gerenciador-reservas-restaurante/assets/76411830/d5b44534-5cb9-4a54-946f-517856ae444f)

#### Realiza a Busca de Restaurante Cadastrado Por Nome

```http
  GET /restaurantes
```
| Parâmetro   | Tipo       | Descrição                           |
| :---------- | :--------- | :---------------------------------- |
| `nomeRestaurante` | `string` | Deve receber o nome do Restaurante. Aceita caracteres numéricos e especiais. Não é Case Sensitive. Caso nenhum valor seja informado retorna todos os restaurantes cadastrados.|

![BuscaPorNome](https://github.com/matheushajer/gerenciador-reservas-restaurante/assets/76411830/b77f3548-4afe-45fe-b961-8300ebcd02ef)

#### Realiza a Busca de Restaurante Cadastrado Pela Cidade

```http
  GET /restaurantes/cidade
```

| Parâmetro   | Tipo       | Descrição                                   |
| :---------- | :--------- | :------------------------------------------ |
| `cidadeRestaurante`      | `string` | Deve receber a cidade do Restaurante. Aceita caracteres numéricos e especiais. Não é Case Sensitive. Caso nenhum valor seja informado retorna todos os restaurantes cadastrados.|

![BuscaPorCidade](https://github.com/matheushajer/gerenciador-reservas-restaurante/assets/76411830/01de080f-6efd-4e20-ad8d-4173e0be3aa0)

#### Realiza a Busca de Restaurante Cadastrado Pela Culinária

```http
  GET /restaurantes/culinaria
```

| Parâmetro   | Tipo       | Descrição                                   |
| :---------- | :--------- | :------------------------------------------ |
| `culinariaRestaurante`      | `string` |Deve receber a culinária do Restaurante. Aceita caracteres numéricos e especiais. Não é Case Sensitive. Caso nenhum valor seja informado retorna todos os restaurantes cadastrados.|

![BuscaPorCulinaria](https://github.com/matheushajer/gerenciador-reservas-restaurante/assets/76411830/a776c5c3-b376-4bcc-9cb4-1a05d07c1f8a)

#### Realiza o Cadastro de Cliente

```http
  POST /cliente/criar-cliente
```
| Parâmetro   | Tipo       | Descrição                           |
| :---------- | :--------- | :---------------------------------- |
| `nome  ` | `string` |**Obrigatório**. Deve receber o Nome do Cliente. Aceita caracteres numéricos e especiais.|
| `cpf  ` | `string` |**Obrigatório**. Deve receber o CPF do Cliente. Utilizar apenas valores numéricos.|
| `email  ` | `string` |**Obrigatório**. Deve receber o E-mail do Cliente, respeitando o seguinte modelo: xxxxxx@xxxxx.|
| `endereco ` | `object` | **Obrigatório**. Deve receber o endereço do Cliente. contendo os campos:|
| ↳`cep ` | `string` | **Obrigatório**. Deve receber o CEP do Cliente respeitando o seguinte modelo: xxxxxx-xxx.|
| ↳`logradouro ` | `string` | **Obrigatório**. Deve receber o Logradouro do Cliente. |
| ↳`numero ` | `string` | **Obrigatório**. Deve receber o Número da Residência do Cliente.|
| ↳`complemento ` | `string` | Deve receber o Complemento do Endereço do Cliente.|
| ↳`bairro ` | `string` | **Obrigatório**. Deve receber o Bairro do Cliente.|
| ↳`cidade ` | `string` | **Obrigatório**. Deve receber a Cidade do Cliente.|
| ↳`uf ` | `string` | **Obrigatório**. Deve receber o UF do Cliente.|
| `dadosCriacaoTelefoneDTO ` | `object` | Deve receber o Telefone do Cliente, contendo os seguintes campos:|
| ↳`ddi ` | `number` | Deve receber o Código Internacional do Número de Telefone do Cliente.|
| ↳`ddd ` | `number` | Deve receber o Código de Área do Telefone do Cliente.|
| ↳`telefone ` | `number` | Deve receber o Telefone do Cliente.|

![CriarCliente1](https://github.com/matheushajer/gerenciador-reservas-restaurante/assets/76411830/f23244aa-85dd-4d68-962a-1623052fafb5)
![CriarCliente2](https://github.com/matheushajer/gerenciador-reservas-restaurante/assets/76411830/7e6801fa-2a1f-4df6-be51-99019674cf9a)

#### Realiza a Criação de Reserva

```http
  POST /reservas/criar-reserva
```
| Parâmetro   | Tipo       | Descrição                           |
| :---------- | :--------- | :---------------------------------- |
| `cliente_id  ` | `integer` |**Obrigatório**. Deve receber o ID do Cliente. Aceita apenas valores numéricos.|
| `dataReserva  ` | `string` |**Obrigatório**. Deve receber a Data e Hora da Reserva, respeitando o seguinte modelo "aaaa-mm-ddThh:mm:ss".|
| `restaurante_id  ` | `integer` |**Obrigatório**. Deve receber o ID do Restaurante. Aceita apenas valores numéricos.|

![CriarReserva](https://github.com/matheushajer/gerenciador-reservas-restaurante/assets/76411830/da015e37-4603-4eae-b0cb-ebe499440343)

#### Lista as Reservas de acordo com o ID

```http
  GET /reservas/listar-reservas/{reserva_id}
```
![ListaReserva](https://github.com/matheushajer/gerenciador-reservas-restaurante/assets/76411830/da210431-a9f0-40d6-82ca-9b9f3d3afee2)

#### Realiza o Encerramento da Reserva

```http
  PUT /reservas/encerrar-reserva/{reserva_id}
```
![EncerrarReserva](https://github.com/matheushajer/gerenciador-reservas-restaurante/assets/76411830/42782f11-a9ec-42d0-b9e8-f46815bc9e91)

#### Cria Avaliações

```http
  POST /avaliacoes/criar-avaliacao
```
| Parâmetro   | Tipo       | Descrição                           |
| :---------- | :--------- | :---------------------------------- |
| `cliente_id  ` | `integer` |**Obrigatório**. Deve receber o ID do Cliente. Aceita apenas valores numéricos.|
| `comentario  ` | `string` |Deve receber o comentário. Aceita caracteres numéricos e especiais.|
| `nota  ` | `integer` |**Obrigatório**. Deve receber a Data e Hora da Reserva, respeitando o seguinte modelo "aaaa-mm-ddThh:mm:ss".|
| `restaurante_id  ` | `integer` |**Obrigatório**. Deve receber o ID do Restaurante. Aceita apenas valores numéricos.|

![CriarAvaliacao](https://github.com/matheushajer/gerenciador-reservas-restaurante/assets/76411830/4197d00a-33ad-4af2-b16d-9a5841b914f2)

## Deploy

#### Deploy local:

Basta acessar https://github.com/matheushajer/gerenciador-reservas-restaurante/tree/master/deployment para encontrar as imagens utilizadas.

O Dockerfile contém a aplicação da api e o Dockerfile-Jacoco Utiliza o Jacoco para gerar o HTML para visualizar os testes.

#### Deploy no Render: 

As imagens nos Dockerfiles usados localmente foram colocadas no docker.io para utilizar no Render.

- API: https://fiap-ger-reservas.onrender.com
- DOC: https://fiap-ger-reservas.onrender.com/swagger-ui/index.html
- Testes: https://fiap-ger-reservas-jacoco.onrender.com/
- Repositório de imagens: https://hub.docker.com/r/yuriesena/fiap-ger-reservas/tags

## Autores

- Cleyton Sales
- Déborah Souza
- Karoline Leite
- Matheus Hajer
- Yuri Sena
