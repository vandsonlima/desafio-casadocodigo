package com.vandson.desafiocasacodigo.autor;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Vandson Lima (vandson.vslima@gmail.com)
 * @since 24/07/2020
 **/
@Repository
public interface AutorRepository extends JpaRepository<Autor, Long> {
    boolean existsAutorByEmail(String email);
}
