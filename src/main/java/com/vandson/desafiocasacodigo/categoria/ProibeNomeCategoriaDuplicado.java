package com.vandson.desafiocasacodigo.categoria;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * @author Vandson Lima (vandson.vslima@gmail.com)
 * @since 24/07/2020
 **/
@Component
public class ProibeNomeCategoriaDuplicado implements Validator {

    private final CategoriaRepository categoriaRepository;

    public ProibeNomeCategoriaDuplicado(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return CategoriaRequest.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object object, Errors errors) {
        if(errors.hasErrors())
            return;
        CategoriaRequest categoriaRequest = (CategoriaRequest) object;
        if(categoriaRepository.existsCategoriaByNome(categoriaRequest.getNome())){
            errors.rejectValue("nome", null, "Já existe uma categoria com o nome " + categoriaRequest.getNome());
        }
    }
}
