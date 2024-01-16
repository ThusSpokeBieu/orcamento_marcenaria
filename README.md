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

- **CUBO RETANGULAR:**
  - Tipo: cubo retangular
  - Comprimento: em cm
  - Largura:  em cm
  - Altura: em cm

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
  "Móvel": "CADEIRA",
  "Material": "Carvalho",
  "Geometrias": [
    {
      "Estrutura": "perna dianteira esquerda",
      "Geometria": "cilindro",
      "Raio_base": "1.5cm",
      "Altura": "40cm"
    },
    {
      "Estrutura": "perna dianteira direita",
      "Geometria": "cilindro",
      "Raio_base": "1.5cm",
      "Altura": "40cm"
    },
    {
      "Estrutura": "perna traseira esquerda",
      "Geometria": "cilindro",
      "Raio_base": "1.5cm",
      "Altura": "40cm"
    },
    {
      "Estrutura": "perna traseira direita",
      "Geometria": "cilindro",
      "Raio_base": "1.5cm",
      "Altura": "40cm"
    },
    {
      "Estrutura": "assento",
      "Geometria": "cubo retangular",
      "Comprimento": "40cm",
      "Largura": "40cm",
      "Altura": "5cm"
    },
    {
      "Estrutura": "encosto",
      "Geometria": "paralelepípedo",
      "Comprimento": "40cm",
      "Largura": "2cm",
      "Altura": "30cm"
    }
  ]
}

```

```json
{
  "Móvel": "ESTANTE",
  "Material": "CARVALHO",
  "Geometrias": [
    {
      "Estrutura": "prateleira 1",
      "Geometria": "cubo retangular",
      "Comprimento": "80cm",
      "Largura": "30cm",
      "Altura": "2cm"
    },
    {
      "Estrutura": "prateleira 2",
      "Geometria": "cubo retangular",
      "Comprimento": "80cm",
      "Largura": "30cm",
      "Altura": "2cm"
    },
    {
      "Estrutura": "prateleira 3",
      "Geometria": "cubo retangular",
      "Comprimento": "80cm",
      "Largura": "30cm",
      "Altura": "2cm"
    },
    {
      "Estrutura": "pilar esquerdo",
      "Geometria": "cilindro",
      "Raio_base": "2cm",
      "Altura": "100cm"
    },
    {
      "Estrutura": "pilar direito",
      "Geometria": "cilindro",
      "Raio_base": "2cm",
      "Altura": "100cm"
    }
  ]
}
```

```json
{
  "Móvel": "CÔMODA",
  "Material": "PINHO",
  "Geometrias": [
    {
      "Estrutura": "gaveta superior",
      "Geometria": "paralelepípedo",
      "Comprimento": "60cm",
      "Largura": "40cm",
      "Altura": "15cm"
    },
    {
      "Estrutura": "gaveta do meio",
      "Geometria": "paralelepípedo",
      "Comprimento": "60cm",
      "Largura": "40cm",
      "Altura": "15cm"
    },
    {
      "Estrutura": "gaveta inferior",
      "Geometria": "paralelepípedo",
      "Comprimento": "60cm",
      "Largura": "40cm",
      "Altura": "20cm"
    },
    {
      "Estrutura": "base",
      "Geometria": "cubo retangular",
      "Comprimento": "70cm",
      "Largura": "50cm",
      "Altura": "5cm"
    },
    {
      "Estrutura": "pés",
      "Geometria": "cilindro",
      "Raio_base": "2cm",
      "Altura": "15cm"
    }
  ]
}

```

A resposta você pode dar no formato que preferir. 
