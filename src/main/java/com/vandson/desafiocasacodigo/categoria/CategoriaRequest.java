package com.vandson.desafiocasacodigo.categoria;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;

/**
 * @author Vandson Lima (vandson.vslima@gmail.com)
 * @since 24/07/2020
 **/
@Getter
@Setter
@ToString
public class CategoriaRequest {

    @NotBlank
    private String nome;

    public CategoriaRequest() {
    }

    public CategoriaRequest(@NotBlank String nome) {
        this.nome = nome;
    }

    public Categoria toCategoria(){
        return new Categoria(nome);
    }
}
