package com.vandson.desafiocasacodigo.compra.comprador;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.validation.Valid;

/**
 * @author Vandson (vaondson.vslima@gmail.com)
 * @since 30/07/2020
 **/
//1
@RestController
public class NovoCompradorcontroller {

    private EntityManager entityManager;
    private final ValidatorEstadosPais validatorEstadosPais;

    public NovoCompradorcontroller(EntityManager entityManager, ValidatorEstadosPais validatorEstadosPais) {
        this.entityManager = entityManager;
        this.validatorEstadosPais = validatorEstadosPais;
    }

    @InitBinder
    public void initBinding(WebDataBinder webDataBinder){
        webDataBinder.addValidators(validatorEstadosPais);
    }

    @PostMapping("/cliente")
    @Transactional
    public String criar(@RequestBody @Valid NovoCompradorRequest novoCompradorRequest) {

        return "salvando...";
    }
}
