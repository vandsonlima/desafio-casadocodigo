package com.vandson.desafiocasacodigo.repository;

import com.vandson.desafiocasacodigo.domain.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Vandson Lima (vandsonlima@info.ufrn.br)
 * @since 23/07/2020
 **/
@Repository
public interface AutorRepository extends JpaRepository<Autor,Long> {

}
