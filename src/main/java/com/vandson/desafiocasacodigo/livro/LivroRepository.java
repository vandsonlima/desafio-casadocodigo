package com.vandson.desafiocasacodigo.livro;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Vandson Lima (vandson.vslima@gmail.com)
 * @since 30/07/2020
 **/
@Repository
public interface LivroRepository extends JpaRepository<Livro, Long> {
}
