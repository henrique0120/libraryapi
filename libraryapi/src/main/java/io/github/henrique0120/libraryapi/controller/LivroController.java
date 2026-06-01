package io.github.henrique0120.libraryapi.controller;

import io.github.henrique0120.libraryapi.controller.dto.LivroDTO;
import io.github.henrique0120.libraryapi.model.Livro;
import io.github.henrique0120.libraryapi.service.LivroService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("livros")
public class LivroController {

    private final LivroService service;

    public LivroController(LivroService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<LivroDTO> register(@RequestBody LivroDTO livro){
        Livro conversao = livro.mapearParaLivro();
        service.register(conversao);
        return ResponseEntity.ok().build();
    }

}
