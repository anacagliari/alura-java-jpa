# 07 Para saber mais: um tipo diferente de modelar dados

Anteriormente, utilizamos um novo tipo de representação de dados, o Enum.

Enum é uma abreviação de "enumerados" e é um tipo especial de classe em Java que tem um número fixo de constantes. Empregar um Enum pode ser muito útil na programação para armazenar valores que sabemos que nunca vão mudar, como os dias da semana ou as fases da lua.

Vamos começar criando nosso próprio Enum? Imagine que estamos construindo um aplicativo sobre o sistema solar, e queremos um Enum para os planetas.

A seguir, um exemplo de como você poderia implementar isso:
```java
public enum Planetas {
    MERCURIO, 
    VENUS, 
    TERRA, 
    MARTE, 
    JUPITER, 
    SATURNO, 
    URANO, 
    NEPTUNO
}
```
Como você pode ver, um Enum em Java é muito semelhante a uma classe. No entanto, em vez de usar a palavra chave 'class', usamos 'enum'. Também devemos representar as constantes, que são separadas por vírgula e todas maiúsculas.

Agora que criamos nosso Enum, podemos começar a usá-lo em nosso código. No exemplo abaixo, definimos um método que recebe um planeta como argumento e imprime uma mensagem. Confira:
```java
public void qualPlaneta(Planetas planeta) {
    switch (planeta) {
        case TERRA:
            System.out.println("Você está na Terra!");
            break;
        default:
            System.out.println("Você não está na Terra!");
            break;
    }
}
```
Na função `qualPlaneta`, podemos passar qualquer um dos planetas definidos em nosso Enum. Se passarmos `TERRA`, a função imprimirá "Você está na Terra!", caso contrário, imprimirá "Você não está na Terra!".

Tratando Dados Categóricos
Um dos usos mais práticos de Enums é para tratar dados categóricos. Dados categóricos são tipos de dados que podem ser divididos em vários grupos ou categorias. A vantagem de usar Enums é que eles podem ajudar a garantir que só vamos usar os valores pré-definidos, prevenindo a ocorrência de erros.

Por exemplo, se estamos criando um aplicativo sobre animais e temos uma categoria chamada 'TipoAnimal', poderíamos usar um Enum para definir essa categoria:
```java
public enum TipoAnimal {
    MAMIFERO,
    REPTIL,
    AVE,
    PEIXE
}
```
Agora, em nosso código, só podemos escolher entre essas quatro opções quando definirmos um tipo de animal, tornando nosso código mais seguro e mais fácil de entender.

Então, para resumir: Enums em Java são uma maneira útil e eficaz de tratar informações estáticas e categóricas em nossos programas. Além disso, eles tornam nosso código mais seguro, visto que só permitimos os valores que já foram definidos no Enum, evitando erros ou confusões.