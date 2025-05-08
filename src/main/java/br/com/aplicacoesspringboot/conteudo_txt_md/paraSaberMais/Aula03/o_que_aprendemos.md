# O que aprendemos?

Nessa aula, você aprendeu como:

- ### Mapear relacionamentos entre entidades da JPA. 
Conhecemos o uso das anotações `@OneToMany` e `@ManyToOne` para identificar o relacionamento ”um para muitos” de séries e episódios.

- ### Conhecer diversos tipos de relacionamento: 
Identificamos qual era o relacionamento presente na nossa aplicação, além de ter conhecimento dos vários tipos de relacionamento em banco de dados.

- ### Associar chaves estrangeiras. E
ntendemos o conceito de chave estrangeira, que é como o banco de dados identifica e configura relacionamentos.

- ### Trabalhar com os tipos de Cascade. 
Como o nosso fluxo de salvamento de dados era salvar séries e depois episódios, foi preciso configurar isso utilizando o atributo Cascade.

- ### Identificar como os dados são carregados. 
Trabalhamos também com o atributo fetch, que fala sobre carregar os dados de forma “preguiçosa” (lazy) ou “ansiosa” (eager).

- ### Configurar relacionamentos bidirecionais. 
Vimos a importância de relacionamentos bidirecionais e deixamos as modificações aparecendo dos dois lados da relação, fazendo tanto setEpisodios() na Série quanto setSerie() nos Episodios.