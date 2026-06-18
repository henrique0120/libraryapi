package io.github.henrique0120.libraryapi.controller;

import io.github.henrique0120.libraryapi.controller.dto.*;
import io.github.henrique0120.libraryapi.controller.mappers.LivroMapper;
import io.github.henrique0120.libraryapi.exceptions.RegistroDuplicadoException;
import io.github.henrique0120.libraryapi.model.Livro;
import io.github.henrique0120.libraryapi.service.LivroService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("livros")
@RequiredArgsConstructor
public class LivroController implements GenericController {

    private final LivroService service;
    private final LivroMapper mapper;

    @PostMapping
    public ResponseEntity<Void> register(@RequestBody @Valid CadastroLivroDTO dto) {
        Livro livro = mapper.toEntity(dto);
        // mapear dto para entidade
        // enviar a entidade para o serviço validar e salvar na base
        // criar url para acesso dos dados do livro
        // retornar codigo created com header location
        //Livro conversao = livro.mapearParaLivro();
        service.register(livro);
        var url = gerarHeaderLocation(livro.getId());
        return ResponseEntity.created(url).build();
    }

    @GetMapping("{id}")
    public ResponseEntity<ResultadoPesquisaLivroDTO> pesquisar(@PathVariable("id") UUID id) {
        return service
                .pesquisar(id)
                .map(livro -> {
                    ResultadoPesquisaLivroDTO dto = mapper.toDTO(livro);
                    return ResponseEntity.ok(dto);
                }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Object> deletar(@PathVariable("id") String id) {
        return service.obterPorId(UUID.fromString(id))
                .map(livro -> {
                    service.deletar(livro);
                    return ResponseEntity.noContent().build();
                }).orElseGet(() -> ResponseEntity.notFound().build());

    }


    @PutMapping("{id}")
    public ResponseEntity<LivroDTO> att(@PathVariable("id") UUID id, @RequestBody LivroDTO dto) {
        return service.atualizar(id, dto);
    }
}
