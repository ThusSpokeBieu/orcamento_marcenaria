# Orçamento Marcenaria


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

O preço da geometria será: (Preço base da Madeira x Preço base da Geometria x Área da Geometria )

**EXEMPLO DE REQUISIÇÃO:**

MESA

- Móvel: MESA
- Madeira: CARVALHO
- Geometrias: 
	- Estrutura: perna 1
	  - Geometria: cilindro
	  - Raio base: 2cm
	  - Altura: 70cm

	- Estrutura: perna 2
	  - Geometria: cilindro
	  - Raio base: 2cm
	  - Altura: 70cm

	- Estrutura: perna 3
	  - Geometria: cilindro
	  - Raio base: 2cm
	  - Altura: 70cm

	- Estrutura: perna 4
	  - Geometria: cilindro
	  - Raio base: 2cm
	  - Altura: 70cm

	- Estrutura: base superior
	  - Geometria: cubo retangular
	  - Comprimento: 100cm
	  - Largura:  70 cm
	  - Altura: 5 cm
