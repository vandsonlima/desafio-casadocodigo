package com.vandson.desafiocasacodigo.compra.detalhesPedido;

import com.vandson.desafiocasacodigo.compra.nova.dominio.PedidoCompra;
import com.vandson.desafiocasacodigo.compra.nova.dominio.PedidoCompraRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

/**
 * @author Vandson (vaondson.vslima@gmail.com)
 * @since 31/07/2020
 **/
//3
@RestController
public class DetalhesPedidoCompraController {

    private final PedidoCompraRepository pedidoCompraRepository;

    public DetalhesPedidoCompraController(PedidoCompraRepository pedidoCompraRepository) {
        this.pedidoCompraRepository = pedidoCompraRepository;
    }

    @GetMapping("/compra/{id}")
    public DetalhesPedidoCompraResponse criar(@PathVariable Long id) {
        PedidoCompra pedidoCompra = pedidoCompraRepository.findById(id)
                .orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return new DetalhesPedidoCompraResponse().fromModel(pedidoCompra);
    }
}
