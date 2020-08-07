package com.vandson.desafiocasacodigo.livro.novoLivro;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.vandson.desafiocasacodigo.autor.Autor;
import com.vandson.desafiocasacodigo.categoria.Categoria;
import com.vandson.desafiocasacodigo.compartilhado.ExistsId;
import com.vandson.desafiocasacodigo.compartilhado.UniqueValue;

import javax.persistence.EntityManager;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * @author Vandson Lima (vandson.vslima@gmail.com)
 * @since 27/07/2020
 **/
class LivroRequest {

    @NotBlank
    @UniqueValue(domainClass = Livro.class, fieldName = "titulo")
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
    @UniqueValue(fieldName = "isbn", domainClass = Livro.class)
    private String isbn;

    @Future
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dataPublicacao;

    @NotNull
    @ExistsId(domainClass = Categoria.class)
    private Long idCategoria;

    @NotNull
    @ExistsId(domainClass = Autor.class)
    private Long idAutor;

    public LivroRequest(@NotBlank String titulo, @NotBlank @Size(max = 500) String resumo, String sumario,
                        @NotNull @DecimalMin(value = "20") BigDecimal preco, @NotNull @Min(value = 100) int numeroPaginas,
                        @NotBlank String isbn, @Future LocalDate dataPublicacao, @NotNull Long idCategoria, @NotNull Long idAutor) {
        this.titulo = titulo;
        this.resumo = resumo;
        this.sumario = sumario;
        this.preco = preco;
        this.numeroPaginas = numeroPaginas;
        this.isbn = isbn;
        this.dataPublicacao = dataPublicacao;
        this.idCategoria = idCategoria;
        this.idAutor = idAutor;
    }

    public Livro toModel(EntityManager manager){
        Autor autor = manager.find(Autor.class, idAutor);
        Categoria categoria = manager.find(Categoria.class, idCategoria);

        return LivroBuilder.aLivro()
                .withAutor(autor)
                .withTitulo(titulo)
                .withCategoria(categoria)
                .withDataPublicacao(dataPublicacao)
                .withIsbn(isbn)
                .withNumeroPaginas(numeroPaginas)
                .withPreco(preco)
                .withResumo(resumo)
                .withSumario(sumario)
                .build();
    }
}
