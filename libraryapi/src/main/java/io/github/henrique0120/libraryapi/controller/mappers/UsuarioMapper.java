package io.github.henrique0120.libraryapi.controller.mappers;


import io.github.henrique0120.libraryapi.controller.dto.RegisterRequestDTO;
import io.github.henrique0120.libraryapi.model.Usuario;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {

    RegisterRequestDTO toDTO(Usuario usuario);
    Usuario toEntity(RegisterRequestDTO registerRequestDTO);

}
