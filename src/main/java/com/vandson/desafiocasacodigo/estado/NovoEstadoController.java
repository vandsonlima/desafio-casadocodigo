package com.vandson.desafiocasacodigo.estado;

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
class NovoEstadoController {

    @PersistenceContext
    private EntityManager entityManager;

    @PostMapping("/estados")
    @Transactional
    public String criar(@RequestBody @Valid NovoEstadoRequest novoEstadoRequest) {
        Estado novoEstado = novoEstadoRequest.toModel(entityManager);
        entityManager.persist(novoEstado);
        return novoEstado.toString();
    }
}
