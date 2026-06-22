package io.github.henrique0120.libraryapi.validator;

import io.github.henrique0120.libraryapi.exceptions.CampoInvalidoException;
import io.github.henrique0120.libraryapi.exceptions.RegistroDuplicadoException;
import io.github.henrique0120.libraryapi.model.Livro;
import io.github.henrique0120.libraryapi.repository.LivroRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class LivroValidator {

    private static final int AND_EXIGENCIA_PRECO = 2020;

    private final LivroRepository repository;

    public void validar(Livro livro){
        if (existeIsbnCadastrado(livro)){
            throw new RegistroDuplicadoException("Livro já cadastrado com esse ISBN!");
        }

        if(isPrecoObrigatorioNulo(livro)){
            throw new CampoInvalidoException("preco", "Para livros com ano de publicação a partir de 2020, o preço é obrigatório.");
        }

    }

    private boolean isPrecoObrigatorioNulo(Livro livro) {
        return livro.getPreco() == null &&
                livro.getDataPublicacao().getYear() >= AND_EXIGENCIA_PRECO;
    }

    private boolean existeIsbnCadastrado(Livro livro){
        Optional<Livro> livroEncontrado = repository.findByIsbn(livro.getIsbn());

        if(livro.getId() == null){
            return livroEncontrado.isPresent();
        }

        return livroEncontrado
                .map(Livro::getId)
                .stream()
                .anyMatch(id -> !id.equals(livro.getId()));

    }
}
