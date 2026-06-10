package io.github.henrique0120.libraryapi.controller.mappers;

import io.github.henrique0120.libraryapi.controller.dto.ResultadoPesquisaLivroDTO;
import io.github.henrique0120.libraryapi.model.Livro;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface LivroMapper {

    ResultadoPesquisaLivroDTO toDTO(Livro livro);

}
