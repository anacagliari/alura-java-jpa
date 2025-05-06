package br.com.aplicacoesspringboot.screenmatch.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.aplicacoesspringboot.screenmatch.model.Serie;

public interface SerieRepository extends JpaRepository<Serie, Long> {
}
// assim, já vai conseguir pegar e salvar coisas no banco de dados 