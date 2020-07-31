package com.vandson.desafiocasacodigo.compra.comprador;

import com.vandson.desafiocasacodigo.estado.Estado;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * @author Vandson Lima (vandson.vslima@gmail.com)
 * @since 31/07/2020
 **/
//6
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
        NovoCompradorRequest novoCompradorRequest = (NovoCompradorRequest) object;
        List<Estado> listaEstados = entityManager
                .createQuery("SELECT estado FROM Estado estado WHERE estado.pais.id =:idPais")
                .setParameter("idPais", novoCompradorRequest.getIdPais())
                .getResultList();

        //Quando o país não tem estado, não deve vir preenchido
        if (CollectionUtils.isEmpty(listaEstados) && Objects.nonNull(novoCompradorRequest.getIdEstado())) {
            errors.rejectValue("idEstado", null, "O País não possui estados");
            return;
        }

        //Quando o país tem estados, o estado deve vir preenchido
        if (!CollectionUtils.isEmpty(listaEstados) && Objects.isNull(novoCompradorRequest.getIdEstado())) {
            errors.rejectValue("idEstado", null, "O estado é obrigatório");
            return;
        }

        //Quando o país tem estados e o estado vem preenchido, o estado deve pertencer ao país selecionado
        if(!CollectionUtils.isEmpty(listaEstados) && Objects.nonNull(novoCompradorRequest.getIdEstado())) {
            Optional<Estado> optionalEstado = listaEstados.stream()
                    .filter(estado -> estado.getId().equals(novoCompradorRequest.getIdEstado()))
                    .findFirst();

            if (optionalEstado.isEmpty())
                errors.rejectValue("idEstado", null,  "O estado informado não pertence ao país");
        }
    }
}
