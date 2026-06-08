package io.github.henrique0120.libraryapi.controller;

import io.github.henrique0120.libraryapi.controller.dto.CadastroLivroDTO;
import io.github.henrique0120.libraryapi.controller.dto.ErroResposta;
import io.github.henrique0120.libraryapi.controller.dto.LivroDTO;
import io.github.henrique0120.libraryapi.exceptions.RegistroDuplicadoException;
import io.github.henrique0120.libraryapi.model.Livro;
import io.github.henrique0120.libraryapi.service.LivroService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import java.net.URI;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("livros")
@RequiredArgsConstructor
public class LivroController {

    private final LivroService service;
    private final LivroDTO dto;

    @PostMapping
    public ResponseEntity<Object> register(@RequestBody @Valid LivroDTO livro){
        try {
            // mapear dto para entidade
            // enviar a entidade para o serviço validar e salvar na base
            // criar url para acesso dos dados do livro
            // retornar codigo created com header location
            Livro conversao = livro.mapearParaLivro();
            service.register(conversao);

            URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(conversao.getId())
                    .toUri();
            return ResponseEntity.created(location).build();
        }catch (RegistroDuplicadoException e){
            var erroDTO = ErroResposta.conflito(e.getMessage());
            return ResponseEntity.status(erroDTO.status()).body(erroDTO);
        }
    }

    @GetMapping("{id}")
    public Optional<LivroDTO> pesquisar(@PathVariable("id") UUID id){
        return service.pesquisar(id);
    }

}
