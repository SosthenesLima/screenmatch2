/*
  By Sósthenes Oliveira lima
 */

package br.com.lima.screenmatch.model;

public enum Categoria {
    ACAO("Action"),
    ROMANCE("Romance"),
    COMEDIA("Comedy"),
    DRAMA("Drama"),
    CRIME("Crime"),
    AVENTURA("Aventura"),
    TERROR("Terror"),
    UNKNOWN("Unknown");

    private String categoriaOmdb;

    Categoria(String categoriaOmdb) {
        this.categoriaOmdb = categoriaOmdb;
    }
    public static Categoria fromString(String text) {
        for (Categoria categoria : Categoria.values()) {
            if (categoria.categoriaOmdb.equalsIgnoreCase(text)) {
                return categoria;
            }
        }
        return Categoria.UNKNOWN;
        //throw new IllegalArgumentException("Nenhuma categoria encontrada para a string fornecida: " + text);
    }

}
