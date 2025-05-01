/*
  By Sósthenes Oliveira Lima
  Palmas-To 2025
 */

package br.com.lima.screenmatch.dto;

import br.com.lima.screenmatch.model.Categoria;

public record SerieDTO(Long id, String titulo,
        Integer totalTemporadas,
        Double avaliacao, Categoria genero,
        String atores,
        String poster,
         String sinopse) {

}
