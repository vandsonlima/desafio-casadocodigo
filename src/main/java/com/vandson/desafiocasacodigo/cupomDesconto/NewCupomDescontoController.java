package com.vandson.desafiocasacodigo.cupomDesconto;

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
 * @author Vandson (vaondson.vslima@gmail.com)
 * @since 06/08/2020
 **/
@RestController
public class NewCupomDescontoController {

    @PersistenceContext
    private EntityManager entityManager;

    @PostMapping("/cupom")
    @Transactional
    public String criar(@RequestBody @Valid NovoCupomRequest novoCupomRequest) {
        CupomDesconto novoCupomDesconto = novoCupomRequest.toModel();
        entityManager.persist(novoCupomDesconto);
        return novoCupomDesconto.toString();
    }
}
