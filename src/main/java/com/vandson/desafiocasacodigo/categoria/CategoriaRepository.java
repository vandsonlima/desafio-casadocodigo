package com.vandson.desafiocasacodigo.categoria;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Vandson Lima (vandson.vslima@gmail.com)
 * @since 24/07/2020
 **/
@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

    boolean existsCategoriaByNome(String nome);
}
