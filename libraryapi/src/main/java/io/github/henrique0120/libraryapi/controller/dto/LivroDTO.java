package io.github.henrique0120.libraryapi.controller.dto;

import io.github.henrique0120.libraryapi.model.Autor;
import io.github.henrique0120.libraryapi.model.GeneroLivro;
import io.github.henrique0120.libraryapi.model.Livro;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public record LivroDTO(
        UUID id,
        @NotBlank(message = "O campo ISBN não pode ficar vazio!")
        String isbn,
        @NotBlank(message = "O campo Titulo não pode ficar vazio!")
        String titulo,
        @Past(message = "A data de publicação não pode ser uma data futura!")
        LocalDate dataPublicacao,
        GeneroLivro genero,
        BigDecimal preco,
        @NotNull
        Autor autor
){}
