package com.vandson.desafiocasacodigo.compra.nova.dominio;

import com.vandson.desafiocasacodigo.cupomDesconto.CupomAplicado;
import com.vandson.desafiocasacodigo.cupomDesconto.CupomDesconto;
import com.vandson.desafiocasacodigo.estado.Estado;
import com.vandson.desafiocasacodigo.pais.Pais;
import org.springframework.util.Assert;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.List;

/**
 * @author Vandson Lima (vandson.vslima@gmail.com)
 * @since 31/07/2020
 **/
@Entity
@Table
//2
public class PedidoCompra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Email
    @NotBlank
    private String email;

    @NotBlank
    private String nome;

    @NotBlank
    private String sobrenome;

    @NotBlank
    private String cpfCnpj;

    @NotBlank
    private String endereco;

    @NotBlank
    private String complemento;

    @NotBlank
    private String cidade;

    @ManyToOne
    @JoinColumn(name = "id_estado")
    private Estado estado;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "id_pais", nullable = false)
    private Pais pais;

    @NotBlank
    private String telefone;

    @NotBlank
    @Pattern(regexp = "^\\d{5}-\\d{3}$", message = "{cep.invalido}")
    private String cep;

    @NotNull
    @Positive
    private Double total;

    @NotEmpty
    @OneToMany(cascade = CascadeType.ALL)
    private List<ItemCompra> itensCompra;

    private StatusCompra statusCompra;

    @Embedded
    private CupomAplicado cupomAplicado;

    @Deprecated
    protected PedidoCompra() {
    }

    protected PedidoCompra(@Email @NotBlank String email,
                           @NotBlank String nome,
                           @NotBlank String sobrenome,
                           @NotBlank String cpfCnpj,
                           @NotBlank String endereco,
                           @NotBlank String complemento,
                           @NotBlank String cidade,
                           @NotNull Pais pais,
                           @NotBlank String telefone,
                           @NotBlank @Pattern(regexp = "^\\d{5}-\\d{3}$", message = "{cep.invalido}") String cep,
                           @NotNull @Positive Double total,
                           @NotEmpty List<ItemCompra> itensCompra, StatusCompra statusCompra) {
        Assert.hasLength(email, "O email não pode ser vazio");
        Assert.hasLength(nome, "O nome não pode ser vazio");
        Assert.hasLength(sobrenome, "O sobrenome não pode ser vazio");
        Assert.hasLength(cpfCnpj, "O cpf/cnpj não pode ser vazio");
        Assert.hasLength(endereco, "O endereço não pode ser vazio");
        Assert.hasLength(complemento, "O complemento não pode ser vazio");
        Assert.hasLength(cidade, "O cidade não pode ser vazio");
        Assert.notNull(pais, "O pais não pode ser vazio");
        Assert.hasLength(telefone, "O telefone não pode estar vazio");
        Assert.hasLength(cep, "O Cep não pode ser vazio");
        Assert.isTrue(total > 0, "O total não pode ser vazio");
        Assert.notEmpty(itensCompra, "A lista de itens não pode ser vazia");

        this.email = email;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.cpfCnpj = cpfCnpj;
        this.endereco = endereco;
        this.complemento = complemento;
        this.cidade = cidade;
        this.pais = pais;
        this.telefone = telefone;
        this.cep = cep;
        this.total = total;
        this.itensCompra = itensCompra;
        this.statusCompra = statusCompra;
    }

    public Long getId() {
        return id;
    }

    public List<ItemCompra> getItensCompra() {
        return itensCompra;
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

    public StatusCompra getStatusCompra() {
        return statusCompra;
    }

    public void aplicarCupom(@NotNull CupomDesconto cupomDesconto){
        Assert.isNull(id, "Cupom só pode ser aplicado em novas compras");
        Assert.isNull(this.cupomAplicado, "O cupom não pode ser substituído");
        Assert.notNull(cupomDesconto, "O cupom não pode ser nulo");
        Assert.isTrue(cupomDesconto.valido(), "O cupom selecionado não é mais válido");

        this.cupomAplicado = new CupomAplicado(cupomDesconto);
        this.total = total - (total * cupomDesconto.getPercentual() * 0.01); //deveria ter usado BigDecimal
    }

    public void setEstado(@NotNull Estado estado) {
        Assert.notNull(estado, "O estado não pode ser nulo");
        Assert.isTrue(estado.pertenceAoPais(this.pais),"O estado não pertence ao país do pedido");
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "PedidoCompra{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", nome='" + nome + '\'' +
                ", sobrenome='" + sobrenome + '\'' +
                ", cpfCnpj='" + cpfCnpj + '\'' +
                ", endereco='" + endereco + '\'' +
                ", complemento='" + complemento + '\'' +
                ", cidade='" + cidade + '\'' +
                ", estado=" + estado +
                ", pais=" + pais +
                ", telefone='" + telefone + '\'' +
                ", cep='" + cep + '\'' +
                ", total=" + total +
                ", itensCompra=" + itensCompra +
                ", statusCompra=" + statusCompra +
                ", cupomAplicado=" + cupomAplicado +
                '}';
    }
}
