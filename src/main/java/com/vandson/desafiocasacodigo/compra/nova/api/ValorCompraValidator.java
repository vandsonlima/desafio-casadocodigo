package com.vandson.desafiocasacodigo.compra.nova.api;

import com.vandson.desafiocasacodigo.compra.nova.api.NovaCompraRequest;
import com.vandson.desafiocasacodigo.livro.novoLivro.Livro;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * @author Vandson Lima (vandson.vslima@gmail.com)
 * @since 31/07/2020
 **/
//4
@Component
public class ValorCompraValidator implements Validator {

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
        if(!valorTotalCorreto(novaCompraRequest)) {
            errors.rejectValue("itensNovaCompra",
                    null,
                    String.format("O valor total de %s não corresponde ao valor total da compra", novaCompraRequest.getTotal()));
        }
    }

    private boolean valorTotalCorreto(NovaCompraRequest novaCompraRequest) {
        Double valorSomadoDosItens = novaCompraRequest.getItensNovaCompra().stream()
                .map(item -> item.toModel(entityManager))
                .mapToDouble(itemCompra -> itemCompra.getValor().doubleValue())
                .sum();
        return valorSomadoDosItens.equals(novaCompraRequest.getTotal());
    }
}
