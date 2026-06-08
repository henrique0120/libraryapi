package io.github.henrique0120.libraryapi.validator;

import io.github.henrique0120.libraryapi.exceptions.RegistroDuplicadoException;
import io.github.henrique0120.libraryapi.repository.LivroRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

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
