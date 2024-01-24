# Ranking de performance

---------------------------------------------------------------------------------
| Posição |              Api                        | Req. p/ Sec | Req. Totais |
| ------- | --------------------------------------- | ----------- | ----------- |
| 1       | Ronaldo - Javascript (uWebSockets)      | 4219.34     | 253196      |
| 2       | Gabriel - Go (fiber)                    | 4210.38     | 252656      |
| 3       | Gabriel - Rust (ntex)                   | 4114.37     | 246882      |
| 4       | Gabriel - Rust (actix)                  | 3928.08     | 235700      |
| 5       | Gabriel - Java (vertx)                  | 3685.79     | 221244      |
| 6       | Emerson - Javascript (fastify) ???      | 2580.77 (?) | 154848 (?)  |
| 7       | Emmanuel - Java (spring) (???) ladrão   | 2144.88 (?) | 128742 (?)  |
| 8       | Gabriel - Java (spring)                 | 1978.82     | 118804      |
| 9       | Pedro Henrique - C# .NET Core 8  (???)  | 1178.57 (?) |  70718 (?)  |
---------------------------------------------------------------------------------


# Orçamento Marcenaria

PRAZO: Terça-Feira (23/01/2024)

Você foi contratado para desenvolver uma API que calcula o orçamento de móveis planejados para uma marcenaria. O processo de orçamento envolve uma requisição REST, onde são fornecidos dados sobre o móvel, incluindo o tipo, o tipo de madeira a ser utilizado e as formas geométricas presentes.

A API deve ser capaz de validar a correção das requisições, respondendo com erros para casos de solicitações incorretas. Em situações corretas, a API deve realizar os cálculos necessários e retornar o preço final de cada forma geométrica, bem como o preço total (soma das formas).

Além disso, é necessário implementar uma documentação utilizando OpenAPI/Swagger, seguindo as melhores práticas de desenvolvimento. A implementação pode ser em memória, sem a necessidade de consultas externas, como bancos de dados.

A  API deve fornecer também dois endpoints para consulta:
-   Tipos de madeira e seus respectivos preços.
-   Tipos de geometria e suas propriedades.

Você é livre para decidir se os tipos de móveis são predefinidos ou livres.

As APIs serão submetidas a testes de desempenho, então também se esforce nesse aspecto. **NÃO SE PREOCUPE EM NÚMEROS REALISTAS.**

Os tipos de madeira são:

- **Tipo de Madeira:** Pinho
  - **Preço base:** R$0,10

- **Tipo de Madeira:** Carvalho
  - **Preço base:** R$0,30

- **Tipo de Madeira:** Ébano
  - **Preço Base:** R$5,00

Você é livre para decidir quais são as formas geométricas existentes, mas lembre-se de que cada forma possui atributos diferentes. Por exemplo:

- **ESFERA:**
  - Tipo: esfera,
  - Raio: em cm,

- **CUBO:**
  - Tipo: cubo,
  - Lado: em cm,

- **CILINDRO:**
  - Tipo: cilindro,
  - Raio base: em cm,
  - Altura: em cm,

- **PARALELEPÍPEDO:**
  - Tipo: paralelepípedo
  - Comprimento: em cm
  - Largura:  em cm
  - Altura: em cm
 
  
 (pirâmide pode ser desconsiderada, mas é um desafio a mais, considere que é uma piramide quadrada para fazer o cálculo)
- **PIRÂMIDE:**
  - Tipo: pirâmide,
  - Base: em cm,
  - Altura: em cm,


A API deverá ser responsável por calcular a área da geometria (não pode-se receber via payload).

O preço da geometria será: Preço base da Madeira x Área da Geometria em reais.

O preço do móvel será a soma do preço de todas as geometrias existentes nele.

**EXEMPLO DE REQUISIÇÃO:**

```json
{
  "movel": "CADEIRA",
  "material": "Carvalho",
  "geometrias": [
    {
      "estrutura": "perna dianteira esquerda",
      "geometria": "cilindro",
      "raio_base": "1.5cm",
      "altura": "40cm"
    },
    {
      "estrutura": "perna dianteira direita",
      "geometria": "cilindro",
      "raio_base": "1.5cm",
      "altura": "40cm"
    },
    {
      "estrutura": "perna traseira esquerda",
      "geometria": "cilindro",
      "raio_base": "1.5cm",
      "altura": "40cm"
    },
    {
      "estrutura": "perna traseira direita",
      "geometria": "cilindro",
      "raio_base": "1.5cm",
      "altura": "40cm"
    },
    {
      "estrutura": "assento",
      "geometria": "paralelepípedo",
      "comprimento": "40cm",
      "largura": "40cm",
      "altura": "5cm"
    },
    {
      "estrutura": "encosto",
      "geometria": "paralelepípedo",
      "comprimento": "40cm",
      "largura": "2cm",
      "altura": "30cm"
    }
  ]
}
```

```json
{
  "movel": "ESTANTE",
  "material": "CARVALHO",
  "geometrias": [
    {
      "estrutura": "prateleira 1",
      "geometria": "paralelepípedo",
      "comprimento": "80cm",
      "largura": "30cm",
      "altura": "2cm"
    },
    {
      "estrutura": "prateleira 2",
      "geometria": "paralelepípedo",
      "comprimento": "80cm",
      "largura": "30cm",
      "altura": "2cm"
    },
    {
      "estrutura": "prateleira 3",
      "geometria": "paralelepípedo",
      "comprimento": "80cm",
      "largura": "30cm",
      "altura": "2cm"
    },
    {
      "estrutura": "pilar esquerdo",
      "geometria": "cilindro",
      "raio_base": "2cm",
      "altura": "100cm"
    },
    {
      "estrutura": "pilar direito",
      "geometria": "cilindro",
      "raio_base": "2cm",
      "altura": "100cm"
    }
  ]
}
```

```json
{
  "movel": "CÔMODA",
  "material": "PINHO",
  "geometrias": [
    {
      "estrutura": "gaveta superior",
      "geometria": "paralelepipedo",
      "comprimento": "60cm",
      "largura": "40cm",
      "altura": "15cm"
    },
    {
      "estrutura": "gaveta do meio",
      "geometria": "paralelepipedo",
      "comprimento": "60cm",
      "largura": "40cm",
      "altura": "15cm"
    },
    {
      "estrutura": "gaveta inferior",
      "geometria": "paralelepipedo",
      "comprimento": "60cm",
      "largura": "40cm",
      "altura": "20cm"
    },
    {
      "estrutura": "base",
      "geometria": "paralelepipedo",
      "comprimento": "70cm",
      "largura": "50cm",
      "altura": "5cm"
    },
    {
      "estrutura": "pés",
      "geometria": "cilindro",
      "raio_base": "2cm",
      "altura": "15cm"
    }
  ]
}
```

A resposta você pode dar no formato que preferir. 
