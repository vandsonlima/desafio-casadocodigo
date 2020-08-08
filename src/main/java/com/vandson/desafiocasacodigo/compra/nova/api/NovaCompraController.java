package com.vandson.desafiocasacodigo.compra.nova.api;

import com.vandson.desafiocasacodigo.compra.nova.dominio.PedidoCompra;
import com.vandson.desafiocasacodigo.cupomDesconto.CupomDescontoRepository;
import com.vandson.desafiocasacodigo.estado.EstadosPaisValidator;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.validation.Valid;
import java.net.URI;

/**
 * @author Vandson (vaondson.vslima@gmail.com)
 * @since 30/07/2020
 **/
//4
@RestController
class NovaCompraController {

    private EntityManager entityManager;
    private final CupomDescontoRepository cupomDescontoRepository;
    private final CupomDescontoValidator cupomDescontoValidator;
    private final EstadosPaisValidator estadosPaisValidator;
    private final ValorCompraValidator valorCompraValidator;

    public NovaCompraController(EntityManager entityManager, CupomDescontoRepository cupomDescontoRepository, CupomDescontoValidator cupomDescontoValidator, EstadosPaisValidator estadosPaisValidator, ValorCompraValidator valorCompraValidator) {
        this.entityManager = entityManager;
        this.cupomDescontoRepository = cupomDescontoRepository;
        this.cupomDescontoValidator = cupomDescontoValidator;
        this.estadosPaisValidator = estadosPaisValidator;
        this.valorCompraValidator = valorCompraValidator;
    }

    @InitBinder
    public void initBinding(WebDataBinder webDataBinder){
        webDataBinder.addValidators(estadosPaisValidator, valorCompraValidator, cupomDescontoValidator);
    }

    @PostMapping("/compra")
    @ResponseStatus(HttpStatus.CREATED)
    @Transactional
    public ResponseEntity<?> criar(@RequestBody @Valid NovaCompraRequest novaCompraRequest) {
        PedidoCompra novoPedido = novaCompraRequest.toModel(entityManager, cupomDescontoRepository);
        entityManager.persist(novoPedido);
        return ResponseEntity.created(URI.create("/compra/" + novoPedido.getId())).build();
    }
}
