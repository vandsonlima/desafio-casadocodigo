package com.vandson.desafiocasacodigo.compra.nova.api;

import com.vandson.desafiocasacodigo.cupomDesconto.CupomDesconto;
import com.vandson.desafiocasacodigo.cupomDesconto.CupomDescontoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author Vandson Lima (vandson.vslima@gmail.com)
 * @since 06/08/2020
 **/
@Component
public class CupomDescontoValidator implements Validator {


    @Autowired
    private CupomDescontoRepository cupomDescontoRepository;

    @Override
    public boolean supports(Class<?> aClass) {
        return NovaCompraRequest.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object object, Errors errors) {
        if(errors.hasErrors())
            return;

        NovaCompraRequest novaCompraRequest = (NovaCompraRequest) object;
        if(novaCompraRequest.getCupomDesconto().isPresent()) {
            CupomDesconto cupomDesconto = cupomDescontoRepository.getByCodigo(novaCompraRequest.getCupomDesconto().get());
            if(!cupomDesconto.valido())
                errors.rejectValue("cupom", null, "Este cupom não é válido");
        }
    }
}
