package com.vandson.desafiocasacodigo.categoria;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.validation.Valid;

/**
 * @author Vandson Lima (vandson.vslima@gmail.com)
 * @since 24/07/2020
 **/
@RestController
//3
public class CategoriaController {

    private final EntityManager entityManager;
    private final ProibeNomeCategoriaDuplicado proibeNomeCategoriaDuplicado;

    public CategoriaController(EntityManager entityManager, ProibeNomeCategoriaDuplicado proibeNomeCategoriaDuplicado) {
        this.entityManager = entityManager;
        this.proibeNomeCategoriaDuplicado = proibeNomeCategoriaDuplicado;
    }

    @InitBinder
    public void initBinding(WebDataBinder webDataBinder){
        webDataBinder.addValidators(proibeNomeCategoriaDuplicado);
    }

    @PostMapping("/categorias")
    @Transactional
    public String criar(@RequestBody @Valid CategoriaRequest categoriaRequest){
        Categoria categoria = categoriaRequest.toCategoria();
        entityManager.persist(categoria);
        return categoria.toString();
    }
}
