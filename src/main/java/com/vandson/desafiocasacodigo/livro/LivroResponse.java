package com.vandson.desafiocasacodigo.livro;

import org.springframework.util.Assert;

import javax.validation.constraints.NotNull;

/**
 * @author Vandson Lima (vandson.vslima@gmail.com)
 * @since 30/07/2020
 **/
public class LivroResponse {

    @NotNull
    private Long id;

    @NotNull
    private String titulo;

    public LivroResponse(Livro livro) {
        Assert.notNull(livro.getId(), "Não é possível criar um LivroResponse com o id vazio");
        Assert.hasLength(livro.getTitulo() , "Não é possível criar um LivroResponse com o titulo vazio");

        this.id = livro.getId();
        this.titulo = livro.getTitulo();
    }

    public Long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }
}
