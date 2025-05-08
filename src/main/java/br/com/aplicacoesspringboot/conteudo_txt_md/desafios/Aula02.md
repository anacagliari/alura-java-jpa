- ### 16 Desafio: hora da prática

Em aula, conhecemos a mágica da JPA para lidar com classes e tabelas. Agora, seu objetivo é continuar praticando e ser capaz de comparar várias funcionalidades diferentes da JPA.

Para isso, você pode criar um novo projeto chamado `gerenciador-pedidos`, onde iremos trabalhar em classes de `Produto` e `Pedido`, por exemplo. Seu projeto deve ser do tipo “spring-sem-web” e deve ter as dependências do Spring JPA e do banco de dados PostgreSQL.

Tendo criado o projeto completo e configurado as configurações do banco no `application.properties`, você pode criar um banco de dados chamado `gerenciador-pedidos` também, conforme feito em aula.

Feito isso, estamos preparados para exercitar os conhecimentos. Vamos lá?

1 - Crie uma classe chamada `Produto` com os seguintes atributos: `id` (Long, chave primária) `nome` (String) `preco` (Double) Anote a classe com `@Entity` e mapeie o atributo `id` como chave primária com `@Id`. A classe deve ter um construtor e os getters.

2 - Modifique o exercício anterior para usar a anotação `@GeneratedValue` no campo `id`, com a estratégia de geração automática de identificadores (`GenerationType.IDENTITY`).

3 - Agora, iremos usar vários parâmetros da anotação `@Column`. Acrescente os seguintes requisitos à classe `Produto`: O atributo `nome` deve ser único e não nulo. O atributo `preco` deve ser armazenado em uma coluna chamada `valor`.

4 - Crie uma classe `Categoria` com os atributos: `id` (Long, chave primária) `nome` (String) A classe deve ter um construtor e os getters. Transforme a classe em entidade.

5 - Crie uma classe `Pedido` com os seguintes atributos: `id` (Long, chave primária) `data` (LocalDate) A classe deve ter um construtor e os getters. Transforme a classe em entidade.

6 - Execute a aplicação e veja se todas as tabelas foram criadas corretamente. Quando estiver tudo certo, podemos ir para o próximo passo.

7 - Agora, iremos querer salvar dados no banco. Por isso, você deve criar vários repositórios diferentes: um para `Pedido`, outro para `Produto` e outro para `Categoria`.

8 - Para finalizar, crie um objeto de cada classe e use os repositórios para salvar os dados. Tudo certo para executar os dados? Provavelmente faltará um passo bem importante. Tente se lembrar do que acontece na aula e fazer este último passo. Você também pode analisar o log de erro :) Caso ainda precise de ajuda, na “Opinião do instrutor” terá uma dica para te ajudar.

**Extra (pra quem quer mergulhar mesmo):** uma vez que seu programa tiver executado corretamente, você pode testar os vários tipos de GenerationType do id. Adicione vários registros diferentes, comparando como o id é inserido no banco de dados. Na prática, qual a diferença de cada um dos tipos de geração de id?

Faça a mesma coisa para os vários parâmetros de `@Column`. Como os parâmetros alteram as colunas? Para te ajudar nessa segunda parte, você pode usar alguma ferramenta de IA, pedindo sugestões de casos para testar. Depois, conte para a gente no fórum sobre sua experiência :)