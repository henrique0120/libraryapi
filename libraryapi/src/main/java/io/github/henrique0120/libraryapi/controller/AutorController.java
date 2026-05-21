package io.github.henrique0120.libraryapi.controller;

import io.github.henrique0120.libraryapi.controller.dto.AutorDTO;
import io.github.henrique0120.libraryapi.model.Autor;
import io.github.henrique0120.libraryapi.service.AutorService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.UUID;


@RestController
@RequestMapping("autores")
@AllArgsConstructor
public class AutorController {

    private final AutorService service;

    @PostMapping
    public ResponseEntity<Void> register(@RequestBody AutorDTO autor){
        var autorEntidade = autor.mapearParaAutor();
        service.salvar(autorEntidade);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(autorEntidade.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }


}
