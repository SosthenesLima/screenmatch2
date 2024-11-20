package br.com.lima.screenmatch.service;

public interface IconverteDados {
    <T> T obterDados (String json, Class<T> classe);

}
