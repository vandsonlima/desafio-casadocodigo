package com.vandson.desafiocasacodigo.autor;

import lombok.Getter;
import org.springframework.util.Assert;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDateTime;

/**
 * @author Vandson Lima (vandson.vslima@gmail.com)
 * @since 23/07/2020
 **/
@Entity
@Table
@Getter
//carga intríseca: 1
public class Autor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime instant = LocalDateTime.now();

    private @Email @NotBlank @Column(unique = true) String email;
    private @NotBlank String nome;
    private @NotBlank @Size(max = 400) String descricao;

    public Autor(@Email @NotBlank String email, @NotBlank String nome, @NotNull @Max(400) String descricao) {
        Assert.hasLength(email, "email não pode ser vazio");
        Assert.hasLength(nome, "nome não pode ser vazio");
        Assert.hasLength(descricao, "descrição não pode ser vazio");
        Assert.isTrue(descricao.length() <= 400, "a descrição deve conter entre 1 e 400");

        this.email = email;
        this.nome = nome;
        this.descricao = descricao;
    }

    @Override
    public String toString() {
        return "Autor{" +
                "id=" + id +
                ", instant=" + instant +
                ", email='" + email + '\'' +
                ", nome='" + nome + '\'' +
                ", descricao='" + descricao + '\'' +
                '}';
    }
}
