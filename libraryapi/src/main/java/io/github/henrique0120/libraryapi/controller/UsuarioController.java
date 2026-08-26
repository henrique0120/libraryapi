package io.github.henrique0120.libraryapi.controller;

import io.github.henrique0120.libraryapi.controller.dto.LoginRequestDTO;
import io.github.henrique0120.libraryapi.controller.dto.RegisterRequestDTO;
import io.github.henrique0120.libraryapi.controller.dto.TokenResponseDTO;
import io.github.henrique0120.libraryapi.controller.mappers.UsuarioMapper;
import io.github.henrique0120.libraryapi.model.Usuario;
import io.github.henrique0120.libraryapi.service.AuthenticationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
@RequiredArgsConstructor
@Tag(name = "Usuarios")
public class UsuarioController {

    private final AuthenticationService authenticationService;
    private final UsuarioMapper mapper;

    @PostMapping("/salvar")
    public ResponseEntity<Usuario> salvar(@RequestBody @Valid RegisterRequestDTO dto) throws BadRequestException {
        //var a = mapper.toEntity(dto);
        authenticationService.criarUsuario(dto);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/login")
    public TokenResponseDTO login(@RequestBody @Valid LoginRequestDTO dto) throws Exception {
        return authenticationService.login(dto);
    }
}