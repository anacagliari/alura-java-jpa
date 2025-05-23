package br.com.aplicacoesspringboot.screenmatch.principal;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;

import br.com.aplicacoesspringboot.screenmatch.model.Categoria;
import br.com.aplicacoesspringboot.screenmatch.model.DadosSerie;
import br.com.aplicacoesspringboot.screenmatch.model.DadosTemporada;
import br.com.aplicacoesspringboot.screenmatch.model.Episodio;
import br.com.aplicacoesspringboot.screenmatch.model.Serie;
import br.com.aplicacoesspringboot.screenmatch.repository.SerieRepository;
import br.com.aplicacoesspringboot.screenmatch.service.ConsumoApi;
import br.com.aplicacoesspringboot.screenmatch.service.ConverteDado;

public class Principal {
    private Scanner leitura = new Scanner(System.in);
    private ConsumoApi consumo = new ConsumoApi();
    private ConverteDado conversor = new ConverteDado();
    private final String ENDERECO = "https://www.omdbapi.com/?t=";
    // constante (permite que valores fixos e imutáveis sejam armazenados e
    // utilizados ao longo do código...)
    private final String API_KEY = "&apikey=c6a5853b";
    private SerieRepository repositorio;
    private List<Serie> series = new ArrayList<>();
    private Optional<Serie> serieBusca;

    public Principal(SerieRepository repositorio) {
        this.repositorio = repositorio;
    }

    // Métodos alterados a partir da base do curso Alura Java - JPA
    public void exibeMenu() throws UnsupportedEncodingException {
        var opcao = -1;
        while (opcao != 0) {

            var menu = """
                    [*] Menu de opções:
                    1 - Buscar séries
                    2 - Buscar episódios
                    3 - Listar séries buscadas
                    4 - Buscar série por título
                    5 - Buscar séries por ator
                    6 - TOP 5 séries
                    7 - Buscar séries por categoria
                    8 - Buscar séries por quantidade máxima de temporadas e nota de avaliação mínima
                    9 - Buscar episódio por trecho
                    10 - TOP 5 episódios por série
                    11 - Buscar episódios a partir da data
                    12 - Listar séries buscadas (apenas nome)

                    0 - Sair
                    """;

            System.out.println(menu);
            opcao = leitura.nextInt();
            leitura.nextLine();

            switch (opcao) {
                case 1:
                    buscarSerieWeb();
                    break;
                case 2:
                    buscarEpisodioPorSerie();
                    break;
                case 3:
                    listarSeriesBuscadas();
                    break;
                case 4:
                    buscarSeriePorTitulo();
                    break;
                case 5:
                    buscarSeriesPorAtor();
                    break;
                case 6:
                    top5Series();
                    break;
                case 7:
                    buscarSeriesPorCategoria();
                    break;
                case 8:
                    buscarQuantidadeMaximaTemporadasEavaliacaoMinima();
                    break;
                case 9:
                    buscarEpisodioPorTrecho();
                    break;
                case 10:
                    topEpisodiosPorSerie();
                    break;
                case 11:
                    buscarEpisodioAPartirData();
                    break;
                case 12:
                    listarSeriesBuscadasApenasNome();
                    break;
                case 0:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida");
            }
        }
    }

    private void buscarSerieWeb() throws UnsupportedEncodingException {
        DadosSerie dados = getDadosSerie();
        Serie serie = new Serie(dados);
        if (dados.titulo() != null) {
            // dadosSeries.add(dados);
            repositorio.save(serie);
            System.out.println(dados);
        }
    }

    private DadosSerie getDadosSerie() {
        System.out.println("Digite o nome da série para busca");
        var nomeSerie = leitura.nextLine();
        var json = consumo.obterDados(ENDERECO + nomeSerie.replace(" ", "+") + API_KEY);
        DadosSerie dados = conversor.obterDados(json, DadosSerie.class);
        return dados;
    }

    private void buscarEpisodioPorSerie() {
        listarSeriesBuscadas();
        System.out.println("Digite o nome da série para buscar episódios: ");
        var nomeSerie = leitura.nextLine();

        Optional<Serie> serie = repositorio.findByTituloContainingIgnoreCase(nomeSerie);

        if (serie.isPresent()) {
            var serieEncontrada = serie.get();
            List<DadosTemporada> temporadas = new ArrayList<>();

            for (int i = 1; i <= serieEncontrada.getTotalTemporadas(); i++) {
                var json = consumo.obterDados(
                        ENDERECO + serieEncontrada.getTitulo().replace(" ", "+") + "&season=" + i + API_KEY);
                DadosTemporada dadosTemporada = conversor.obterDados(json, DadosTemporada.class);
                temporadas.add(dadosTemporada);
            }

            temporadas.forEach(System.out::println);

            List<Episodio> episodios = temporadas.stream()
                    .flatMap(d -> d.episodios().stream()
                            .map(e -> new Episodio(d.numero(), e)))
                    .collect(Collectors.toList());

            serieEncontrada.setEpisodios(episodios);

            repositorio.save(serieEncontrada);
        } else {
            System.out.println("Série não encontrada.");
        }
    }

    private void listarSeriesBuscadas() {
        series = repositorio.findAll();
        series.stream()
                .sorted(Comparator.comparing(Serie::getGenero))
                .forEach(System.out::println);
    }

    private void buscarSeriePorTitulo() {
        System.out.println("Digite o nome da série: ");
        var nomeSerie = leitura.nextLine();
        serieBusca = repositorio.findByTituloContainingIgnoreCase(nomeSerie);

        if (serieBusca.isPresent()) {
            System.out.println("[*] Dados da série: " + serieBusca.get());
        } else {
            System.out.println("Série não encontrada.");
        }

    }

    private void buscarSeriesPorAtor() {
        System.out.println("Digite o nome do/a ator/atriz: ");
        var nomeAtor = leitura.nextLine();

        System.out.println("Digite a nota da avaliação: [Vírgula é o separador padrão][A partir de ] ");
        var nota = leitura.nextDouble();

        List<Serie> seriesEncontradas = repositorio
                .findByAtoresContainingIgnoreCaseAndAvaliacaoGreaterThanEqual(nomeAtor, nota);

        if (seriesEncontradas.isEmpty()) {
            System.out.println("Nenhuma série encontrada para o/a ator/atriz " + nomeAtor);
        } else {
            System.out.println("[*] Séries encontradas para o/a ator/atriz " + nomeAtor + ":");
            seriesEncontradas
                    .forEach(s -> System.out.println("# " + s.getTitulo() + " - Avaliacao: " + s.getAvaliacao()));
        }
    }

    private void top5Series() {
        List<Serie> seriesTop = repositorio.findTop5ByOrderByAvaliacaoDesc();
        seriesTop.forEach(s -> System.out.println("[*] " + s.getTitulo() + " - Avaliacao: " + s.getAvaliacao()));
    }

    private void buscarSeriesPorCategoria() {
        System.out.println("Digite o genero da série: ");
        var nomeGenero = leitura.nextLine();

        Categoria categoria = Categoria.fromPortuguese(nomeGenero);

        List<Serie> seriesPorCategoriaEncontradas = repositorio.findByGenero(categoria);

        if (seriesPorCategoriaEncontradas.isEmpty()) {
            System.out.println("Nenhuma série encontrada para a categoria " + nomeGenero);
        } else {
            System.out.println("[*] Séries encontradas para a categoria " + nomeGenero + ":");
            seriesPorCategoriaEncontradas
                    .forEach(s -> System.out.println("# " + s.getTitulo() + " - Avaliacao: " + s.getAvaliacao()));
        }
    }

    private void buscarQuantidadeMaximaTemporadasEavaliacaoMinima() {
        System.out.println("Digite a quantidade máxima de temporadas: ");
        var numeroTemporadas = leitura.nextInt();
        leitura.nextLine();

        System.out.println("Digite a nota da avaliação: [Vírgula é o separador padrão][A partir de ] ");
        var nota = leitura.nextDouble();
        leitura.nextLine();

        List<Serie> seriesEspecificasEncontradas = repositorio.seriesPorTemporadaEAvaliacao(numeroTemporadas, nota);

        System.out.println("[*] Séries encontradas com até " + numeroTemporadas
                + " temporadas e avaliação maior ou igual a " + nota + ": ");
        seriesEspecificasEncontradas
                .forEach(s -> System.out.println("# " + s.getTitulo() + " - Avaliacao: " + s.getAvaliacao()));
    }

    private void buscarEpisodioPorTrecho() {
        System.out.println("Digite o trecho do título do episódio: ");
        var trechoEpisodio = leitura.nextLine();

        List<Episodio> episodiosEncontrados = repositorio.episodiosPorTrecho(trechoEpisodio);

        System.out.println("[*] Episódios encontrados com o trecho [" + trechoEpisodio + "]: ");
        // episodiosEncontrados.forEach(System.out::println); // imprime o toString()
        episodiosEncontrados.forEach(e -> System.out.printf(
                "%s | %s ª Temporada - Ep.: %s | - %s\n",
                e.getSerie().getTitulo(), e.getTemporada(), e.getNumeroEpisodio(), e.getTitulo()));
    }

    private void topEpisodiosPorSerie() {
        buscarSeriePorTitulo();

        if (serieBusca.isPresent()) {
            Serie serie = serieBusca.get();
            List<Episodio> topEpisodios = repositorio.topEpisodiosPorSerie(serie);

            System.out.println("[*] Top 5 episódios: ");

            topEpisodios.forEach(e -> 
                System.out.printf(
                    "%s | %sª Temporada - Ep. %s | %s | Nota: %s\n",
                    e.getSerie().getTitulo(), 
                    e.getTemporada(), 
                    e.getNumeroEpisodio(), 
                    e.getTitulo(), 
                    e.getAvaliacao()
                )
            );
        }
    }

    private void buscarEpisodioAPartirData() {
        buscarSeriePorTitulo();
        if (serieBusca.isPresent()) {
            Serie serie = serieBusca.get();
            System.out.println("A partir de que ano você deseja ver os episódios? ");
            var espisodiosAPartirDoAno = leitura.nextInt();
            leitura.nextLine();
            
            List<Episodio> episodiosAPartirDe = repositorio.episodiosPorSerieEAno(serie, espisodiosAPartirDoAno);
            
            System.out.println("[*] Episódios encontrados: ");

            episodiosAPartirDe.forEach(System.out::println);
        }
    }

    private void listarSeriesBuscadasApenasNome() {
        series = repositorio.findAll();
        series.stream()
                .sorted(Comparator.comparing(Serie::getAvaliacao).reversed())
                .forEach(s -> System.out.println(s.getTitulo()));
    }
}

/*
 * // Método original do curso Alura Java - Spring Boot
 * public void exibeMenu() {
 * System.out.println("Digite o nome da série: ");
 * var nomeSerie = leitura.nextLine();
 * var json = consumo.obterDados(ENDERECO + nomeSerie.replace(" ", "+") +
 * API_KEY);
 * DadosSerie dados = conversor.obterDados(json, DadosSerie.class);
 * System.out.println(dados);
 * 
 * List<DadosTemporada> temporadas = new ArrayList<>();
 * 
 * for (int i = 1; i <= dados.totalTemporadas(); i++) {
 * json = consumo.obterDados(ENDERECO + nomeSerie.replace(" ", "+") + "&Season="
 * + i + API_KEY);
 * DadosTemporada dadosTemporada = conversor.obterDados(json,
 * DadosTemporada.class);
 * temporadas.add(dadosTemporada);
 * }
 * temporadas.forEach(System.out::println); // mesma coisa que:
 * temporadas.forEach(t -> System.out.println(t));
 * 
 * temporadas.forEach(t -> t.episodios().forEach(e ->
 * System.out.println(e.titulo())));
 * // lambda e stream
 ** LAMBDA (funções anônimas)
 * São uma maneira de definir funções que são frequentemente usadas uma única
 * vez, direto no local onde elas serão usadas. Principal vantagem é simplificar
 * o código e mais legível.
 * t: temporadas - e: episodios. - substitui os for dentro de for, descrito
 * abaixo.
 * for (int i = 0; i < dados.totalTemporadas(); i++) {
 * List<DadosEpisodio> episodiosTemporada = temporadas.get(i).episodios();
 * for (int j = 0; j < episodiosTemporada.size(); j++) {
 * System.out.println(episodiosTemporada.get(j).titulo());
 * }
 * }
 ** STREAM
 * Forma de trabalhar com coleções de dados no Java. Elas permitem realizar
 * operações de forma mais eficiente e concisa, utilizando uma abordagem
 * funcional.
 * Uma stream é uma sequência de elementos que pode ser processada em paralelo
 * ou em série. Ela pode ser criada a partir de uma coleção, um array, um
 * arquivo, entre outros.
 * A partir daí, podemos realizar diversas operações nessa stream, como filtrar,
 * mapear, ordenar, entre outras.
 * As operações intermediárias são aquelas que podem ser aplicadas em uma stream
 * e retornam uma nova stream como resultado. Essas operações não são executadas
 * imediatamente,
 * mas apenas quando uma operação final é chamada.
 * List<String> nomes = Arrays.asList("Jacque", "Iasmin", "Paulo", "Rodrigo",
 * "Nico");
 * nomes.stream()
 * .sorted() //ordena os elementos
 * .limit(3) //limita o número de elementos da stream
 * .filter(n -> n.startsWith("N")) //permite filtrar os elementos da stream com
 * base em uma condição. Por exemplo, podemos filtrar uma lista de números para
 * retornar apenas os números pares.
 * .map(n -> n.toUpperCase()) //permite transformar cada elemento da stream em
 * outro tipo de dado. Por exemplo, podemos transformar uma lista de strings em
 * uma lista de seus respectivos tamanhos.
 * .forEach(System.out::println); //permite executar uma ação em cada elemento
 * da stream. Por exemplo, podemos imprimir cada elemento da lista.
 * OUTRAS OPERAÇÕES
 * .collect //permite coletar os elementos da stream em uma coleção ou em outro
 * tipo de dado. Por exemplo, podemos coletar os números pares em um conjunto.
 * .distinct (que remove elementos duplicados)
 * .skip (que pula os primeiros elementos da stream)
 * .reduce (que combina os elementos da stream em um único resultado)
 * 
 * List<DadosEpisodio> dadosEpisodios = temporadas.stream()
 * .flatMap(t -> t.episodios().stream())
 * .collect(Collectors.toList());
 * System.out.println("Top 5 episódios: ");
 * dadosEpisodios.stream()
 * .filter(e -> !e.avaliacao().equalsIgnoreCase("N/A"))
 * .sorted(Comparator.comparing(DadosEpisodio::avaliacao).reversed())
 * .limit(5)
 * .forEach(System.out::println);
 * 
 * List<Episodio> episodios = temporadas.stream()
 * .flatMap(t -> t.episodios().stream()
 * .map(d -> new Episodio(t.numero(), d))
 * ).collect(Collectors.toList());
 * 
 * episodios.forEach(System.out::println);
 * 
 * System.out.println("A partir de que ano você deseja ver os episódios? ");
 * var ano = leitura.nextInt();
 * leitura.nextLine();
 * 
 * LocalDate dataBusca = LocalDate.of(ano, 1, 1);
 * 
 * DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy");
 * 
 * episodios.stream()
 * .filter(e -> e.getDataLancamento() != null &&
 * e.getDataLancamento().isAfter(dataBusca))
 * .forEach(e -> System.out.println(
 * "Temporada: " + e.getTemporada() +
 * " | Episódio: " + e.getTitulo() +
 * " | Data de lançamento: " + e.getDataLancamento().format(formatador)
 * ));
 * }
 */
