package com.vandson.desafiocasacodigo.categoria;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;

/**
 * @author Vandson Lima (vandson.vslima@gmail.com)
 * @since 24/07/2020
 **/
@RestController
//1
public class CategoriaController {

    @PersistenceContext
    private EntityManager entityManager;

    @PostMapping("/categorias")
    @Transactional
    public String criar(@RequestBody @Valid CategoriaRequest categoriaRequest){
        Categoria categoria = new Categoria(categoriaRequest.getNome());
        entityManager.persist(categoria);
        return categoria.toString();
    }
}
