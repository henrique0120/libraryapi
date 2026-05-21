package io.github.henrique0120.libraryapi.controller;

import io.github.henrique0120.libraryapi.model.Autor;
import io.github.henrique0120.libraryapi.service.AutoresService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;


@RestController
@RequestMapping("/autores")
@AllArgsConstructor
public class AutoresController {

    private AutoresService autoresControllerService;

    @PostMapping
    public Autor register(@RequestBody Autor autor){
        return this.autoresControllerService.register(autor);
    }

    @GetMapping
    public Autor find(@RequestParam String nome, String nacionalidade){
        return autoresControllerService.find(nome, nacionalidade);
    }

    @GetMapping("{id}")
    public Autor details(@PathVariable UUID id){
        return this.autoresControllerService.details(id);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable UUID id){
        autoresControllerService.delete(id);
    }


}
