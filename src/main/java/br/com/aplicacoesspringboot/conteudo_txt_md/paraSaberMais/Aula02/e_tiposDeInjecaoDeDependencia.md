# 11 Para saber mais: Tipos de injeção de dependência

Quando estamos desenvolvendo aplicações Java, frequentemente nos deparamos com a necessidade de criar e gerenciar objetos que dependem de outros objetos. Esse gerenciamento pode ser complicado se não utilizarmos boas práticas, e é aí que entra um conceito fundamental: Injeção de Dependências (Dependency Injection - DI).

Injeção de Dependências é um padrão de design que promove o desacoplamento entre classes, facilitando o gerenciamento, manutenção e testes do código. A ideia principal é que uma classe não precisa criar ou configurar suas próprias dependências. Em vez disso, essas dependências são fornecidas (ou injetadas) por um container de IoC (Inversion of Control), como o Spring.

Vamos usar um exemplo simples. Observe um código sem injeção de dependências:

```java
public class ClienteService {
    private EmailService emailService;

    public ClienteService() {
        this.emailService = new EmailService();
    }

    public void notificarCliente(String mensagem) {
        emailService.enviarEmail(mensagem);
    }
}
```

Aqui, a classe `ClienteService` cria uma instância de `EmailService` internamente. Isso é problemático porque:

- 1. Alto acoplamento: Se `EmailService` mudar, `ClienteService` pode precisar ser alterado.
- 2. Dificuldade em testar: É impossível substituir `EmailService` por um mock sem modificar o código.
Agora observe o mesmo código com injeção de dependências:

```java
public class ClienteService {
    private final EmailService emailService;

    public ClienteService(EmailService emailService) {
        this.emailService = emailService;
    }

    public void notificarCliente(String mensagem) {
        emailService.enviarEmail(mensagem);
    }
}
```

Agora, `ClienteService` não se preocupa mais em criar `EmailService`. Quem usa `ClienteService` é responsável por fornecer essa dependência. Isso reduz o acoplamento e torna o código mais flexível e fácil de testar.

O Spring Framework é um dos frameworks mais populares em Java que utiliza Injeção de Dependências. Ele gerencia os objetos da aplicação e injeta dependências automaticamente com base na configuração.

Assim, quando injetamos uma dependência, o Spring cria automaticamente os objetos, sem que precisemos utilizar explicitamente `new Objeto()`. Para que isso seja possível, a classe deve ser reconhecida pelo Spring. Ou seja, ela deve ter alguma anotação como `@Service`, `@Repository`, `@Component`...

><br> Mas o que acontece em casos que injetamos uma interface, como em `SerieRepository`?<br><br>

O Spring gera a implementação automaticamente e a injeta onde for necessário. Logo, uma vez que ele tem a implementação gerada, é possível instanciar os objetos automaticamente.

Existem várias maneiras de configurar a injeção de dependências no Spring. Uma delas é a que usa @Autowired, que vimos em vídeo, e outra muito popular é a injeção via construtor.

## Uso da anotação `@Autowired`
`@Autowired` é uma anotação do Spring usada para injetar automaticamente dependências em classes.

```java
@Service
public class ClienteService {

    @Autowired
    private EmailService emailService;

    public void notificarCliente(String mensagem) {
        emailService.enviarEmail(mensagem);
    }
}
```

Neste caso, o Spring injeta automaticamente a dependência `EmailService`.

## Injeção via Construtor
Também podemos configurar a injeção de dependências de uma forma explícita, usando o construtor da classe:

```java
@Service
public class ClienteService {

    private final EmailService emailService;

    public ClienteService(EmailService emailService) {
        this.emailService = emailService;
    }

    public void notificarCliente(String mensagem) {
        emailService.enviarEmail(mensagem);
    }
}
```

Aqui, o Spring detecta o construtor da classe e injeta automaticamente a dependência. Repare que, ao usar a anotação `@Autowired`, todo o processo realizado no construtor é feito de uma maneira implícita.

No final das contas, a abordagem de usar o construtor é mais segura e flexível e, por isso, acaba sendo mais utilizada. Para quem está desenvolvendo, é muito mais fácil entender que a classe `ClienteService` precisa de `EmailService` na segunda opção do que na primeira.

No entanto, optamos por usar no curso a primeira opção, pela praticidade que ela oferece. Dessa forma, você será capaz de conhecer as várias abordagens possíveis e compará-las, não levando um susto ao ver um `@Autowired` em algum sistema por aí :)