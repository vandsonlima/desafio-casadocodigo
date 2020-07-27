package com.vandson.desafiocasacodigo.autor;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;

/**
 * @author Vandson Lima (vandson.vslima@gmail.com)
 * @since 23/07/2020
 **/
@RestController
//carga intríseca:2
public class AutorController {

    @PersistenceContext
    private EntityManager entityManager;

    @PostMapping("/autores")
    @Transactional
    public String criar(@RequestBody @Valid AutorRequest autorRequest){
        Autor  novoAutor = autorRequest.toAutor();
        entityManager.persist(novoAutor);
        return  novoAutor.toString();
    }
}
