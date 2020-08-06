package com.vandson.desafiocasacodigo.livro.novoLivro;

import com.vandson.desafiocasacodigo.autor.Autor;
import com.vandson.desafiocasacodigo.categoria.Categoria;
import org.springframework.util.Assert;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * @author Vandson Lima (vandson.vslima@gmail.com)
 * @since 27/07/2020
 **/
@Entity
@Table
public class Livro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(unique = true)
    private String titulo;

    @NotBlank
    @Size(max = 500)
    private String resumo;

    private String sumario;

    private @NotNull @DecimalMin(value = "20") BigDecimal preco;

    @NotNull
    @Min(value = 100)
    private int numeroPaginas;

    @NotBlank
    @Column(unique = true)
    private String isbn;

    @NotNull
    @Future
    private LocalDate dataPublicacao;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "id_categoria", nullable = false, foreignKey = @ForeignKey(name = "id_categoria_fk"))
    private Categoria categoria;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "id_autor", nullable = false, foreignKey = @ForeignKey(name = "id_autor_fk"))
    private Autor autor;

     /**
     * O construtor deve ser utilizado somente pelo builder para
     * construir objetos válidos
     *
     * @param titulo
     * @param resumo
     * @param sumario
     * @param preco
     * @param numeroPaginas
     * @param isbn
     * @param dataPublicacao
     * @param categoria
     * @param autor
     */
    Livro(@NotBlank String titulo,
          @NotBlank @Size String resumo,
          String sumario,
          @NotNull @DecimalMin(value = "20") BigDecimal preco,
          int numeroPaginas,
          @NotBlank String isbn,
          @NotNull @Future LocalDate dataPublicacao,
          @NotNull Categoria categoria,
          @NotNull Autor autor) {

        Assert.hasLength(titulo, "O título é obrigatório");
        Assert.hasLength(titulo, "O resumo é obrigatório");
        Assert.isTrue(resumo.length() <= 500, "O resumo deve ter no máximo 500 caracteres");
        Assert.isTrue(preco.doubleValue() >= 20, "O preco deve ser maior ou igual a 20.00");
        Assert.isTrue(numeroPaginas >= 100, "O número de páginas deve ser maior ou igual a 100");
        Assert.hasLength(isbn, "isbn é obritatório");
        Assert.notNull(dataPublicacao, "data publicação é obritatório");
        Assert.notNull(categoria, "Categoria é obrigatória");
        Assert.notNull(autor, "Autor é obrigatório");

        this.titulo = titulo;
        this.resumo = resumo;
        this.sumario = sumario;
        this.preco = preco;
        this.numeroPaginas = numeroPaginas;
        this.isbn = isbn;
        this.dataPublicacao = dataPublicacao;
        this.categoria = categoria;
        this.autor = autor;
    }

    @Deprecated
    protected Livro() {
    }

    @Override
    public String toString() {
        return "Livro{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", resumo='" + resumo + '\'' +
                ", sumario='" + sumario + '\'' +
                ", preco=" + preco +
                ", numeroPaginas=" + numeroPaginas +
                ", isbn='" + isbn + '\'' +
                ", dataPublicacao=" + dataPublicacao +
                ", categoria=" + categoria +
                ", autor=" + autor +
                '}';
    }

    public Long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getResumo() {
        return resumo;
    }

    public String getSumario() {
        return sumario;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public int getNumeroPaginas() {
        return numeroPaginas;
    }

    public String getIsbn() {
        return isbn;
    }

    public LocalDate getDataPublicacao() {
        return dataPublicacao;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public Autor getAutor() {
        return autor;
    }
}
