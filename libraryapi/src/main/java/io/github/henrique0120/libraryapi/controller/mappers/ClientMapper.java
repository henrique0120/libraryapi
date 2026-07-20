package io.github.henrique0120.libraryapi.controller.mappers;

import io.github.henrique0120.libraryapi.controller.dto.ClientDTO;
import io.github.henrique0120.libraryapi.model.Client;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ClientMapper {
    Client toEntity (ClientDTO dto);
    Client toDTO(ClientDTO dto);
}
