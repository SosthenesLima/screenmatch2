package br.com.lima.screenmatch.model;

import com.fasterxml.jackson.annotation.JsonAlias;

public record DadosEpisodio(@JsonAlias("Title") String titulo,
                           @JsonAlias("Episode") Integer numero,
                            @JsonAlias("imdbRating") String avaliacao,
                            @JsonAlias("Released") String dataLacamento) {
}
