package io.github.henrique0120.libraryapi.controller;

import io.github.henrique0120.libraryapi.controller.dto.UsuarioDTO;
import io.github.henrique0120.libraryapi.controller.mappers.UsuarioMapper;
import io.github.henrique0120.libraryapi.model.Usuario;
import io.github.henrique0120.libraryapi.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("path")
@RequiredArgsConstructor
public class UsuarioController {

    private final AuthenticationService authenticationService;
    private final UsuarioMapper mapper;

    @PostMapping("/salvar")
    public ResponseEntity<Usuario> salvar(UsuarioDTO dto) throws BadRequestException {
        var a = mapper.toEntity(dto);
        authenticationService.criarUsuario(a);
        return ResponseEntity.ok().build();

    }
}