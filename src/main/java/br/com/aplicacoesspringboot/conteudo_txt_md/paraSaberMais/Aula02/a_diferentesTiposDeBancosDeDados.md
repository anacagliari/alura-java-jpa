# 04 Para saber mais: diferentes tipos de bancos de dados

Os bancos de dados são como grandes armários onde guardamos todas as informações que uma aplicação precisa para funcionar bem: informações sobre usuários, produtos, vendas, etc.

Mas os bancos de dados são todos iguais? Não! Há diferentes tipos de bancos de dados e eles são definidos pela forma como organizam essas informações. Imagine, por exemplo, que há armários com diferentes tamanhos de gavetas e tipo de compartimentos. Vamos entender mais sobre dois tipos: bancos de dados relacionais e bancos de dados orientados a documentos.

## O que são bancos de dados relacionais?

Bancos de dados relacionais (RDBMS) são como armários com muitas gavetas de tamanhos específicos que armazenam informações organizadas em tabelas com linhas e colunas.Cada linha representa um registro único e cada coluna representa um campo desse registro. É como se você organizasse suas roupas separando-as por cor e tipo: camisas brancas em uma gaveta, calças jeans em outra, etc.

A principal característica de um RDBMS é que ele permite estabelecer relações entre as tabelas, ou seja, é possível associar registros de uma tabela com registros de outra. Por exemplo, uma tabela de "Clientes" poderia se relacionar com uma tabela de "Pedidos", exibindo todos os pedidos feitos por um cliente específico.

Um exemplo de código em SQL, a linguagem utilizada em RDBMS:

```sql
CREATE TABLE clientes (
   id INT PRIMARY KEY,
   nome VARCHAR(100),
   email VARCHAR(100)
);

CREATE TABLE pedidos (
   id INT PRIMARY KEY,
   id_cliente INT,
   produto VARCHAR(100),
   FOREIGN KEY (id_cliente) REFERENCES clientes(id)
);
```
## O que são bancos de dados orientados a documentos?
Bancos de dados orientados a documentos são como armários com compartimentos flexíveis que permitem que você guarde objetos de tamanhos diferentes em um mesmo lugar. Eles armazenam as informações em formato de documentos, geralmente em JSON, que é um formato de dados leve, fácil de ler e trabalhar, capaz de representar estruturas de dados complexas.

Os documentos podem conter diferentes campos e estruturas de dados, e os campos podem conter diferentes tipos de dados. O conjunto de documentos é flexível, ou seja, documentos no mesmo banco de dados não precisam ter a mesma estrutura.

Por exemplo, você poderia ter um documento para um usuário que tem um campo de endereço, e outro documento para um usuário que não tem.

Um exemplo de código em MongoDB - um banco de dados orientado a documentos:

```sql
var meuCliente = {
   nome: "João",
   email: "joao@email.com",
   pedidos: [
      { produto: "Camiseta", quantidade: 2 },
      { produto: "Calça", quantidade: 1 },
   ]
}

db.clientes.insert(meuCliente);
```
Ao trabalhar com bancos de dados é importante entender as diferenças entre os modelos para escolher a opção que melhor se adapta às necessidades do seu projeto. Bancos de dados relacionais são ótimos para dados estruturados e relações complexas, que é o nosso caso. Por isso, optamos por um tipo de RDBMS, o Postgres.

Já os bancos de dados orientados a documentos são excelentes para armazenar dados semi estruturados ou não estruturados, que oferecem escalabilidade e flexibilidade.

Há outros tipos de bancos de dados, por isso, convidamos você a pesquisar sobre o assunto caso queira se aprofundar mais no tema!