package com.vandson.desafiocasacodigo.compra.nova.dominio;

/**
 * @author Vandson Lima (vandson.vslima@gmail.com)
 * @since 31/07/2020
 **/
public enum StatusCompra {

    PEDIDO("Pedido"),
    PAGAMENTO_CONFIRMADO("Pagamento Confirmado"),
    ENVIADO("enviado");

    private String nome;

    StatusCompra(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }
}
