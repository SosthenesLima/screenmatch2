package br.com.lima.screenmatch.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosTemprada(@JsonAlias("Season") Integer numero,
                            @JsonAlias("Episodes") List<DadosEpisodio> episodios) {
}
