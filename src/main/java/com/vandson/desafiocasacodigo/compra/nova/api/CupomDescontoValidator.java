package com.vandson.desafiocasacodigo.compra.nova.api;

import com.vandson.desafiocasacodigo.cupomDesconto.CupomDesconto;
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

    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public boolean supports(Class<?> aClass) {
        return NovaCompraRequest.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object object, Errors errors) {
        if(errors.hasErrors())
            return;

        NovaCompraRequest novaCompraRequest = (NovaCompraRequest) object;
        if(novaCompraRequest.temCupom()) {
            List<CupomDesconto> cupomDescontos = Optional.of(entityManager
                    .createQuery("SELECT cupom FROM CupomDesconto cupom WHERE cupom.codigo =:codigo AND cupom.validade >:dataHoraAtual")
                    .setParameter("codigo", novaCompraRequest.getCupom())
                    .setParameter("dataHoraAtual", LocalDateTime.now())
                    .getResultList()).orElseGet(ArrayList::new);

            if(CollectionUtils.isEmpty(cupomDescontos)){
                errors.rejectValue("cupom", null, "Este cupom não é válido");
            }
        }
    }
}
