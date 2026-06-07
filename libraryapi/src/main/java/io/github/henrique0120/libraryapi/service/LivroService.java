package io.github.henrique0120.libraryapi.service;

import io.github.henrique0120.libraryapi.model.Autor;
import io.github.henrique0120.libraryapi.model.Livro;
import io.github.henrique0120.libraryapi.repository.LivroRepository;
import io.github.henrique0120.libraryapi.validator.LivroValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LivroService {

    private final LivroRepository repository;
    private final LivroValidator validator;

    public ResponseEntity<Livro> register(Livro livro){
        validator.validar(livro.getIsbn());
        repository.save(livro);
        return ResponseEntity.ok().build();
    }

    public List<Livro> validarExclusao(Autor autor) {
        Autor sla = new Autor();
        return sla.getLivros();
    }
}
