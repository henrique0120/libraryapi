package io.github.henrique0120.libraryapi.dto;


import java.util.Date;

public record AutoresDTO(
        String nome,
        String nacionalidade,
        Date dataNascimento
) {}
