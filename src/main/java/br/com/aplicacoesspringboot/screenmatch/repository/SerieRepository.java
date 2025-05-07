package br.com.aplicacoesspringboot.screenmatch.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.aplicacoesspringboot.screenmatch.model.Categoria;
import br.com.aplicacoesspringboot.screenmatch.model.Serie;

import java.util.List;
import java.util.Optional;


public interface SerieRepository extends JpaRepository<Serie, Long> {
    Optional<Serie> findByTituloContainingIgnoreCase(String nomeSerie);

    List<Serie> findByAtoresContainingIgnoreCaseAndAvaliacaoGreaterThanEqual(String nomeAtor, Double avaliacaoMinima);

    List<Serie> findTop5ByOrderByAvaliacaoDesc();

    List<Serie> findByGenero(Categoria categoria);

    // buscar sério com numero X máximo de temporadas com avaliação maior ou igual a determinado valor
    List<Serie> findByNumeroTemporadasLessThanEqualAndAvaliacaoGreaterThanEqual(Integer totalTemporadas, Double avaliacaoMinima);
}
// aqui não precisa colocar nada, o JpaRepository já tem os métodos prontos para fazer as operações no banco de dados
// só precisa colocar a entidade e o tipo do id dela
// assim, já vai conseguir pegar e salvar coisas no banco de dados 

// o findBy é um padrão do Spring Data JPA, que vai fazer a busca no banco de dados

// o findByTituloContainingIgnoreCase é um método que vai buscar o título da série, ignorando maiúsculas e minúsculas

// o findByAtoresContainingIgnoreCaseAndAvaliacaoGreaterThanEqual é um método que vai buscar os atores da série, ignorando maiúsculas e minúsculas, e a avaliação mínima
