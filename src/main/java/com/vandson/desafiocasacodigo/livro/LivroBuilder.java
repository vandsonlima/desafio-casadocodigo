package com.vandson.desafiocasacodigo.livro;

import com.vandson.desafiocasacodigo.autor.Autor;
import com.vandson.desafiocasacodigo.categoria.Categoria;
import org.springframework.util.Assert;

import java.time.LocalDate;

/**
 * @author Vandson Lima (vandson.vslima@gmail.com)
 * @since 27/07/2020
 **/
public class LivroBuilder {
    private String titulo;
    private String resumo;
    private String sumario;
    private double preco;
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

    public LivroBuilder withPreco(double preco) {
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
