# DESAFIO DA MARCENARIA

O desafio a seguir tem a proposta de desenvolver uma simples API REST para aplicação prática de conceitos básicos de programação. Ele faz parte de um projeto de benchmark de frameworks web, mas pode ser utilizado como base para estudo, prova de conceito e projetos pessoais. Ele pode ser realizado em qualquer linguagem de programação e framework de preferência.

Para os benchmarks ele precisa seguir os padrões e requisitos mínimos; mas, por se for feito em outra ocasião, você pode adaptá-lo com suas preferências.

## DESCRIÇÃO 

O desafio simula uma REST API de um sistema de Marcenaria que faz o orçamento de móveis planejados. O sistema deverá receber uma payload JSON que representa um móvel, o material que será utilizado no movel, e medidas de estruturas geométricas que serão usadas nesse móvel.

- As estruturas devem obrigatoriamente ser representadas por objetos polimórficos de propriedades diferentes.
- Todos os JSONs da aplicação deve possuir seus valores númericos representados por String seguida por sua medida (vide o exemplo de payload)
- O sistema deverá calcular a área da estrutura de acordo com o tipo da geometria e as medidas recebidas.
- O preço de cada estrutura é a área dela múltiplicada pelo preço base do material utilizado.
- O preço do móvel é a soma do preço de todas as estruturas.
- A serialização do input deve considerar validações e conversões, por exemplo: ignorar acentuações e caracteres com letras maiusculas/minusculas.
- Por fim, deverá retornar uma resposta JSON com todos os dados: o móvel, material, preço total e os detalhes de cada uma de suas estrutras.

A Api não precisa possuir banco de dados, seu processamento pode ser feito completamente em memória.
Pode haver um endpoint que utilize cache (a fim de comparação), mas será mensurado para o benchmark apenas sem cache.

OS NÚMEROS NÃO PRECISAM E NEM DEVEM SER PLAUSÍVEIS EM UM CONTEXTO REAL, A REST API É COMPLETAMENTE HIPOTÉTICA.

### MATERIAIS E PREÇOS BASES

O nível básico é o básico necessário para existir na API.

-------------------------
|       *NÍVEL BASICO*    |
|-----------------------|
| *MATERIAL* | *PRECO BASE* |
-------------------------
| pinho      | R$0,10   |
| carvalho   | R$0,30   |
| ébano      | R$5,00   |
|-----------------------|

Existe também o nível avançado que servirá para ser feito o benchmark que possui mais materiais, a ideia é ter mais opções para buscar dentre as opções:

-------------------------------------
|            *NÍVEL AVANÇADO*       |
|-----------------------------------|
|     *MATERIAL*    | *PRECO BASE*  |
------------------------------------|
| pinho             |     R$0,10    |
| compensado        |     R$0,12    |
| mdf               |     R$0,15    |
| formica           |     R$0,18    |
| freijó            |     R$0,20    |
| cedro             |     R$0,25    |
| carvalho          |     R$0,30    |
| ipê               |     R$0,45    |
| mogno             |     R$0,50    |
| imbuia            |     R$0,55    |
| jacarandá         |     R$0,60    |
| aço inoxidável    |     R$1,50    |
| aço temperado     |     R$2,00    |
| ébano             |     R$5,00    |
|-----------------------------------|

A payload de request e de resposta devem seguir o seguinte padrão:


