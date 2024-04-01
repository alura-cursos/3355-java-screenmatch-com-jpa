package br.com.alura.screenmatch.model;

public enum Categoria {
    ACAO("Action"),
    ROMANCE("Romance"),
    COMEDIA("Comedy"),
    DRAMA("Drama"),
    CRIME("Crime"),
    ;

    private String categoriaOmdb;

    Categoria(String categoriaOmdb) {
        this.categoriaOmdb = categoriaOmdb;
    }

    public static Categoria porNome(String nome) {
        for (Categoria categoria : values()) {
            if (categoria.categoriaOmdb.equalsIgnoreCase(nome)) {
                return categoria;
            }
        }
        throw new IllegalArgumentException("Categoria não encontrada: " + nome);
    }

}
