package com.vandson.desafiocasacodigo.cupomDesconto;

import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import javax.persistence.*;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.time.LocalDateTime;

/**
 * @author Vandson Lima (vandson.vslima@gmail.com)
 * @since 06/08/2020
 **/
@Entity
@Table
public class CupomDesconto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(unique = true)
    private String codigo;

    @Positive
    private int percentual;

    @Future
    private LocalDateTime validade;


    public CupomDesconto(@NotNull String codigo, @Positive int percentual, @Future LocalDateTime validade) {
        Assert.hasLength(codigo, "O código não pode ser nulo");
        Assert.isTrue(percentual > 0, "O percentual precisa ser maior que zero");
        Assert.notNull(validade, "Validade não pode ser nulo");
        Assert.isTrue(validade.isAfter(LocalDateTime.now()), "A validade deve ser uma data futura");

        this.codigo = codigo;
        this.percentual = percentual;
        this.validade = validade;
    }

    @Deprecated
    protected CupomDesconto() {
    }

    public Long getId() {
        return id;
    }

    public int getPercentual() {
        return percentual;
    }

    public String getCodigo() {
        return codigo;
    }

    public LocalDateTime getValidade() {
        return validade;
    }

    @Override
    public String toString() {
        return "CupomDesconto{" +
                "id=" + id +
                ", codigo='" + codigo + '\'' +
                ", percentual=" + percentual +
                ", validade=" + validade +
                '}';
    }

    public boolean valido() {
        return validade.isAfter(LocalDateTime.now());
    }
}
