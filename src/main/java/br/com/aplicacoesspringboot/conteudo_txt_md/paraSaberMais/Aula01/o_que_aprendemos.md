# O que aprendemos?

Nessa aula, você aprendeu como:

- ### Buscar várias séries na API. 

Criamos um loop que encerra somente quando o usuário escolhe sair do menu, sendo capaz de buscar várias séries na API sem parar.

- ### Métodos privados. 

Vimos que, se apenas uma classe irá acessar um método, não precisamos deixá-lo público, podemos declará-lo como privado. Isso é essencial para o encapsulamento das nossas classes.

- ### Adicionar mais informações aos dados buscados. 

Revisamos como realizar o mapeamento entre atributos da API e atributos da nossa record.

- ### Converter os dados que vêm da API para a sua própria classe. 

Criamos nossa própria classe Serie para representar melhor nossos dados. Para isso, foi necessário utilizar vários métodos de conversão.

- ### Utilizar um “ifreduzido”. 

Utilizamos a classe OptionalDouble para lidar com valores decimais e seus possíveis erros, utilizando os métodos ofe orElse, que lembram muito o código de um if reduzido, e são muito úteis para evitar que ocorram Exceptions.

- ### Criar um Enum. 

Percebemos que seria excelente poder categorizar nossas séries por gênero. Criamos um enum para isso, e vimos como criar métodos personalizados em enums.

- ### Consumir a API do ChatGPT. 

Utilizamos a API do ChatGPT para traduzir nossos dados, adicionamos todas as dependências necessárias e configuramos a classe de consumo.