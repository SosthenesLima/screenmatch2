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
    UNKNOWN("Unknown", "Desconhecido");

    private String categoriaOmdb;

    private String catetoriaPortugues;

    Categoria(String categoriaOmdb, String catetoriaPortugues) {
        this.categoriaOmdb = categoriaOmdb;
        this.catetoriaPortugues = catetoriaPortugues;
    }
    public static Categoria fromString(String text) {
        for (Categoria categoria : Categoria.values()) {
            if (categoria.categoriaOmdb.equalsIgnoreCase(text)) {
                return categoria;
            }
            throw new IllegalArgumentException("Nenhuma categoria encontrada para a string fornecida: " + text);
        }
        return Categoria.UNKNOWN;
        //throw new IllegalArgumentException("Nenhuma categoria encontrada para a string fornecida: " + text);
    }

    public static Categoria fromPortugues(String text) {
        for (Categoria categoria : Categoria.values()) {
            if (categoria.catetoriaPortugues.equalsIgnoreCase(text)) {
                return categoria;
            }
        }

        throw new IllegalArgumentException("Nenhuma categoria encontrada para a string fornecida: " + text);
        //return Categoria.UNKNOWN;
        //throw new IllegalArgumentException("Nenhuma categoria encontrada para a string fornecida: " + text);
    }
}
