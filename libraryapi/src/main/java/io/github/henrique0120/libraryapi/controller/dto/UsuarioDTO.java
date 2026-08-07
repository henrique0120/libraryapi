package io.github.henrique0120.libraryapi.controller.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

@Builder
public record UsuarioDTO (
        String nome,
        String email,
        String senha
){}
