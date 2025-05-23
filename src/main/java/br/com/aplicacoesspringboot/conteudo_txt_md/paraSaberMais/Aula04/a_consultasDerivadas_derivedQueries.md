# 03 Para saber mais: consultas derivadas ("derived queries")

A JPA tem diversos recursos, e um dos mais legais que podemos utilizar são as derived queries, em que trabalhamos com métodos específicos que consultam o banco de forma personalizada. Esses métodos são criados na interface que herda de JpaRepository. Neles, utilizaremos palavras-chave (em inglês) para indicar qual a busca que queremos fazer.

A estrutura básica de uma derived query na JPA consiste em:

`verbo introdutório + palavra-chave “By” + critérios de busca`

Como verbos introdutórios, temos find, read, query, count e get. Já os critérios são variados. Veremos alguns exemplos em vídeo, mas você pode explorar bastante a prática para entendê-los melhor.

Os critérios mais simples envolvem apenas os atributos da classe mapeada no Repository. No nosso caso, um exemplo desse critério seria o `findByTitulo`, em que fazemos uma busca por séries com um atributo específico da classe `Serie`. Mas podemos acrescentar condições a esses critérios. É aí que surge o `findByTituloContainingIgnoreCase()`. Para fazer os filtros, poderíamos utilizar várias outras palavras. Dentre elas, podemos citar:

- Palavras relativas à igualdade:
  - `Is`, para ver igualdades
  - `Equals`, para ver igualdades (essa palavra-chave e a anterior têm os mesmos princípios, e são mais utilizadas para a legibilidade do método).
  - `IsNot`, para checar desigualdades
  - `IsNull`, para verificar se um parâmetro é nulo

- Palavras relativas à similaridade:
  - `Containing`, para palavras que contenham um trecho
  - `StartingWith`, para palavras que comecem com um trecho
  - `EndingWith`, para palavras que terminem com um trecho
  - Essas palavras podem ser concatenadas com outras condições, como o `ContainingIgnoreCase`, para não termos problemas de Case Sensitive.

- Palavras relacionadas à comparação:
  - `LessThan`, para buscar registros menores que um valor
  - `LessThanEqual`, para buscar registros menores ou iguais a um valor
  - `GreaterThan`, para identificar registros maiores que um valor
  - `GreaterThanEqual`, para identificar registros maiores ou iguais a um valor
  - `Between`, para saber quais registros estão entre dois valores
  
  Essas são apenas algumas das palavras que podemos utilizar, e podemos combiná-las de muitas formas! Ao longo dos próximos vídeos, vamos exercitar nossos conhecimentos fazendo várias buscas com essas palavras-chave, mas também te convidamos a testar com vários exemplos para ver na prática como funciona!