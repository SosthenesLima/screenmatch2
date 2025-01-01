/*
  By Sósthenes Oliveira lima
 */

package br.com.lima.screenmatch.model;

public enum Categoria {
    ACAO("Action", "Ação"),
    ROMANCE("Romance", "Romance"),
    COMEDIA("Comedy", "Comédia"),
    DRAMA("Drama", "Drama"),
    CRIME("Crime", "Crime"),
    AVENTURA("Aventure", "Aventura"),
    TERROR("Terror", "Terror"),
    UNKNOWN("Unknown");

    private String categoriaOmdb;

    private String catetoriaPortugues;

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
