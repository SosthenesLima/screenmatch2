/*
  By Sósthenes Oliveira Lima
 */

package br.com.lima.screenmatch.service;

public interface IConverteDados {
    <T> T  obterDados(String json, Class<T> classe);
}