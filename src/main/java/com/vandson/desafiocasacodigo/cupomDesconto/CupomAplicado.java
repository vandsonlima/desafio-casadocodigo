package com.vandson.desafiocasacodigo.cupomDesconto;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.time.LocalDateTime;

/**
 * @author Vandson Lima (vandson.vslima@gmail.com)
 * @since 07/08/2020
 **/
@Embeddable
public class CupomAplicado {

    @ManyToOne
    @JoinColumn(name = "id_cupom_desconto")
    private CupomDesconto cupomDesconto;

    @Positive
    @Column(columnDefinition = "integer default 0")
    private int percentual;

    @NotNull
    @Future
    private LocalDateTime validade;

    @Deprecated
    protected CupomAplicado() {
    }

    public CupomAplicado(CupomDesconto cupomDesconto) {
        this.cupomDesconto = cupomDesconto;
        this.percentual = cupomDesconto.getPercentual();
        this.validade = cupomDesconto.getValidade();
    }

    @Override
    public String toString() {
        return "CupomAplicado{" +
                ", percentual=" + percentual +
                ", validade=" + validade +
                '}';
    }

    public int getPercentual() {
        return percentual;
    }
}
