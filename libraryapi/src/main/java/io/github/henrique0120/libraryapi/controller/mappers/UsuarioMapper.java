package io.github.henrique0120.libraryapi.controller.mappers;


import io.github.henrique0120.libraryapi.controller.dto.UsuarioDTO;
import io.github.henrique0120.libraryapi.model.Usuario;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {

    UsuarioDTO toDTO(Usuario usuario);
    Usuario toEntity(UsuarioDTO usuarioDTO);

}
