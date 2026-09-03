package io.github.henrique0120.libraryapi.controller;

import io.github.henrique0120.libraryapi.controller.dto.AutorDTO;
import io.github.henrique0120.libraryapi.controller.mappers.AutorMapper;
import io.github.henrique0120.libraryapi.model.Autor;
import io.github.henrique0120.libraryapi.service.AutorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("autores")
@RequiredArgsConstructor
@Tag(name = "Autores")
@Slf4j
public class AutorController implements GenericController {

    private final AutorService service;
    private final AutorMapper mapper;

    @PostMapping
    @Operation(summary = "Salvar", description = "Cadastrar novo autor")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Cadastrado com sucesso!"),
            @ApiResponse(responseCode = "422", description = "Erro de validação!"),
            @ApiResponse(responseCode = "409", description = "Autor já cadastrado!"),

    })
    public ResponseEntity<Void> register(@RequestBody @Valid AutorDTO dto) {
        log.info("Registrando autor: {}",  dto.nome());

        Autor autor = mapper.toEntity(dto);
        service.salvar(autor);
        URI location = gerarHeaderLocation(autor.getId());
        return ResponseEntity.created(location).build();
    }

    @GetMapping("{id}")
    @Operation(summary = "Pesquisar", description = "Obter autores pelo ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Pesquisado com sucesso!"),
            @ApiResponse(responseCode = "404", description = "Autor não encontrado!")

    })
    public ResponseEntity<AutorDTO> obterDetalhes(@PathVariable("id") String id) {
        var idAutor = UUID.fromString(id);

        return service
                .obterPorId(idAutor)
                .map(autor -> {
                    AutorDTO dto = mapper.toDTO(autor);
                    return ResponseEntity.ok(dto);
                }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("{id}")
    @Operation(summary = "Deletar", description = "Deleta um autor")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Deletado com sucesso!"),
            @ApiResponse(responseCode = "404", description = "Autor não encontrado!"),
            @ApiResponse(responseCode = "400", description = "Autor possui livro cadastrado!"),

    })
    public ResponseEntity<Void> deletar(@PathVariable("id") String id) {

        log.info("Deletando autor: {}", id);

        var idAutor = UUID.fromString(id);
        Optional<Autor> autorOptional = service.obterPorId(idAutor);

        if (autorOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        service.deletar(autorOptional.get());

        return ResponseEntity.noContent().build();
    }

    @GetMapping
    @Operation(summary = "Pesquisar", description = "Pesquisa um autor por parametros")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Sucesso!")

    })
    public ResponseEntity<List<AutorDTO>> search(
            @RequestParam(value = "nome", required = false) String nome,
            @RequestParam(value = "nacionalidade", required = false) String nacionalidade) {
        List<Autor> resultado = service.pesquisaByExample(nome, nacionalidade);
        List<AutorDTO> lista = resultado
                .stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(lista);
    }

    @PutMapping("{id}")
    @Operation(summary = "Atualizar", description = "Atualiza um autor por parametros")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Atualizado com sucesso!"),
            @ApiResponse(responseCode = "404", description = "Autor não encontrado!"),
            @ApiResponse(responseCode = "400", description = "Autor possui livro cadastrado!")

    })
    public ResponseEntity<Void> update(@PathVariable("id") UUID id, @RequestBody @Valid AutorDTO autor) {
        Optional<Autor> autorConsulta = service.obterPorId(id);

        if (autorConsulta.isEmpty()) {
            return ResponseEntity.unprocessableContent().build();
        }

        Autor entidade = autorConsulta.get();
        entidade.setNome(autor.nome());
        entidade.setDataNascimento(autor.dataNascimento());
        entidade.setNacionalidade(autor.nacionalidade());

        service.update(entidade);

        return ResponseEntity.noContent().build();
    }
}