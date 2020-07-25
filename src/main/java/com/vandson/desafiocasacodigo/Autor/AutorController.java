package com.vandson.desafiocasacodigo.Autor;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
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
//carga intríseca:3
public class AutorController {

    @PersistenceContext
    private EntityManager entityManager;
    private final ProibeEmailDuplicadoValidator proibeEmailDuplicadoValidator;

    public AutorController(ProibeEmailDuplicadoValidator proibeEmailDuplicadoValidator) {
        this.proibeEmailDuplicadoValidator = proibeEmailDuplicadoValidator;
    }

    @InitBinder
    public void initBinder(WebDataBinder webDataBinder){
        webDataBinder.addValidators(proibeEmailDuplicadoValidator);

    }

    @PostMapping("/autores")
    @Transactional
    public String criar(@RequestBody @Valid AutorRequest autorRequest){
        Autor  novoAutor = autorRequest.toAutor();
        entityManager.persist(novoAutor);
        return  novoAutor.toString();
    }
}
