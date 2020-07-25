package com.vandson.desafiocasacodigo.autor;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * @author Vandson Lima (vandson.vslima@gmail.com)
 * @since 24/07/2020
 **/
@Component
public class ProibeEmailDuplicadoValidator  implements Validator {

    private final AutorRepository autorRepository;

    public ProibeEmailDuplicadoValidator(AutorRepository autorRepository) {
        this.autorRepository = autorRepository;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return AutorRequest.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object object, Errors errors) {
        if(errors.hasErrors())
            return;

        AutorRequest autorRequest = (AutorRequest) object;
        if(autorRepository.existsAutorByEmail(autorRequest.getEmail())){
            errors.rejectValue("email",null, "Já existe um(a) outro(a) autor(a) com o mesmo email " + autorRequest.getEmail());
        }
    }
}
