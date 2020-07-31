package com.vandson.desafiocasacodigo.compra.nova.dominio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Vandson Lima (vandson.vslima@gmail.com)
 * @since 31/07/2020
 **/
//1
@Repository
public interface PedidoCompraRepository extends JpaRepository<PedidoCompra, Long> {
}
