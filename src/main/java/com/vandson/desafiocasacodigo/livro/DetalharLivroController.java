package com.vandson.desafiocasacodigo.livro;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author Vandson (vaondson.vslima@gmail.com)
 * @since 30/07/2020
 **/
//4
@RestController
public class DetalharLivroController {

    private LivroRepository livroRepository;

    DetalharLivroController(LivroRepository livroRepository) {
        this.livroRepository = livroRepository;
    }

    @GetMapping("/livros")
    List<LivroResponse> recuperarLivros(){
        List<Livro> livros = Optional.of(livroRepository.findAll())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "não há livros cadastrados"));

        return livros.stream().map(LivroResponse::new).collect(Collectors.toList());
    }

    @GetMapping("/livros/{id}")
    DetalheLivroResponse detalharLivro(@PathVariable("id") Long id){
        Livro livro = livroRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        return new DetalheLivroResponse(livro);
    }
}
