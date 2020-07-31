package com.vandson.desafiocasacodigo.estado;

import com.vandson.desafiocasacodigo.compartilhado.ExistsId;
import com.vandson.desafiocasacodigo.compartilhado.UniqueValue;
import com.vandson.desafiocasacodigo.pais.Pais;

import javax.persistence.EntityManager;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

/**
 * @author Vandson Lima (vandson.vslima@gmail.com)
 * @since 30/07/2020
 **/
class NovoEstadoRequest {

    @NotBlank
    @UniqueValue(domainClass = Estado.class, fieldName = "nome")
    private String nome;

    @NotNull
    @Positive
    @ExistsId(domainClass = Pais.class)
    private Long idPais;

    NovoEstadoRequest(@NotBlank String nome, @NotNull @Positive Long idPais) {
        this.nome = nome;
        this.idPais = idPais;
    }

    @Override
    public String toString() {
        return "NovoEstadoRequest{" +
                "nome='" + nome + '\'' +
                ", idPais=" + idPais +
                '}';
    }

    public String getNome() {
        return nome;
    }

    public Long getIdPais() {
        return idPais;
    }

    public Estado toModel(EntityManager entityManager) {
        return new Estado(nome, entityManager.find(Pais.class, idPais));
    }
}
