package com.vandson.desafiocasacodigo.livro.detalhesLivro;

import com.vandson.desafiocasacodigo.livro.novoLivro.Livro;
import org.springframework.util.Assert;

import java.time.LocalDate;

/**
 * @author Vandson Lima (vandson.vslima@gmail.com)
 * @since 30/07/2020
 **/
class DetalheLivroResponse {

    private String titulo;
    private String resumo;
    private String sumario;
    private double preco;
    private int numeroPaginas;
    private String isbn;
    private LocalDate dataPublicacao;
    private Long idCategoria;
    private String nomeCategoria;
    private Long idAutor;
    private String nomeAutor;
    private String descricaoAutor;

    @Deprecated
    DetalheLivroResponse(){

    }

    public DetalheLivroResponse(Livro livro) {

        Assert.notNull(livro.getAutor(), "O autor precisa vir preenchida");
        Assert.notNull(livro.getCategoria(), "A categoria precisa vir preenchida");

        this.titulo = livro.getTitulo();
        this.resumo = livro.getResumo();
        this.sumario = livro.getSumario();
        this.preco = livro.getPreco();
        this.numeroPaginas = livro.getNumeroPaginas();
        this.isbn = livro.getIsbn();
        this.dataPublicacao = livro.getDataPublicacao();
        this.idCategoria = livro.getCategoria().getId();
        this.nomeCategoria = livro.getCategoria().getNome();
        this.idAutor = livro.getAutor().getId();
        this.nomeAutor = livro.getAutor().getNome();
        this.descricaoAutor = livro.getAutor().getDescricao();
    }

    public String getTitulo() {
        return titulo;
    }

    public String getResumo() {
        return resumo;
    }

    public String getSumario() {
        return sumario;
    }

    public double getPreco() {
        return preco;
    }

    public int getNumeroPaginas() {
        return numeroPaginas;
    }

    public String getIsbn() {
        return isbn;
    }

    public LocalDate getDataPublicacao() {
        return dataPublicacao;
    }

    public Long getIdCategoria() {
        return idCategoria;
    }

    public String getNomeCategoria() {
        return nomeCategoria;
    }

    public Long getIdAutor() {
        return idAutor;
    }

    public String getNomeAutor() {
        return nomeAutor;
    }

    public String getDescricaoAutor() {
        return descricaoAutor;
    }
}
