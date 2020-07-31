package com.vandson.desafiocasacodigo.compra.nova.dominio;

import com.vandson.desafiocasacodigo.livro.novoLivro.Livro;
import org.springframework.util.Assert;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

/**
 * @author Vandson Lima (vandson.vslima@gmail.com)
 * @since 31/07/2020
 **/
//2
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
    @JoinColumn(name = "id_pedido_compra", foreignKey = @ForeignKey(name = "id_pedido_compra_fk"))
    private PedidoCompra pedidoCompra;

    @ManyToOne
    @JoinColumn(name = "id_livro", foreignKey = @ForeignKey(name = "id_livro_item_pedido_fk"))
    private Livro livro;

    public ItemCompra(@NotNull @Positive Integer quantidade, Livro livro) {
        Assert.notNull(livro, "O livro não pode ser nulo");
        Assert.isTrue(quantidade > 0, "A quantidade deve ser maior que zero");

        this.quantidade = quantidade;
        this.livro = livro;
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

    /**
     * FIXME: O pedido está sendo inserido via setter!
     * @param pedidoCompra
     */
    public void setPedidoCompra(PedidoCompra pedidoCompra) {
        this.pedidoCompra = pedidoCompra;
    }
}
