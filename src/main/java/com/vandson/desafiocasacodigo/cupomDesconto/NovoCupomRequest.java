package com.vandson.desafiocasacodigo.cupomDesconto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.vandson.desafiocasacodigo.compartilhado.UniqueValue;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author Vandson Lima (vandson.vslima@gmail.com)
 * @since 06/08/2020
 **/
public class NovoCupomRequest {

    @NotNull
    @UniqueValue(domainClass = CupomDesconto.class , fieldName = "codigo")
    private String codigo;

    @Positive
    private int percentual;

    @Future
    private LocalDateTime validade;

    public NovoCupomRequest(@NotNull String codigo, @Positive int percentual, @Future LocalDateTime validade) {
        this.codigo = codigo;
        this.percentual = percentual;
        this.validade = validade;
    }

    public CupomDesconto toModel() {
       return new CupomDesconto(codigo, percentual, validade);
    }
}
