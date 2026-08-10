package io.github.henrique0120.libraryapi.controller.mappers;

import io.github.henrique0120.libraryapi.controller.dto.RolesDTO;
import io.github.henrique0120.libraryapi.model.Roles;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RolesMapper {

    Roles toDTO(RolesDTO dto);
    RolesDTO toEntity(Roles roles);

}
