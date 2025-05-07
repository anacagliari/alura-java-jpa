package br.com.aplicacoesspringboot.screenmatch.model;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.OptionalDouble;

import br.com.aplicacoesspringboot.screenmatch.service.traducao.ConsultaMyMemory;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "series")
public class Serie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String titulo;

    private Integer totalTemporadas;
    private Double avaliacao;

    @Enumerated(EnumType.STRING)
    private Categoria genero;

    private String atores;
    private String poster;
    private String sinopse;

    @OneToMany(mappedBy = "serie", cascade = CascadeType.ALL, fetch = FetchType.EAGER) 
    // mapeia a relação com a classe Episodio, indicando que a classe Episodio possui o atributo serie
        // e que a classe Episodio é a dona da relação, ou seja, ela possui a chave estrangeira. 
    // O cascade ALL indica que todas as operações realizadas na classe Serie serão refletidas na classe Episodio.
        // Isso significa que, se uma série for excluída, todos os episódios associados a ela também serão excluídos.
    // O fetch EAGER indica que os episódios serão carregados junto com a série quando a série for consultada no banco de dados.
        // Isso significa que, ao buscar uma série, todos os episódios associados a ela serão carregados na mesma consulta.
    private List<Episodio> episodios = new ArrayList<>();
    
    public Serie() {
    }
    
    public Serie(DadosSerie dadosSerie) throws UnsupportedEncodingException {
        this.titulo = dadosSerie.titulo();
        this.totalTemporadas = dadosSerie.totalTemporadas();
        this.avaliacao = OptionalDouble.of(Double.valueOf(dadosSerie.avaliacao())).orElse(0);
        this.genero = Categoria.fromString(dadosSerie.genero().split(",")[0].trim());
        // transforma o valor recebido no valor que queremos mostrar, separa os valores
        // pegando apenas a primeira categoria apenas, removendo espaços e pula linhas
        this.atores = dadosSerie.atores();
        this.poster = dadosSerie.poster();
        this.sinopse = ConsultaMyMemory.obterTraducao(dadosSerie.sinopse()).trim();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Episodio> getEpisodios() {
        return episodios;
    }

    public void setEpisodios(List<Episodio> episodios) {
        episodios.forEach(episodio -> episodio.setSerie(this)); // define a série para cada episódio
        // isso é necessário para que a relação entre a série e os episódios seja mantida corretamente
        this.episodios = episodios;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Integer getTotalTemporadas() {
        return totalTemporadas;
    }

    public void setTotalTemporadas(Integer totalTemporadas) {
        this.totalTemporadas = totalTemporadas;
    }

    public Double getAvaliacao() {
        return avaliacao;
    }

    public void setAvaliacao(Double avaliacao) {
        this.avaliacao = avaliacao;
    }

    public Categoria getGenero() {
        return genero;
    }

    public void setGenero(Categoria genero) {
        this.genero = genero;
    }

    public String getAtores() {
        return atores;
    }

    public void setAtores(String atores) {
        this.atores = atores;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getSinopse() {
        return sinopse;
    }

    public void setSinopse(String sinopse) {
        this.sinopse = sinopse;
    }

    @Override
    public String toString() {
        return "SERIE: genero = " + genero + " | " +
                ", titulo = " + titulo + " | " +
                ", total de temporadas = " + totalTemporadas + " | " +
                ", avaliacao = " + avaliacao + " | " +
                ", atores = " + atores + " | " +
                ", poster = " + poster + " | " +
                ", sinopse = " + sinopse + " | " +
                ", episodios = " + episodios;
    }

}
