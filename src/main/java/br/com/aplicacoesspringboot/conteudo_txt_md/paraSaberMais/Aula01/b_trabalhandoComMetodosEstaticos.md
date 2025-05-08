# 08 Para saber mais: trabalhando com métodos estáticos

Em Java, um método estático é um método que pertence à classe e não a um objeto específico da classe. Traduzindo para uma linguagem mais simples: um método estático é aquele que pode ser usado mesmo que você não tenha criado nenhum objeto da classe.

Se você precisar de um método que não precisa interagir com os atributos ou outros métodos de um objeto, criar um método estático pode ser uma solução elegante e eficiente. Por exemplo, você poderia criar um método estático para calcular a média de um conjunto de números.

Além disso, os métodos estáticos são amplamente utilizados em bibliotecas auxiliares, como a classe Math e a classe Arrays do Java. Em vez de requerer que o usuário crie um objeto para usar os métodos, os métodos são estáticos, e assim podem ser acessados diretamente a partir da classe.

Como eu os utilizo em Java?
Vamos a um código de exemplo para entender isso melhor:

```java
public class MathUtils {
    public static int add(int a, int b) {
        return a + b;
    }
}
```

No código acima, temos um método estático "add" que soma dois números. Note que utilizamos a palavra-chave `static` para defini-lo. E como utilizamos ele? Aí vai:

```java
int result = MathUtils.add(5, 10);
```
Viu só? Não precisamos criar um objeto de MathUtils para usar o método "add".

Entender bem como funcionam os métodos estáticos em Java é de grande importância, pois eles permitem concretizar operações sem a necessidade de criar objetos e também são amplamente utilizados em bibliotecas do Java.