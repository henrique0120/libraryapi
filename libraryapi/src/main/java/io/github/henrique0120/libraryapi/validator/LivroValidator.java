package io.github.henrique0120.libraryapi.validator;

import io.github.henrique0120.libraryapi.exceptions.RegistroDuplicadoException;
import io.github.henrique0120.libraryapi.model.Autor;
import io.github.henrique0120.libraryapi.model.Livro;
import io.github.henrique0120.libraryapi.repository.LivroRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class LivroValidator {

    private final LivroRepository repository;

    public void validar(String isbn){
        if (existeIsbnCadastrado(isbn)){
            throw new RegistroDuplicadoException("Livro já cadastrado com esse ISBN!");
        }
    }

    private boolean existeIsbnCadastrado(String isbn){
        return repository.existsByIsbn(isbn);
    }
}
