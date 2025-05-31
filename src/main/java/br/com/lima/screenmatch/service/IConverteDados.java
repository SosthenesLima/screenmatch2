/*
  By Sósthenes Oliveira Lima
  Palmas-TO
  2025
 */

package br.com.lima.screenmatch.service;

public interface IConverteDados {
    <T> T  obterDados(String json, Class<T> classe);
}