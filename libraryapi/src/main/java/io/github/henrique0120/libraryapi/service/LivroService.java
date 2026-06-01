package io.github.henrique0120.libraryapi.service;

import io.github.henrique0120.libraryapi.model.Autor;
import io.github.henrique0120.libraryapi.model.Livro;
import io.github.henrique0120.libraryapi.repository.LivroRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class LivroService {

    private LivroRepository repository;

    public LivroService(LivroRepository repository) {
        this.repository = repository;
    }

    public ResponseEntity<Livro> register(Livro livro){
        repository.save(livro);
        return ResponseEntity.ok().build();
    }

    public List<Livro> validarExclusao(Autor autor) {
        Autor sla = new Autor();
        return sla.getLivros();
    }
}
