package io.github.henrique0120.libraryapi.service;

import io.github.henrique0120.libraryapi.controller.dto.AutorDTO;
import io.github.henrique0120.libraryapi.model.Autor;
import io.github.henrique0120.libraryapi.repository.AutorRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AutorService {

    private final AutorRepository repository;

    public AutorService(AutorRepository repository){
        this.repository = repository;
    }

    public Autor salvar(Autor autor){
        return repository.save(autor);
    }

}
