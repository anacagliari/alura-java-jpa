# 03 Para saber mais: tipos de relacionamentos na JPA

Quando falamos de relacionamentos em banco de dados, estamos nos referindo à maneira como diferentes tabelas de um banco de dados interagem entre si. Através destas relações, conseguimos criar associações lógicas entre diferentes conjuntos de informações, facilitando a organização e compreensão destes dados.

Temos quatro tipos principais de relações:

- 1. One-To-Many (Um-Para-Muitos): Neste tipo de relação, um registro em uma tabela pode se relacionar com muitos registros em outra tabela. Por exemplo, um professor pode dar aulas para muitos alunos e criar uma relação um-para-muitos entre o professor e os alunos.
- 2. Many-To-One (Muitos-Para-Um): Aqui, muitos registros em uma tabela podem se relacionar com um registro em outra tabela. Usando o exemplo anterior, muitos alunos podem ter aula com um mesmo professor, estabelecendo uma relação muitos-para-um.
- 3. Many-To-Many (Muitos-Para-Muitos): Nesta relação, muitos registros em uma tabela podem se relacionar com muitos registros em outra tabela. Bem, um aluno pode ter aulas com vários professores e vice-versa, certo? Esta é uma relação muitos-para-muitos.
- 4. One-To-One (Um-Para-Um): Neste tipo de relação, um registro em uma tabela se relaciona com apenas um registro em outra tabela, e vice-versa. Por exemplo, um usuário pode ter apenas um endereço, e este endereço pertence a apenas um usuário.
Para mapear esses relacionamentos na nossa aplicação, utilizamos anotações específicas da JPA. Elas podem ser:

## 1 - One-To-Many: 
Utilizamos a anotação `@OneToMany` para representar esse tipo de relação. Vamos ver um exemplo:

```java
@Entity
public class Professor {
    @Id
    private Long id;
    private String nome;
    
    @OneToMany(mappedBy = "professor")
    private List<Aluno> alunos;
}
```

Nesse código, estamos dizendo que um professor pode possuir muitos alunos. A propriedade mappedBy é usada para indicar o campo que representa o professor na entidade Aluno.

## 2 - Many-To-One: Para esse tipo de relação, usamos a anotação `@ManyToOne`. Veja o exemplo:
```java
@Entity
public class Aluno {
    @Id
    private Long id;
    private String nome;
    
    @ManyToOne
    private Professor professor;
}
```
Aqui, estamos dizendo que vários alunos podem ser ensinados por um professor.

## 3 - Many-To-Many: Essa relação é um pouco mais complexa, pois requer uma tabela intermediária para sua implementação. Utilizamos a anotação `@ManyToMany` para representar essa relação. Confira o exemplo:
```java
@Entity
public class Aluno {
    @Id
    private Long id;
    private String nome;
    
    @ManyToMany
    private List<Professor> professores;
}

@Entity
public class Professor {
    @Id
    private Long id;
    private String nome;

    @ManyToMany(mappedBy = "professores")
    private List<Aluno> alunos;
}
```
Nesse caso, estamos dizendo que um aluno pode ter muitos professores e um professor pode ter muitos alunos. A tabela intermediária é criada automaticamente pela JPA. Caso queira saber mais sobre relacionamentos e como eles ocorrem no banco, recomendamos os cursos de banco de dados aqui da Alura. [Você encontra uma formação de SQL com Postgres aqui](https://cursos.alura.com.br/formacao-postgresql).

## 4 - One-To-One: Para esse tipo de relação, usamos a anotação `@OneToOne`. Veja o exemplo:
```java
@Entity
public class Usuario {
    @Id
    private Long id;
    private String nome;
    
    @OneToOne(cascade = CascadeType.ALL)
    private Endereco endereco;
}

@Entity
public class Endereco {
    @Id
    private Long id;
    private String logradouro;
    private String bairro;
    private String cidade;
    // outros atributos...
    
    @OneToOne(mappedBy = "endereco")
    private Usuario usuario;
}
```
Neste exemplo, um usuário possui apenas um endereço e um endereço pertence a apenas um usuário. O atributo `cascade = CascadeType.ALL` indica que as operações de persistência (salvar, atualizar, remover) no objeto Usuario serão propagadas para o objeto Endereço.

Com essa compreensão das relações de banco de dados e como representá-las na JPA, você conseguirá modelar seus bancos de dados de maneira eficiente e lógica.