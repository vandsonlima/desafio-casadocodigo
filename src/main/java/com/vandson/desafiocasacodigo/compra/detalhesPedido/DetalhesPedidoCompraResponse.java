package com.vandson.desafiocasacodigo.compra.detalhesPedido;

import com.vandson.desafiocasacodigo.compra.nova.dominio.PedidoCompra;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Vandson Lima (vandson.vslima@gmail.com)
 * @since 31/07/2020
 **/
//3
public class DetalhesPedidoCompraResponse {
    private String status;
    private String nome;
    private String cpfCnpj;
    private Double total;
    private List<ItemCompraResponse> itensComprados;

    public DetalhesPedidoCompraResponse(String status, String nome, String cpfCnpj, Double total, List<ItemCompraResponse> itensComprados) {
        this.status = status;
        this.nome = nome;
        this.cpfCnpj = cpfCnpj;
        this.total = total;
        this.itensComprados = itensComprados;
    }

    public DetalhesPedidoCompraResponse() {
    }

    public DetalhesPedidoCompraResponse fromModel(PedidoCompra pedidoCompra){
        var itensComprados = pedidoCompra.getItensCompra().stream()
                .map(itemCompra -> new ItemCompraResponse(itemCompra.getLivro().getTitulo(), itemCompra.getQuantidade()))
                .collect(Collectors.toList());

        return new DetalhesPedidoCompraResponse(pedidoCompra.getStatusCompra().getNome(),
                pedidoCompra.getNome(), pedidoCompra.getCpfCnpj(), pedidoCompra.getTotal(),
                itensComprados);
    }

    public String getStatus() {
        return status;
    }

    public String getNome() {
        return nome;
    }

    public String getCpfCnpj() {
        return cpfCnpj;
    }

    public Double getTotal() {
        return total;
    }

    public List<ItemCompraResponse> getItensComprados() {
        return itensComprados;
    }
}
