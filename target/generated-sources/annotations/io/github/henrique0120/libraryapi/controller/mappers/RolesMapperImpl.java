package io.github.henrique0120.libraryapi.controller.mappers;

import io.github.henrique0120.libraryapi.controller.dto.RolesDTO;
import io.github.henrique0120.libraryapi.model.Roles;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-08-10T16:53:57-0300",
    comments = "version: 1.6.3, compiler: javac, environment: Java 21.0.12 (Microsoft)"
)
@Component
public class RolesMapperImpl implements RolesMapper {

    @Override
    public Roles toDTO(RolesDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Roles.RolesBuilder roles = Roles.builder();

        return roles.build();
    }

    @Override
    public RolesDTO toEntity(Roles roles) {
        if ( roles == null ) {
            return null;
        }

        RolesDTO rolesDTO = new RolesDTO();

        return rolesDTO;
    }
}
