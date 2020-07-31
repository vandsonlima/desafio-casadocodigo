package com.vandson.desafiocasacodigo.livro.novoLivro;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;

/**
 * @author Vandson (vaondson.vslima@gmail.com)
 * @since 27/07/2020
 **/
//2
@RestController
class LivroController {

    @PersistenceContext
    private EntityManager entityManager;

    @PostMapping("/livros")
    @Transactional
    public String criar(@RequestBody @Valid LivroRequest livroRequest) {
        Livro livro = livroRequest.toModel(entityManager);
        entityManager.persist(livro);
        return livro.toString();
    }
}
