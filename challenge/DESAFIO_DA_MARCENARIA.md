# DESAFIO DE PROGRAMAÇÃO - API PARA ORÇAMENTO DE MÓVEIS PLANEJADOS

Este desafio tem como objetivo o desenvolvimento de uma API REST para aplicação prática de conceitos básicos de programação. Parte de um projeto de benchmark de frameworks web, a API simula um sistema de orçamento de móveis planejados para marcenaria.

Pode ser implementado em qualquer linguagem de programação e framework de preferência. Embora haja padrões e requisitos mínimos para benchmarks, o desafio pode ser adaptado conforme preferências individuais.

## DESCRIÇÃO 

O desafio simula uma REST API de um sistema de Marcenaria que faz o orçamento de móveis planejados. A API deve receber uma payload JSON representando um móvel, o material a ser utilizado e as medidas das estruturas geométricas do móvel.

- As estruturas devem ser representadas por objetos polimórficos com propriedades diferentes.
- Todos os valores numéricos nos JSONs devem ser representados por strings seguidas de suas medidas (conforme exemplo de payload).
- O sistema deve calcular a área da estrutura de acordo com o tipo de geometria e as medidas recebidas.
- O preço de cada estrutura é a área multiplicada pelo preço base do material utilizado.
- O preço total do móvel é a soma dos preços de todas as estruturas.
- A serialização do input deve considerar validações e conversões, como ignorar acentuações e padronizar caracteres com letras maiúsculas/minúsculas.
- Deve retornar uma resposta JSON com todos os dados: o móvel, material, preço total e detalhes de cada estrutura.
- A API não precisa possuir um banco de dados; o processamento pode ser feito completamente em memória. Pode haver um endpoint que utilize cache (para comparação), mas o benchmark será medido apenas sem cache.

*Nota: Os números não precisam ser plausíveis em um contexto real; a REST API é completamente hipotética.*

### RECURSOS

A documentação OPEN API deve ser seguida, incluindo exemplos de requisições e respostas.

*ENDPOINTS*

- *POST /orcamentos:* Recebe uma payload JSON com uma lista de móveis, detalhes de geometrias e seus materiais. Retorna o orçamento detalhado de todos os móveis.

- *POST /orcamento-unitario:* Recebe a payload JSON com os dados de um único móvel, detalhes das geometrias e seus materiais. Retorna o orçamento detalhado.

*GET /geometrias:* Retorna uma lista no formato JSON com os detalhes de todas as geometrias disponíveis.

*GET /materiais:* Retorna uma lista no formato JSON com os detalhes de todos os materiais disponíveis e seus preços base.

### MATERIAIS E PREÇOS BASES

O nível básico é o básico necessário para existir na API.


*NÍVEL BASICO*

| *MATERIAL*     | *PRECO BASE* |
|----------------|--------------|
| pinho          | R$0,10       |
| carvalho       | R$0,30       |
| ébano          | R$5,00       |


Existe também o nível avançado que servirá para ser feito o benchmark que possui mais materiais, a ideia é ter mais opções para buscar dentre as opções:


*NÍVEL AVANÇADO*

| *MATERIAL*        | *PRECO BASE* |
|-------------------|--------------|
| pinho             | R$0,10       |
| compensado        | R$0,12       |
| mdf               | R$0,15       |
| formica           | R$0,18       |
| freijó            | R$0,20       |
| cedro             | R$0,25       |
| carvalho          | R$0,30       |
| ipê               | R$0,45       |
| mogno             | R$0,50       |
| imbuia            | R$0,55       |
| jacarandá         | R$0,60       |
| aço inoxidável    | R$1,50       |
| aço temperado     | R$2,00       |
| ébano             | R$5,00       |


### GEOMETRIAS E FÓRMULAS DE CÁLCULO ÁREA 

*NÍVEL BÁSICO*

| *GEOMETRIA*    | *FÓRMULA*                                                             |
|----------------|-----------------------------------------------------------------------|
| ESFERA         | 2 * pi * raio²                                                        |
| CUBO           | 6 * lado²                                                             |
| CILINDRO       | 6 * pi * raio base * (raio base + altura)                             |
| PARALELEPIPEDO | 2 * (comprimento * largura + comprimento * altura + largura * altura) |


*NIVEL AVANÇADO*

| *GEOMETRIA*              | *FÓRMULA*                                                             |
|--------------------------|-----------------------------------------------------------------------|
| ESFERA                   | 2 × pi × raio²                                                        |
| CUBO                     | 6 × lado²                                                             |
| CILINDRO                 | 6 × pi × raio base × (raio base + altura)                             |
| PARALELEPIPEDO           | 2 × (comprimento × largura + largura × altura + altura × comprimento) |
| PIRAMIDE BASE QUADRADA   | lado² + 4 × (lado × altura / 2)                                       |
| DISCO                    | pi × raio²                                                            |
| CONE                     | (1/3) × pi × raio base² × altura                                      |


A payload de request e de resposta devem seguir o seguinte padrão:

- (REQUEST)[../payloads/request.json]
- (RESPONSE)
