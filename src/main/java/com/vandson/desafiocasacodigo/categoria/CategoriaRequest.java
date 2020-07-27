package com.vandson.desafiocasacodigo.categoria;

import com.vandson.desafiocasacodigo.compartilhado.UniqueValue;

import javax.validation.constraints.NotBlank;

/**
 * @author Vandson Lima (vandson.vslima@gmail.com)
 * @since 24/07/2020
 **/
//0
public class CategoriaRequest {

    @NotBlank
    @UniqueValue(fieldName = "nome", domainClass = Categoria.class)
    private String nome;

    public CategoriaRequest() {
    }

    public CategoriaRequest(@NotBlank String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return "CategoriaRequest{" +
                "nome='" + nome + '\'' +
                '}';
    }

    public String getNome() {
        return nome;
    }
}
