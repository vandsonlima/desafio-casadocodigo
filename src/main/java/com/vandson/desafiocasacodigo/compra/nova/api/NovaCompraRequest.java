package com.vandson.desafiocasacodigo.compra.nova.api;

import com.vandson.desafiocasacodigo.compartilhado.ExistsId;
import com.vandson.desafiocasacodigo.compra.nova.dominio.ItemCompra;
import com.vandson.desafiocasacodigo.compra.nova.dominio.PedidoCompra;
import com.vandson.desafiocasacodigo.compra.nova.dominio.PedidoCompraBuilder;
import com.vandson.desafiocasacodigo.compra.nova.dominio.StatusCompra;
import com.vandson.desafiocasacodigo.cupomDesconto.CupomDesconto;
import com.vandson.desafiocasacodigo.cupomDesconto.CupomDescontoRepository;
import com.vandson.desafiocasacodigo.estado.Estado;
import com.vandson.desafiocasacodigo.pais.Pais;
import org.hibernate.validator.constraints.br.CNPJ;
import org.hibernate.validator.constraints.br.CPF;
import org.hibernate.validator.group.GroupSequenceProvider;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author Vandson Lima (vandson.vslima@gmail.com)
 * @since 30/07/2020
 **/
//10
@GroupSequenceProvider(NovaCompradaSequenceProvider.class)
public class NovaCompraRequest {
    @Email
    @NotBlank
    private String email;

    @NotBlank
    private String nome;

    @NotBlank
    private String sobrenome;

    @CPF(groups = PessoaFisica.class)
    @CNPJ(groups = PessoaJuridica.class)
    @NotBlank
    private String cpfCnpj;

    @NotBlank
    private String endereco;

    @NotBlank
    private String complemento;

    @NotBlank
    private String cidade;

    @ExistsId(domainClass = Estado.class)
    private Long idEstado;

    @ExistsId(domainClass = Pais.class)
    private Long idPais;

    @NotBlank
    private String telefone;

    @NotBlank
    @Pattern(regexp = "^\\d{5}-\\d{3}$", message = "{cep.invalido}")
    private String cep;

    @NotNull
    @Positive
    private Double total;

    @NotEmpty
    @Valid
    private List<ItemCompraRequest> itensNovaCompra;

    @ExistsId(domainClass = CupomDesconto.class, fieldName = "codigo")
    private String cupom;

    NovaCompraRequest(@Email @NotBlank String email,
                      @NotBlank String nome,
                      @NotBlank String sobrenome,
                      @NotBlank String cpfCnpj,
                      @NotBlank String endereco,
                      @NotBlank String complemento,
                      @NotBlank String cidade,
                      Long idEstado,
                      @NotBlank Long idPais,
                      @NotBlank String telefone,
                      @NotBlank String cep,
                      @NotNull @Positive Double total,
                      @NotEmpty @Valid List<ItemCompraRequest> itensNovaCompra, String cupom) {
        this.email = email;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.cpfCnpj = cpfCnpj;
        this.endereco = endereco;
        this.complemento = complemento;
        this.cidade = cidade;
        this.idEstado = idEstado;
        this.idPais = idPais;
        this.telefone = telefone;
        this.cep = cep;
        this.total = total;
        this.itensNovaCompra = itensNovaCompra;
        this.cupom = cupom;
    }

     public String getCpfCnpj() {
        return cpfCnpj;
    }

    public Long getIdPais() {
        return idPais;
    }

    public Long getIdEstado() {
        return idEstado;
    }

    public Double getTotal() {
        return total;
    }

    public PedidoCompra toModel(EntityManager entityManager, CupomDescontoRepository cupomDescontoRepository){
        PedidoCompraBuilder builder = PedidoCompraBuilder.umPedidoCompra();

        List<ItemCompra> itemCompras = itensNovaCompra
                .stream()
                .map( itemCompraRequest -> itemCompraRequest.toModel(entityManager))
                .collect(Collectors.toList());
        builder = builder.comItensCompra(itemCompras);

        if(temEstado())
            builder.comEstado(entityManager.find(Estado.class, idEstado));

        if(StringUtils.hasText(cupom))
            builder.comCupom(cupomDescontoRepository.getByCodigo(cupom));

        return builder
                .comPais(entityManager.find(Pais.class, idPais))
                .comCep(this.cep)
                .comCidade(this.cidade)
                .comComplemento(this.complemento)
                .comCpfCnpj(this.cpfCnpj)
                .comEmail(this.email)
                .comEndereco(this.endereco)
                .comNome(this.nome)
                .comSobrenome(this.sobrenome)
                .comTelefone(this.telefone)
                .comTotal(this.total)
                .comStatusCompra(StatusCompra.PEDIDO)
                .build();
    }

    public List<ItemCompraRequest> getItensNovaCompra() {
        return itensNovaCompra;
    }

    public boolean temEstado() {
        return idEstado != null;
    }

    public Optional<String> getCupomDesconto() {
        return Optional.ofNullable(cupom);
    }

    @Override
    public String toString() {
        return "NovaCompraRequest{" +
                "email='" + email + '\'' +
                ", nome='" + nome + '\'' +
                ", sobrenome='" + sobrenome + '\'' +
                ", cpfCnpj='" + cpfCnpj + '\'' +
                ", endereco='" + endereco + '\'' +
                ", complemento='" + complemento + '\'' +
                ", cidade='" + cidade + '\'' +
                ", idEstado=" + idEstado +
                ", idPais=" + idPais +
                ", telefone='" + telefone + '\'' +
                ", cep='" + cep + '\'' +
                ", total=" + total +
                ", itensNovaCompra=" + itensNovaCompra +
                ", cupom='" + cupom + '\'' +
                '}';
    }
}
