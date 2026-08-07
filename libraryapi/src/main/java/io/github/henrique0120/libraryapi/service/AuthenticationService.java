package io.github.henrique0120.libraryapi.service;

import io.github.henrique0120.libraryapi.model.Usuario;
import io.github.henrique0120.libraryapi.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UsuarioRepository usuarioRepository;

    public Usuario criarUsuario(Usuario usuario) throws BadRequestException{
        Optional<Usuario> var =  usuarioRepository.findByEmail(usuario.getEmail());

        if (var.isPresent()){
            throw new BadRequestException("Já existe um usuario cadastrado com esse e-mail.");
        }

        return usuarioRepository.save(usuario);
    }


}
