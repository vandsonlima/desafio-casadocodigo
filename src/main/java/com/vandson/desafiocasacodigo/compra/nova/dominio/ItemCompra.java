package com.vandson.desafiocasacodigo.compra.nova.dominio;

import com.vandson.desafiocasacodigo.livro.novoLivro.Livro;
import org.springframework.util.Assert;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

/**
 * @author Vandson Lima (vandson.vslima@gmail.com)
 * @since 31/07/2020
 **/
//1
@Entity
@Table
public class ItemCompra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Positive
    private Integer quantidade;

    @ManyToOne
    @JoinColumn(name = "id_livro", foreignKey = @ForeignKey(name = "id_livro_item_pedido_fk"))
    private Livro livro;

    @Positive
    private BigDecimal precoMomento;

    public ItemCompra(@NotNull @Positive Integer quantidade, Livro livro, @Positive BigDecimal precoMomento) {
        Assert.notNull(livro, "O livro não pode ser nulo");
        Assert.isTrue(quantidade > 0, "A quantidade deve ser maior que zero");

        this.quantidade = quantidade;
        this.livro = livro;
        this.precoMomento = precoMomento;
    }

    @Deprecated
    protected ItemCompra() {
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public Livro getLivro() {
        return livro;
    }

    public BigDecimal getValor() {
        return precoMomento.multiply(BigDecimal.valueOf(quantidade));
    }
}
