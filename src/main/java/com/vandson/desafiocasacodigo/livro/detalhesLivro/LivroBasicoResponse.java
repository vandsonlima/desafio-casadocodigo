package com.vandson.desafiocasacodigo.livro.detalhesLivro;

import com.vandson.desafiocasacodigo.livro.novoLivro.Livro;
import org.springframework.util.Assert;

import javax.validation.constraints.NotNull;

/**
 * @author Vandson Lima (vandson.vslima@gmail.com)
 * @since 30/07/2020
 **/
class LivroBasicoResponse {

    @NotNull
    private Long id;

    @NotNull
    private String titulo;

    LivroBasicoResponse(Livro livro) {
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
