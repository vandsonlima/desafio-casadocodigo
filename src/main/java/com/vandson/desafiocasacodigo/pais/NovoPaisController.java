package com.vandson.desafiocasacodigo.pais;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;

/**
 * @author Vandson (vaondson.vslima@gmail.com)
 * @since 30/07/2020
 **/
@RestController
class NovoPaisController {

    @PersistenceContext
    private EntityManager entityManager;

    @PostMapping("/pais")
    @Transactional
    public String criar(@RequestBody @Valid NovoPaisRequest novoPaisRequest) {
        Pais pais = new Pais(novoPaisRequest.getNome());
        entityManager.persist(pais);
        return pais.toString();
    }
}
