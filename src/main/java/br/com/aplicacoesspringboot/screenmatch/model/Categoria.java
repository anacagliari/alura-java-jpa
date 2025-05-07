package br.com.aplicacoesspringboot.screenmatch.model;

public enum Categoria {
    ACAO("Action", "Ação"),
    AVENTURA("Adventure", "Aventura"),
    ROMANCE("Romance", "Romance"),
    COMEDIA("Comedy", "Comédia"),
    FANTASIA("Fantasy", "Fantasia"),
    DRAMA("Drama", "Drama"),
    CRIME("Crime", "Crime"),
    BIOGRAFIA("Biography", "Biografia"),
    DOCUMENTARIO("Documentary", "Documentário"),
    ANIMACAO("Animation", "Animação"),
    MISTÉRIO("Mystery", "Mistério"),
    SUSPENSE("Thriller", "Suspense"),
    FICCAO_CIENTIFICA("Sci-Fi", "Ficção Científica");

    private String categoriaOmdb;
    private String categoriaEmPortugues;

    Categoria(String categoriaOmdb, String categoriaEmPortugues) {
        this.categoriaOmdb = categoriaOmdb;
        this.categoriaEmPortugues = categoriaEmPortugues;
    }

    public static Categoria fromString(String text) {
        for (Categoria categoria : Categoria.values()) {
            if (categoria.categoriaOmdb.equalsIgnoreCase(text)) {
                return categoria;
            }
        }
        throw new IllegalArgumentException("Nenhuma categoria encontrada para a string fornecida: " + text);
    }

    public static Categoria fromPortuguese(String text) {
        for (Categoria categoria : Categoria.values()) {
            if (categoria.categoriaEmPortugues.equalsIgnoreCase(text)) {
                return categoria;
            }
        }
        throw new IllegalArgumentException("Nenhuma categoria encontrada para a string fornecida: " + text);
    }
}
