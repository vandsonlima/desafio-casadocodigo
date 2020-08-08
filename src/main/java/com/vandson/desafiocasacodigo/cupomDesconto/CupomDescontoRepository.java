package com.vandson.desafiocasacodigo.cupomDesconto;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * @author Vandson Lima (vandson.vslima@gmail.com)
 * @since 07/08/2020
 **/
@Repository
public interface CupomDescontoRepository extends JpaRepository<CupomDesconto, Long> {

    /**
     * Busca por um cupom que existe no sistema
     * @param codigo
     * @return
     */
    CupomDesconto getByCodigo(@NotNull String codigo);

    List<CupomDesconto> findAllByCodigoAndValidadeAfter(String Codigo, LocalDateTime dateTimeAtual);

}
