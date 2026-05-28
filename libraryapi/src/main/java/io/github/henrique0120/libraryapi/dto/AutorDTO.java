package io.github.henrique0120.libraryapi.dto;


import lombok.Getter;

import java.util.Date;
import java.util.UUID;

public record AutorDTO(
        UUID id,
        String nome,
        String nacionalidade,
        Date dataNascimento
){}
