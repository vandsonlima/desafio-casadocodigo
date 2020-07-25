package com.vandson.desafiocasacodigo.categoria;

import lombok.Getter;
import org.springframework.util.Assert;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

/**
 * @author Vandson Lima (vandson.vslima@gmail.com)
 * @since 24/07/2020
 **/
@Entity
@Table
@Getter
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private @NotBlank String nome;

    @Deprecated
    protected Categoria() {    }

    public Categoria(@NotBlank String nome) {
        Assert.hasLength(nome, "Campo nome não pode ser nulo nem vazio");
        this.nome = nome;
    }

    @Override
    public String toString() {
        return "Categoria{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                '}';
    }
}
