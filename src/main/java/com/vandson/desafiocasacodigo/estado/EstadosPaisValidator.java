package com.vandson.desafiocasacodigo.estado;

import com.vandson.desafiocasacodigo.compra.nova.api.NovaCompraRequest;
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
public class EstadosPaisValidator implements Validator {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public boolean supports(Class<?> aClass) {
        return NovaCompraRequest.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object object, Errors errors) {
        if (errors.hasErrors())
            return;

        NovaCompraRequest novaCompraRequest = (NovaCompraRequest) object;
        if(novaCompraRequest.temEstado()) {
            Pais pais = entityManager.find(Pais.class, novaCompraRequest.getIdPais());
            Estado estado = entityManager.find(Estado.class, novaCompraRequest.getIdEstado());

            if (!estado.pertenceAoPais(pais)) {
                errors.rejectValue("idEstado", null, "O estado não pertence ao país selecionado");
            }
        }
    }
}
