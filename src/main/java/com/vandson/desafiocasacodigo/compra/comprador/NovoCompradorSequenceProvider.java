package com.vandson.desafiocasacodigo.compra.comprador;

import com.vandson.desafiocasacodigo.compra.PessoaFisica;
import com.vandson.desafiocasacodigo.compra.PessoaJuridica;
import org.hibernate.validator.spi.group.DefaultGroupSequenceProvider;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Vandson Lima (vandson.vslima@gmail.com)
 * @since 30/07/2020
 **/
//5
public class NovoCompradorSequenceProvider implements DefaultGroupSequenceProvider<NovoCompradorRequest> {

    @Override
    public List<Class<?>> getValidationGroups(NovoCompradorRequest novoCompradorRequest) {
        List<Class<?>> defaultGroupSequence = new ArrayList<>();
        defaultGroupSequence.add(NovoCompradorRequest.class);
        if(novoCompradorRequest == null)
            return defaultGroupSequence;

        if(StringUtils.hasLength(novoCompradorRequest.getCpfCnpj())){
            //validação básica, mas pode ser substituída por uma validação mais robusta com regex por ex.
           if(novoCompradorRequest.getCpfCnpj().length() < 14)
               defaultGroupSequence.add(PessoaFisica.class);
           else
               defaultGroupSequence.add(PessoaJuridica.class);
        }
        return defaultGroupSequence;
    }
}
