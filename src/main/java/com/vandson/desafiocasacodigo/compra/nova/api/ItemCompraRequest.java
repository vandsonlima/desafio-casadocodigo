package com.vandson.desafiocasacodigo.compra.nova.api;

import com.vandson.desafiocasacodigo.compartilhado.ExistsId;
import com.vandson.desafiocasacodigo.compra.nova.dominio.ItemCompra;
import com.vandson.desafiocasacodigo.compra.nova.dominio.PedidoCompra;
import com.vandson.desafiocasacodigo.livro.novoLivro.Livro;
import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.util.List;

/**
 * @author Vandson Lima (vandson.vslima@gmail.com)
 * @since 31/07/2020
 **/
//2
public class ItemCompraRequest {

    @ExistsId(domainClass = Livro.class)
    private Long idLivro;

    @NotNull
    @Positive
    private Integer quantidade;

    public ItemCompraRequest(Long idLivro, @NotNull @Positive Integer quantidade) {
        this.idLivro = idLivro;
        this.quantidade = quantidade;
    }

    public Long getIdLivro() {
        return idLivro;
    }

    public ItemCompra toModel(EntityManager entityManager) {
        Assert.notNull(idLivro, "O livro é de preenchimento obrigatório");
        Assert.isTrue(quantidade > 0, "A quantidade deve ser maior que zero");

        Livro livro = entityManager.find(Livro.class, idLivro);
        return new ItemCompra(quantidade, livro, livro.getPreco());
    }
}
