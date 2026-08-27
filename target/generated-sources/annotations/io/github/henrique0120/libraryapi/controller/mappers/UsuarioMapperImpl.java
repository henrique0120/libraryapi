package io.github.henrique0120.libraryapi.controller.mappers;

import io.github.henrique0120.libraryapi.controller.dto.RegisterRequestDTO;
import io.github.henrique0120.libraryapi.model.Usuario;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-08-27T15:09:19-0300",
    comments = "version: 1.6.3, compiler: javac, environment: Java 21.0.12.1 (Microsoft)"
)
@Component
public class UsuarioMapperImpl implements UsuarioMapper {

    @Override
    public RegisterRequestDTO toDTO(Usuario usuario) {
        if ( usuario == null ) {
            return null;
        }

        RegisterRequestDTO.RegisterRequestDTOBuilder registerRequestDTO = RegisterRequestDTO.builder();

        registerRequestDTO.nome( usuario.getNome() );
        registerRequestDTO.email( usuario.getEmail() );
        registerRequestDTO.senha( usuario.getSenha() );

        return registerRequestDTO.build();
    }

    @Override
    public Usuario toEntity(RegisterRequestDTO registerRequestDTO) {
        if ( registerRequestDTO == null ) {
            return null;
        }

        Usuario.UsuarioBuilder usuario = Usuario.builder();

        usuario.nome( registerRequestDTO.getNome() );
        usuario.email( registerRequestDTO.getEmail() );
        usuario.senha( registerRequestDTO.getSenha() );

        return usuario.build();
    }
}
