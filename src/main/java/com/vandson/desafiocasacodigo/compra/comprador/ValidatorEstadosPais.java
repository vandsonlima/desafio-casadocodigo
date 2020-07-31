package com.vandson.desafiocasacodigo.compra.comprador;

import com.vandson.desafiocasacodigo.estado.Estado;
import com.vandson.desafiocasacodigo.pais.Pais;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * @author Vandson Lima (vandson.vslima@gmail.com)
 * @since 31/07/2020
 **/
//4
@Component
public class ValidatorEstadosPais implements Validator {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public boolean supports(Class<?> aClass) {
        return NovoCompradorRequest.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object object, Errors errors) {
        if (errors.hasErrors())
            return;

        NovoCompradorRequest novoCompradorRequest = (NovoCompradorRequest) object;

        Pais pais = entityManager.find(Pais.class, novoCompradorRequest.getIdPais());
        Estado estado = entityManager.find(Estado.class, novoCompradorRequest.getIdEstado());

        if (!estado.pertenceAoPais(pais)) {
            errors.rejectValue("idEstado", null, "O estado não pertence ao país selecionado");
        }
    }
}
