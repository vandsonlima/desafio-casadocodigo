package com.vandson.desafiocasacodigo.controller;

import com.vandson.desafiocasacodigo.domain.Autor;
import com.vandson.desafiocasacodigo.repository.AutorRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author Vandson Lima (vandsonlima@info.ufrn.br)
 * @since 23/07/2020
 **/
@RestController
public class AutorController {

    private final AutorRepository autorRepository;

    public AutorController(AutorRepository autorRepository) {
        this.autorRepository = autorRepository;
    }

    @PostMapping("/autor/novo")
    public ResponseEntity<?> salvarNovoAutor(@RequestBody @Valid Autor autor){
        return new ResponseEntity<>(autorRepository.save(autor), HttpStatus.OK);
    }
}
