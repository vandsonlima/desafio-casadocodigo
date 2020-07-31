package com.vandson.desafiocasacodigo.livro.detalhesLivro;

import com.vandson.desafiocasacodigo.livro.novoLivro.Livro;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author Vandson (vaondson.vslima@gmail.com)
 * @since 30/07/2020
 **/
//4
@RestController
class DetalharLivroController {

    @PersistenceContext
    private EntityManager entityManager;

    @GetMapping("/livros")
    List<LivroBasicoResponse> recuperarLivros(){
        List<Livro> livros = Optional.of(entityManager.createQuery("SELECT livro FROM Livro livro").getResultList())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "não há livros cadastrados"));
        return livros.stream().map(LivroBasicoResponse::new).collect(Collectors.toList());
    }

    @GetMapping("/livros/{id}")
    DetalheLivroResponse detalharLivro(@PathVariable Long id){
        Livro livro = Optional.ofNullable(entityManager.find(Livro.class, id))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Livro com o id "+id + " não encontrado"));
        return new DetalheLivroResponse(livro);
    }
}
