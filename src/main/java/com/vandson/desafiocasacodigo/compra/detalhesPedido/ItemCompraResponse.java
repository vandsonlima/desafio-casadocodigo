package com.vandson.desafiocasacodigo.compra.detalhesPedido;

/**
 * @author Vandson Lima (vandson.vslima@gmail.com)
 * @since 31/07/2020
 **/
public class ItemCompraResponse {

    private String nomeLivro;
    private Integer quantidade;

    public ItemCompraResponse(String nomeLivro, Integer quantidade) {
        this.nomeLivro = nomeLivro;
        this.quantidade = quantidade;
    }

    public String getNomeLivro() {
        return nomeLivro;
    }

    public Integer getQuantidade() {
        return quantidade;
    }
}
