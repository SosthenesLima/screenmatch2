/*
 By Sósthenes Oliveira Lima
 Palmas-TO 2025

 */

package br.com.lima.screenmatch.model;

import br.com.lima.screenmatch.service.traducao.ConsultaMyMemory;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;


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
    private List<Episodios> episodios = new ArrayList<>();

    public Serie() {}

    public Serie(DadosSerie dadosSerie) {
        this.titulo = dadosSerie.titulo();
        this.totalTemporadas = dadosSerie.totalTemporadas();
        this.avaliacao = dadosSerie.avaliacao() != null ? Double.valueOf(dadosSerie.avaliacao()) : 0.0;
        this.genero = Categoria.fromString(dadosSerie.genero().split(",")[0].trim());
        this.atores = dadosSerie.atores();
        this.poster = dadosSerie.poster();
        //this.sinopse = dadosSerie.sinopse(); // Tradução será feita em outro lugar
        this.sinopse = ConsultaMyMemory.obterTraducao(dadosSerie.sinopse()).trim();
    }

    //public void traduzirSinopse(ConsultaChatGPT chatGPTService) {
       // this.sinopse = chatGPTService.obterTraducao(this.sinopse).trim();
   // }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Episodios> getEpisodios() {
        return episodios;
    }

    public void setEpisodios(List<Episodios> episodios) {

        // Ajustando a chave estrangeira
        episodios.forEach(e -> e.setSerie(this));
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
        return "Serie{" +
                "titulo='" + titulo + '\'' +
                ", totalTemporadas=" + totalTemporadas +
                ", avaliacao=" + avaliacao +
                ", genero=" + genero +
                ", atores='" + atores + '\'' +
                ", poster='" + poster + '\'' +
                ", sinopse='" + sinopse + '\'' +
                ", episodios='" + episodios + '\'' +
                '}';
    }
}
