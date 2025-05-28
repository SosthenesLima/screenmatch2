/*
 By Sósthenes Oliveira Lima
 Palmas-TO
 2025


 */

package br.com.lima.screenmatch.service.traducao;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosResposta( @JsonAlias(value = "translatedText") String textoTraduzido) {
}
