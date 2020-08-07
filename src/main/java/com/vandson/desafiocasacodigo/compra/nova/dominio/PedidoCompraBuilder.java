package com.vandson.desafiocasacodigo.compra.nova.dominio;

import com.vandson.desafiocasacodigo.cupomDesconto.CupomDesconto;
import com.vandson.desafiocasacodigo.estado.Estado;
import com.vandson.desafiocasacodigo.pais.Pais;

import java.util.List;
import java.util.Objects;

/**
 * @author Vandson Lima (vandson.vslima@gmail.com)
 * @since 31/07/2020
 **/
//4
public final class PedidoCompraBuilder {
    private String email;
    private String nome;
    private String sobrenome;
    private String cpfCnpj;
    private String endereco;
    private String complemento;
    private String cidade;
    private Estado estado;
    private Pais pais;
    private String telefone;
    private String cep;
    private Double total;
    private List<ItemCompra> itensCompra;
    private StatusCompra statusCompra;
    private CupomDesconto cupom;

    private PedidoCompraBuilder() {
    }

    public static PedidoCompraBuilder umPedidoCompra() {
        return new PedidoCompraBuilder();
    }

    public PedidoCompraBuilder comEmail(String email) {
        this.email = email;
        return this;
    }

    public PedidoCompraBuilder comNome(String nome) {
        this.nome = nome;
        return this;
    }

    public PedidoCompraBuilder comSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
        return this;
    }

    public PedidoCompraBuilder comCpfCnpj(String cpfCnpj) {
        this.cpfCnpj = cpfCnpj;
        return this;
    }

    public PedidoCompraBuilder comEndereco(String endereco) {
        this.endereco = endereco;
        return this;
    }

    public PedidoCompraBuilder comComplemento(String complemento) {
        this.complemento = complemento;
        return this;
    }

    public PedidoCompraBuilder comCidade(String cidade) {
        this.cidade = cidade;
        return this;
    }

    public PedidoCompraBuilder comEstado(Estado estado) {
        this.estado = estado;
        return this;
    }

    public PedidoCompraBuilder comPais(Pais pais) {
        this.pais = pais;
        return this;
    }

    public PedidoCompraBuilder comTelefone(String telefone) {
        this.telefone = telefone;
        return this;
    }

    public PedidoCompraBuilder comCep(String cep) {
        this.cep = cep;
        return this;
    }

    public PedidoCompraBuilder comTotal(Double total) {
        this.total = total;
        return this;
    }

    public PedidoCompraBuilder comItensCompra(List<ItemCompra> itensCompra) {
        this.itensCompra = itensCompra;
        return this;
    }

    public PedidoCompraBuilder comStatusCompra(StatusCompra statusCompra){
        this.statusCompra = statusCompra;
        return this;
    }

    public PedidoCompraBuilder comCupom(CupomDesconto cupomDesconto) {
        this.cupom = cupomDesconto;
        return this;
    }

    public PedidoCompra build() {
        PedidoCompra pedidoCompra = new PedidoCompra(email, nome, sobrenome, cpfCnpj, endereco, complemento, cidade, pais, telefone, cep, total, itensCompra, statusCompra);
        if(Objects.nonNull(cupom))
            pedidoCompra.aplicarCupom(cupom);
        if(Objects.nonNull(estado))
            pedidoCompra.setEstado(estado);

        return pedidoCompra;
    }

}
