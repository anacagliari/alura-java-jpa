# 14 Para saber mais: Utilizando outra API de tradução

Caso tenha algum problema com a API do ChatGPT, você pode utilizar outra API de tradução. Uma delas, com configuração bem simples, é a [MyMemory](https://mymemory.translated.net/doc/spec.php). Essa é uma API gratuita, com uso limitado a 5000 caracteres por dia.

Para utilizá-la, precisaremos criar algumas classes. Você pode agrupar essas classes em um pacote chamado `traducao`, criado dentro do pacote `service`.

Podemos fazer uma requisição de teste para a API, usando a url: `https://api.mymemory.translated.net/get?q=Hello+world&langpair=en|pt`. Com isso, vemos que os dados de resposta estão estruturados da seguinte forma:

```java
{
  "responseData": {
    "translatedText": "Olá mundo!",
    "match": 1
  },
  "quotaFinished": false,
  "mtLangSupported": null,
  "responseDetails": "",
  "responseStatus": 200,
  "responderId": null,
  "exception_code": null,
  "matches": [
    {
…. 
//código omitido
}
```
Ou seja: temos uma resposta, dada por `responseData`, e dentro de responseData, temos o campo `translatedText`, que é o que desejamos. Portanto, iremos usar duas records para representar os dados. A primeira será `DadosTraducao`, que pegará o campo específico `responseData`:

```java
package br.com.alura.screenmatch.service.traducao;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosTraducao(@JsonAlias(value = "responseData") DadosResposta dadosResposta) {
}
```
Já a segunda record será para pegarmos o campo específico da tradução, que é o translatedText. Essa classe será a `DadosResposta`:

```java
package br.com.alura.screenmatch.service.traducao;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosResposta( @JsonAlias(value = "translatedText") String textoTraduzido) {
}
```
Agora, com as classes de consumo devidamente configuradas, podemos criar uma nova classe para consultar essa API. Podemos chamar a nova classe de `ConsultaMyMemory`. Essa classe terá o código a seguir:

```java
package br.com.alura.screenmatch.service.traducao;

import br.com.alura.screenmatch.service.ConsumoApi;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.net.URLEncoder;

public class ConsultaMyMemory {
    public static String obterTraducao(String text) {
        ObjectMapper mapper = new ObjectMapper();

        ConsumoApi consumo = new ConsumoApi();

        String texto = URLEncoder.encode(text);
        String langpair = URLEncoder.encode("en|pt-br");

        String url = "https://api.mymemory.translated.net/get?q=" + texto + "&langpair=" + langpair;

        String json = consumo.obterDados(url);

        DadosTraducao traducao;
        try {
            traducao = mapper.readValue(json, DadosTraducao.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        return traducao.dadosResposta().textoTraduzido();
    }
}
```
Por fim, precisamos adicionar a mudança na classe `Serie`: ao invés de utilizar a classe `ConsultaChatGPT`, usaremos a classe `ConsultaMyMemory`. Dessa forma, o construtor de Serie ficará assim:

```java
public Serie(DadosSerie dadosSerie){
        this.titulo = dadosSerie.titulo();
        this.totalTemporadas = dadosSerie.totalTemporadas();
        this.avaliacao = OptionalDouble.of(Double.valueOf(dadosSerie.avaliacao())).orElse(0);
        this.genero = Categoria.fromString(dadosSerie.genero().split(",")[0].trim());
        this.atores = dadosSerie.atores();
        this.poster = dadosSerie.poster();
        this.sinopse = ConsultaMyMemory.obterTraducao(dadosSerie.sinopse()).trim();
    }
```
Feitas essas alterações, terminamos a configuração! Você pode executar o projeto e ver a tradução sendo feita. Uma observação importante: lembre-se dos limites da API, citados acima. Caso queira conferir, você pode acessar o [link da documentação](https://mymemory.translated.net/doc/usagelimits.php).

Caso conheça alguma outra API de tradução, fique à vontade para explorar e contar pra gente no fórum como você fez. Te aguardamos por lá!

Agradecemos aos tópicos do fórum: [Alternativa de API para tradução](https://cursos.alura.com.br/forum/topico-projeto-alternativa-de-traducao-a-api-do-chatgpt-351880) e [Alternativa de tradução à API do ChatGPT](https://cursos.alura.com.br/forum/topico-projeto-alternativa-de-api-para-traducao-433467) , que sugeriram o uso da API MyMemory, além de fornecer suas próprias implementações de consumo. Muito obrigada por contribuírem com a comunidade!