package com.vandson.desafiocasacodigo.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

/**
 * @author Vandson Lima (vandsonlima@info.ufrn.br)
 * @since 23/07/2020
 **/
@Entity
@Table
@Getter
@Setter
public class Autor {

    @Id
    @GeneratedValue
    private Long id;

    private LocalDateTime instant = LocalDateTime.now();

    @Email
    private String email;

    @NotEmpty
    private String nome;

    @NotEmpty
    @Size(max = 400)
    private String descricao;


}
