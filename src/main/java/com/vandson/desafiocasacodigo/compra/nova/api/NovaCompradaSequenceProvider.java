package com.vandson.desafiocasacodigo.compra.nova.api;

import org.hibernate.validator.spi.group.DefaultGroupSequenceProvider;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Vandson Lima (vandson.vslima@gmail.com)
 * @since 30/07/2020
 **/
//7
public class NovaCompradaSequenceProvider implements DefaultGroupSequenceProvider<NovaCompraRequest> {

    @Override
    public List<Class<?>> getValidationGroups(NovaCompraRequest novaCompraRequest) {
        List<Class<?>> defaultGroupSequence = new ArrayList<>();
        defaultGroupSequence.add(NovaCompraRequest.class);
        if(novaCompraRequest == null)
            return defaultGroupSequence;

        if(StringUtils.hasLength(novaCompraRequest.getCpfCnpj())){
            //validação básica, mas pode ser substituída por uma validação mais robusta com regex por ex.
           if(novaCompraRequest.getCpfCnpj().length() < 14)
               defaultGroupSequence.add(PessoaFisica.class);
           else
               defaultGroupSequence.add(PessoaJuridica.class);
        }
        return defaultGroupSequence;
    }
}
