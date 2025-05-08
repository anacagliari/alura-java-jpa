- ### 04 Desafio: adicionando mais propriedades à série

Nossa classe `DadosSerie` não está refletindo muito bem todas as informações que queremos buscar e armazenar na nossa aplicação. Já que iremos gravar séries no banco, é ideal que ela descreva mais os dados, tendo mais atributos. Por isso, te desafiamos a incrementar a busca e adicionar dados como:

```java
Gênero;
Atores;
Pôster;
Sinopse.
```
Propomos que você faça o mapeamento e rode a aplicação novamente. Verifique se todos os dados foram salvos corretamente. No próximo vídeo, mostramos a nossa implementação sendo executada.

---
- ### 16 Desafio: hora da prática

Durante a modelagem da classe Série, exploramos vários recursos diferentes do Java. Para que você explore ainda mais, criamos uma lista de exercícios, onde você poderá exercitar todo o conhecimento adquirido ao longo da aula.

1 - Imagine que você tem uma lista de strings. Algumas das strings são números, mas outras não. Queremos converter a lista de strings para uma lista de números. Se a conversão falhar, você deve ignorar o valor. Por exemplo, na lista a seguir, a saída deve ser [10, 20]:

```java
List<String> input = Arrays.asList("10", "abc", "20", "30x");
```

2 - Implemente um método que recebe um número inteiro dentro de um `Optional`. Se o número estiver presente e for positivo, calcule seu quadrado. Caso contrário, retorne `Optional.empty`.

```java
public class Main {
    public static void main(String[] args) {
       System.out.println(processaNumero(Optional.of(5))); // Saída: Optional[25]
       System.out.println(processaNumero(Optional.of(-3))); // Saída: Optional.empty
       System.out.println(processaNumero(Optional.empty())); // Saída: Optional.empty


}
public static Optional<Integer> processaNumero(Optional<Integer> numero) {
    // Implementar aqui
}
}
```

3 - Implemente um método que recebe uma String representando um nome completo separado por espaços. O método deve retornar o primeiro e o último nome após remover os espaços desnecessários.

```java
public class Main {
    public static void main(String[] args) {
        System.out.println(obterPrimeiroEUltimoNome("João Carlos Silva")); // Saída: "João Silva"
        System.out.println(obterPrimeiroEUltimoNome("Maria")); // Saída: "Maria"
    }

    public static String obterPrimeiroEUltimoNome(String nomeCompleto) {
        // Implementar aqui
    }

}
```

4 - Implemente um método que verifica se uma frase é um palíndromo. Um palíndromo é uma palavra/frase que, quando lida de trás pra frente, é igual à leitura normal. Um exemplo:

```java
public class Main {
    public static void main(String[] args) {
        System.out.println(ehPalindromo("subi no onibus em marrocos")); // Saída: true
        System.out.println(ehPalindromo("Java")); // Saída: false
    }

    public static boolean ehPalindromo(String palavra) {
        // Implementar aqui
    }

}
```
5 - Implemente um método que recebe uma lista de e-mails (String) e retorna uma nova lista onde cada e-mail está convertido para letras minúsculas.

```java
public class Main {
    public static void main(String[] args) {
        List<String> emails = Arrays.asList("TESTE@EXEMPLO.COM", "exemplo@Java.com ", "Usuario@teste.Com");
        System.out.println(normalizeEmails(emails));
        // Saída: ["teste@exemplo.com", "exemplo@java.com", "usuario@teste.com"]
    }

    public List<String> converterEmails(List<String> emails) {
        // Implementar aqui
    }
}
```
6 - Crie um enum `Mes` que represente os meses do ano. Adicione um método que retorna o número de dias de cada mês, considerando anos não bissextos.

```java
public enum Mes {
    // Definir os valores

    public int getNumeroDeDias() {
        // Implementar aqui
    }
}
```
Para chamar o método:

```java
System.out.println(Mes.FEVEREIRO.getNumeroDeDias()); // 28
System.out.println(Mes.JULHO.getNumeroDeDias()); // 31
```
7 - Crie um enum `Moeda` com valores como `DOLAR`, `EURO`, `REAL`. Cada moeda deve ter uma taxa de conversão para reais. Adicione um método que recebe um valor em reais e retorna o valor convertido para a moeda.

```java
public enum Moeda {
    // Definir os valores

    public double converterPara(double valorEmReais) {
        // Implementar aqui
    }
}
```
Para chamar o método:

```java
System.out.println(Moeda.DOLAR.converterPara(100)); // 19.60 (aproximado)
System.out.println(Moeda.EURO.converterPara(100)); // 18.18 (aproximado)
```
8 - Crie um enum `CodigoErro` com valores de erros HTTP, como `NOT_FOUND`, `BAD_REQUEST`, `INTERNAL_SERVER_ERROR`. Cada valor deve ter um código numérico e uma descrição associados. Adicione métodos para acessar o código e a descrição. Dica: consulte o [site cat](https://http.cat/) para conhecer os vários erros HTTP e conseguir descrevê-los melhor.

```java
public enum CodigoErro {
    // Definir os valores
    ;

    public int getCodigo() {
        // Implementar aqui
    }

    public String getDescricao() {
        // Implementar aqui
    }
}
```
Para chamar o método:

```java
System.out.println(CodigoErro.NOT_FOUND.getCodigo()); // 404
System.out.println(CodigoErro.BAD_REQUEST.getDescricao()); // Requisição inválida
```
