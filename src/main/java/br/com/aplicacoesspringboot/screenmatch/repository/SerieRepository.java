package br.com.aplicacoesspringboot.screenmatch.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.aplicacoesspringboot.screenmatch.model.Categoria;
import br.com.aplicacoesspringboot.screenmatch.model.Episodio;
import br.com.aplicacoesspringboot.screenmatch.model.Serie;

import java.util.List;
import java.util.Optional;


public interface SerieRepository extends JpaRepository<Serie, Long> {
    Optional<Serie> findByTituloContainingIgnoreCase(String nomeSerie);

    List<Serie> findByAtoresContainingIgnoreCaseAndAvaliacaoGreaterThanEqual(String nomeAtor, Double avaliacaoMinima);

    List<Serie> findTop5ByOrderByAvaliacaoDesc();

    List<Serie> findByGenero(Categoria categoria);
    
    List<Serie> findByTotalTemporadasLessThanEqualAndAvaliacaoGreaterThanEqual(int totalTemporadas, Double avaliacaoMinima);

    @Query("SELECT s FROM Serie s WHERE s.totalTemporadas <= :totalTemporadas AND s.avaliacao >= :avaliacao")
    List<Serie> seriesPorTemporadaEAvaliacao(int totalTemporadas, Double avaliacao);

    @Query("SELECT e FROM Serie s JOIN s.episodios e WHERE e.titulo ILIKE %:trechoEpisodio%")
    List<Episodio> episodiosPorTrecho(String trechoEpisodio);

    @Query("SELECT e FROM Serie s JOIN s.episodios e WHERE s = :serie ORDER BY e.avaliacao DESC LIMIT 5")
    List<Episodio> topEpisodiosPorSerie(Serie serie);

    @Query("SELECT e FROM Serie s JOIN s.episodios e WHERE s = :serie AND YEAR(e.dataLancamento) >= :espisodiosAPartirDoAno")
    List<Episodio> episodiosPorSerieEAno(Serie serie, int espisodiosAPartirDoAno);
}
// aqui não precisa colocar nada, o JpaRepository já tem os métodos prontos para fazer as operações no banco de dados
// só precisa colocar a entidade e o tipo do id dela. assim, já vai conseguir pegar e salvar coisas no banco de dados 

// o findBy é um padrão do Spring Data JPA, que vai fazer a busca no banco de dados

// o findByTituloContainingIgnoreCase é um método que vai buscar o título da série, ignorando maiúsculas e minúsculas

// o findByAtoresContainingIgnoreCaseAndAvaliacaoGreaterThanEqual é um método que vai buscar os atores da série, ignorando maiúsculas e minúsculas, e a avaliação mínima

// o findTop5ByOrderByAvaliacaoDesc é um método que vai buscar as 5 séries com a maior avaliação

// o findByGenero é um método que vai buscar as séries pelo gênero

// o findByTotalTemporadasLessThanEqualAndAvaliacaoGreaterThanEqual é um método que vai buscar as séries com o total de temporadas menor ou igual ao informado e a avaliação maior ou igual à informada

// o seriesPorTemporadaEAvaliacao é um método que vai buscar as séries com o total de temporadas menor ou igual ao informado e a avaliação maior ou igual à informada, usando JPQL

// o episodiosPorTrecho é um método que vai buscar os episódios da série, usando JPQL, onde o título do episódio contém o trecho informado
    // o ILIKE é um operador do PostgreSQL que faz a busca sem diferenciar maiúsculas e minúsculas, mas o JPA não tem esse operador, então usamos o ILIKE no JPQL
    // o JOIN é um operador do JPQL que faz a junção entre as tabelas, no caso, a tabela de séries e a tabela de episódios
    // o %:trechoEpisodio% é um parâmetro que vai ser passado na hora de fazer a busca, e o % é um caractere curinga que representa qualquer sequência de caracteres