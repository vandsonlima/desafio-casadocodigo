package com.vandson.desafiocasacodigo.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

/**
 * @author Vandson Lima (vandson.vslima@gmail.com)
 * @since 24/07/2020
 **/
@Getter
@Setter
@ToString
public class AutorRequest {


    private @Email @NotBlank String email;
    private @NotBlank String nome;
    private @NotBlank @Size(max = 400) String descricao;

    public AutorRequest(@Email @NotBlank String email,
                        @NotBlank String nome,
                        @NotBlank @Size(max = 400) String descricao) {
        this.email = email;
        this.nome = nome;
        this.descricao = descricao;
    }

    public Autor toAutor(){
        return  new Autor(email, nome, descricao);
    }
}
