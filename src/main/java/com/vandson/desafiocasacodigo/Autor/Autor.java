package com.vandson.desafiocasacodigo.Autor;

import lombok.Getter;

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

    @Email
    private String email;

    @NotEmpty
    private String nome;

    @NotEmpty
    @Size(max = 400)
    private String descricao;


    public Autor(@NotEmpty @Email String email, @NotBlank String nome, @NotNull @Max(400) String descricao) {
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
