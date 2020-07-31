package com.vandson.desafiocasacodigo.compra.nova.api;

import com.vandson.desafiocasacodigo.compartilhado.ExistsId;
import com.vandson.desafiocasacodigo.compra.nova.dominio.ItemCompra;
import com.vandson.desafiocasacodigo.compra.nova.dominio.PedidoCompra;
import com.vandson.desafiocasacodigo.compra.nova.dominio.PedidoCompraBuilder;
import com.vandson.desafiocasacodigo.compra.nova.dominio.StatusCompra;
import com.vandson.desafiocasacodigo.estado.Estado;
import com.vandson.desafiocasacodigo.livro.novoLivro.Livro;
import com.vandson.desafiocasacodigo.pais.Pais;
import org.hibernate.validator.constraints.br.CNPJ;
import org.hibernate.validator.constraints.br.CPF;
import org.hibernate.validator.group.GroupSequenceProvider;
import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.List;
import java.util.Objects;
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
                      @NotEmpty @Valid List<ItemCompraRequest> itensNovaCompra) {
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
    }

    @Override
    public String toString() {
        return "NovoClienteRequest{" +
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
                ", total='" + total + '\'' +
                '}';
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

    public List<Long> getListaIdLivros() {
        return itensNovaCompra
                .stream()
                .mapToLong(ItemCompraRequest::getIdLivro)
                .boxed()
                .collect(Collectors.toList());
    }

    public PedidoCompra toModel(EntityManager entityManager){

        List<ItemCompra> itemCompras = itensNovaCompra.stream()
                .map( itemCompraRequest -> itemCompraRequest.toModel(entityManager))
                .collect(Collectors.toList());

        Pais pais = entityManager.find(Pais.class, idPais);
        Estado estado = null;
        if(Objects.nonNull(idEstado)){
            estado = entityManager.find(Estado.class, idEstado);
        }

        return PedidoCompraBuilder.umPedidoCompra()
                .comCep(this.cep)
                .comCidade(this.cidade)
                .comComplemento(this.complemento)
                .comCpfCnpj(this.cpfCnpj)
                .comEmail(this.email)
                .comEndereco(this.endereco)
                .comEstado(estado)
                .comPais(pais)
                .comItensCompra(itemCompras)
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
}
