package io.github.henrique0120.libraryapi.controller.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequestDTO {

    @NotBlank
    private String nome;
    @NotBlank
    private String email;
    @NotBlank
    private String senha;

}
