package com.vandson.desafiocasacodigo.livro.novoLivro;

import com.vandson.desafiocasacodigo.autor.Autor;
import com.vandson.desafiocasacodigo.categoria.Categoria;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * @author Vandson Lima (vandson.vslima@gmail.com)
 * @since 27/07/2020
 **/
class LivroBuilder {
    private String titulo;
    private String resumo;
    private String sumario;
    private @NotNull @DecimalMin(value = "20") BigDecimal preco;
    private int numeroPaginas;
    private String isbn;
    private LocalDate dataPublicacao;
    private Categoria categoria;
    private Autor autor;

    /**
     * Utilizar o método #asLivro() que retorna uma nova
     * instância do builder
     */
    private LivroBuilder() {
    }

    public static LivroBuilder aLivro() {
        return new LivroBuilder();
    }

    public LivroBuilder withTitulo(String titulo) {
        this.titulo = titulo;
        return this;
    }

    public LivroBuilder withResumo(String resumo) {
        this.resumo = resumo;
        return this;
    }

    public LivroBuilder withSumario(String sumario) {
        this.sumario = sumario;
        return this;
    }

    public LivroBuilder withPreco(@NotNull @DecimalMin(value = "20") BigDecimal preco) {
        this.preco = preco;
        return this;
    }

    public LivroBuilder withNumeroPaginas(int numeroPaginas) {
        this.numeroPaginas = numeroPaginas;
        return this;
    }

    public LivroBuilder withIsbn(String isbn) {
        this.isbn = isbn;
        return this;
    }

    public LivroBuilder withDataPublicacao(LocalDate dataPublicacao) {
        this.dataPublicacao = dataPublicacao;
        return this;
    }

    public LivroBuilder withCategoria(Categoria categoria) {
        this.categoria = categoria;
        return this;
    }

    public LivroBuilder withAutor(Autor autor) {
        this.autor = autor;
        return this;
    }

    public Livro build() {
        return new Livro(titulo, resumo, sumario, preco, numeroPaginas, isbn, dataPublicacao, categoria, autor);
    }
}
