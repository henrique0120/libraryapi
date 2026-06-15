package io.github.henrique0120.libraryapi.controller.mappers;

import io.github.henrique0120.libraryapi.controller.dto.CadastroLivroDTO;
import io.github.henrique0120.libraryapi.controller.dto.LivroDTO;
import io.github.henrique0120.libraryapi.controller.dto.ResultadoPesquisaLivroDTO;
import io.github.henrique0120.libraryapi.model.Livro;
import io.github.henrique0120.libraryapi.repository.AutorRepository;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring")
public abstract class LivroMapper {

    @Autowired
    AutorRepository autorRepository;

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "dataCadastro", ignore = true)
    @Mapping(target = "dataPublicacao", ignore = true)
    @Mapping(target = "idUsuario", ignore = true)
    @Mapping(target = "autor", expression = "java(autorRepository.findById(dto.idAutor()).orElse(null))")
    public abstract Livro toEntity(CadastroLivroDTO dto);

    public abstract ResultadoPesquisaLivroDTO toDTO(Livro livro);

    public abstract LivroDTO toLivroDTO(Livro livro);

}
