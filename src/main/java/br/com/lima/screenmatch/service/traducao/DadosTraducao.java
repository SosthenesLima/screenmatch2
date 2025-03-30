/*
  By Sósthenes Oliveira Lima
  Paslmas-to 2025
 */

package br.com.lima.screenmatch.service.traducao;


import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosTraducao(@JsonAlias(value = "responseData") DadosResposta dadosResposta) {
}
