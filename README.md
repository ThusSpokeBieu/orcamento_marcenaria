# Marcenaria Bench - Desafio e Benchmark de Programação


Bem-vindo ao "Marcenaria Bench", um repositório destinado a uma proposta divertida, ideal para estudo e prova de conceito. Aqui, apresentamos um desafio envolvendo a criação de uma REST API, aberta para ser implementada em qualquer tecnologia. Além disso, propomos um benchmark para impulsionar a melhoria das soluções desenvolvidas, buscando otimizar a performance de cada implementação.

### Foco no desafio:

O desafio abrange diversos temas, incluindo:

- Programação Orientada a Objetos e Polimorfismo.
- Requisição HTTP e Rest API 
- Manipulação de Buffers 
- Serialização e Desserialização (JSON e/ou bytes)
- Validação e Conversão de dados.
- Otimização de processamento.
- Fundamentos da programação e Paradigmas.

Você pode encontrar detalhes específicos do desafio no documento [DESAFIO_DA_MARCENARIA.md](./challenge/).

As implementações estão disponíveis no repositório [CODES](./codes/)
Cada implementação tem um README explicando o processo de sua criação.

Os resultados do benchmark serão detalhadas no repositório [BENCHMARKS](./benchmarks/).

As payloads podem ser encontradas no repositório [payloads](./payloads/).

## Indice

1. [Motivação](#motivação)
2. [O Desafio](#o-desafio)
3. [Como funciona o benchmark?](#como-funciona-o-benchmark)
4. [Como contribuir?](#como-contribuir)
5. [Lista de Implementações](#lista-de-implementações)
6. [Contato](#contato)

## Motivação 

O objetivo é criar um repositório colaborativo de estudos, onde todos possam contribuir e compartilhar informações sobre como resolver o problema proposto. Não se trata de uma competição para determinar qual framework é o mais performático ou qual programador fez a melhor implementação. A proposta é um estímulo para que as pessoas busquem aprimorar seus próprios códigos, seja estudando implementações de outros ou refinando as suas, compartilhando experiências e justificando decisões.


## O Desafio 

O desafio consiste na criação de uma webapi REST capaz de realizar orçamentos de móveis.

Detalhes completos do desafio podem ser encontrados neste documento: [DESAFIO DA MARCENARIA](./challenge/DESAFIO_DA_MARCENARIA.md).

## Como funciona o Benchmark? 

O benchmark é uma brincadeira e está sendo conduzido localmente, utilizando a ferramenta APACHE BENCHMARK para realizar 1000 requisições HTTP concorrentes durante um minuto. A payload da requisição pode ser encontrada [aqui](./payloads/).

Serão realizados no mínimo três testes para cada implementação, e o resultado com o maior número de requisições por segundo será considerado e adicionado a um ranking.

O benchmark visa avaliar como cada implementação lida com um grande volume de processamento de dados, validação e serialização de JSONs, polimorfismo, fluxos de controle e tratamento de strings.

Este benchmark é uma brincadeira, não uma competição. Está sempre aberto, sem prazos, e os melhores valores obtidos ao rodar uma aplicação em uma máquina específica serão considerados.


## Como contribuir?

Estamos explorando melhores formas de contribuição. Por enquanto, você pode abrir uma issue, criar um fork e tentar fazer uma Pull Request para um código existente, ou criar uma nova implementação em um novo repositório adicionando um novo framework, linguagem ou até mesmo uma outra implementação de um framework já existente.

Fique à vontade para fazer comentários, divulgar o repositório, criar forks, utilizar o desafio como modelo para algo externo, e contribuir de outras formas.

Você pode também fazer você mesmo um benchmark dos repositórios e compartilhar conosco.

## Lista de Implementações

- [.NET](./codes/dotnet/)
    - [.NET Core 8](./codes/dotnet/MarcenariaDotNet/)


- [Go](./codes/go/)
    - [Fiber Framework](./codes/go/marcenaria-go-fiber/)
    - [Hertz Framework](./codes/go/marcenaria-go-hertz/go.mod) 


- [Java](./codes/java/)
    - [ActiveJ Java 21](./codes/java/marcenaria-java-activej/)
    - [Springboot Java 21](./codes/java/marcenaria-spring/)
    - [Vertx Java 21](./codes/java/marcenaria-java-vertx/) 


- [Rust](./codes/rust/)
    - [Actix](./codes/rust/marcenaria_rust_actix/)
    - [Ntex](./codes/rust/marcenaria_rust_ntex/) 

## Contato 

Gabriel M. Costa
- github: @gmessiasc

Ronaldo Junior
- github: @itujo
