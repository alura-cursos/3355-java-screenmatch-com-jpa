package br.com.alura.screenmatch.model;

public enum Categoria {
    ACAO("Action", "Ação"),
    ROMANCE("Romance", "Romance"),
    COMEDIA("Comedy", "Comédia"),
    DRAMA("Drama",  "Drama"),
    CRIME("Crime", "Crime"),
    ;

    private String categoriaOmdb;
    private String categoriaPortugues;

    Categoria(String categoriaOmdb, String categoriaPortugues) {
        this.categoriaOmdb = categoriaOmdb;
        this.categoriaPortugues = categoriaPortugues;
    }

    public static Categoria porNome(String nome) {
        for (Categoria categoria : values()) {
            if (categoria.categoriaOmdb.equalsIgnoreCase(nome)) {
                return categoria;
            }
        }
        throw new IllegalArgumentException("Categoria não encontrada: " + nome);
    }

    public static Categoria porCategoriaPortugues(String nome) {
        for (Categoria categoria : values()) {
            if (categoria.categoriaPortugues.equalsIgnoreCase(nome)) {
                return categoria;
            }
        }
        throw new IllegalArgumentException("Categoria não encontrada: " + nome);
    }


}
