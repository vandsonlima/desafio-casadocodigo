package com.vandson.desafiocasacodigo.autor;

import com.vandson.desafiocasacodigo.compartilhado.UniqueValue;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * @author Vandson Lima (vandson.vslima@gmail.com)
 * @since 24/07/2020
 **/
@Getter
@Setter
@ToString
public class AutorRequest {



    @Email
    @NotBlank
    @UniqueValue(domainClass = Autor.class, fieldName = "email")
    private String email;
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
