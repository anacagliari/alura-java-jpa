# O que aprendemos?

Nessa aula, você aprendeu como:

- ### Configurar seu ambiente Postgres.
Fizemos a instalação desse banco de dados relacional e vimos a diferença entre bancos relacionais e outros tipos de bancos de dados, além de criar nosso banco de séries no Postgres.

- ### Preparar sua aplicação para trabalhar com banco de dados. 
Adicionamos a dependência da JPA ao pom.xml e as configurações do banco de dados no application.properties.

- ### Utilizar anotações do Hibernate para mapear suas entidades. 
Usamos anotações como @Entity, @Transient e @Column na classe Serie, indicando como seriam as configurações da tabela correspondente no banco de dados.

- ### Manipular interfaces do tipo Repository. 
Para fazer operações básicas no banco de dados, como um CRUD, precisamos de uma interface do tipo Repository com o nosso tipo de dados. No nosso caso, criamos a SerieRepository.

- ### Injetar dependências. 
Vimos que não podemos instanciar uma interface do tipo Repository em qualquer lugar. Elas precisam ser declaradas em classes gerenciadas pelo Spring, precedidas de um @Autowired, indicando que está sendo realizada uma injeção de dependências.

- ### Trabalhar com variáveis de ambiente. 
Utilizamos variáveis de ambiente para proteger dados sensíveis da conexão com o banco de dados e com a API.