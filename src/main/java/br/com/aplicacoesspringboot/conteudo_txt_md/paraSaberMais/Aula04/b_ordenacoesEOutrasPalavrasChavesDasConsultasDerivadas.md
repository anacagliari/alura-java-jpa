# 06 Para saber mais: ordenações e outras palavras chaves das consultas derivadas

Para trabalhar com a ordenação de registros pesquisados, também existem algumas palavras-chave. Podemos utilizar o `OrderBy` para ordenar os registros por algum atributo deles, como a série pela avaliação. Também podemos encadear atributos. Se uma Série tem um Ator e queremos ordenar pelo nome do ator, podemos utilizar `OrderByAtorNome`, por exemplo.

Além do `OrderBy`, ainda existem alguns outros recursos de filtros que podem ser utilizados:

- `Distinct`, para remover dados duplicados
- `First`, para pegar o primeiro registro
- `Top`, para limitar o número de dados

Ainda existe uma infinidade de palavras-chave e recursos que podemos utilizar com as derived queries. Sugerimos que você leia a documentação e exercite a prática.

[Documentação da Spring Data JPA](https://docs.spring.io/spring-data/jpa/reference/).

Boa leitura!