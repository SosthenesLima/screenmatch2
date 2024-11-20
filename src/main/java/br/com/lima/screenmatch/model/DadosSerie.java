package br.com.lima.screenmatch.model;

import com.fasterxml.jackson.annotation.JsonAlias;


public record DadosSerie(@JsonAlias("Title") String titulo,
                         @JsonAlias("totalSeasons") Integer totalTempadas,
                         @JsonAlias("imdbRating") String avaliacao) {
}
