package com.vandson.desafiocasacodigo.compra.detalhesPedido;

import com.vandson.desafiocasacodigo.compra.nova.dominio.PedidoCompra;

import java.util.List;
import java.util.Objects;
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
    private boolean possuiCupom;
    private Double valorCupom;
    private Double valorFinal;
    private List<ItemCompraResponse> itensComprados;

    public DetalhesPedidoCompraResponse(String status, String nome, String cpfCnpj, Double total, boolean possuiCupom, Double valorFinal, List<ItemCompraResponse> itensComprados) {
        this.status = status;
        this.nome = nome;
        this.cpfCnpj = cpfCnpj;
        this.total = total;
        this.possuiCupom = possuiCupom;
        this.valorFinal = valorFinal;
        this.itensComprados = itensComprados;
    }

    public DetalhesPedidoCompraResponse() {
    }

    public DetalhesPedidoCompraResponse fromModel(PedidoCompra pedidoCompra){
        var itensComprados = pedidoCompra.getItensCompra().stream()
                .map(itemCompra -> new ItemCompraResponse(itemCompra.getLivro().getTitulo(), itemCompra.getQuantidade()))
                .collect(Collectors.toList());

        DetalhesPedidoCompraResponse novoDetalhe = new DetalhesPedidoCompraResponse(pedidoCompra.getStatusCompra().getNome(),
           pedidoCompra.getNome(), pedidoCompra.getCpfCnpj(), pedidoCompra.getTotal(),
           Objects.nonNull(pedidoCompra.getCupomAplicado()), pedidoCompra.valorFinal().orElseGet(pedidoCompra::getTotal), itensComprados);

        if(Objects.nonNull(pedidoCompra.getCupomAplicado())){
            novoDetalhe.setValorCupom(pedidoCompra.valorCupom());
        }
        return novoDetalhe;
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

    public void setValorCupom(Double valorCupom) {
        this.valorCupom = valorCupom;
    }

    public boolean isPossuiCupom() {
        return possuiCupom;
    }

    public Double getValorCupom() {
        return valorCupom;
    }

    public Double getValorFinal() {
        return valorFinal;
    }
}
