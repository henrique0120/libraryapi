package io.github.henrique0120.libraryapi.controller;

import io.github.henrique0120.libraryapi.controller.dto.*;
import io.github.henrique0120.libraryapi.controller.mappers.LivroMapper;
import io.github.henrique0120.libraryapi.exceptions.RegistroDuplicadoException;
import io.github.henrique0120.libraryapi.model.Autor;
import io.github.henrique0120.libraryapi.model.GeneroLivro;
import io.github.henrique0120.libraryapi.model.Livro;
import io.github.henrique0120.libraryapi.service.LivroService;
import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import java.net.URI;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("livros")
@RequiredArgsConstructor
public class LivroController {

    private final LivroService service;
    private final LivroMapper mapper;

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
    public ResponseEntity<ResultadoPesquisaLivroDTO> pesquisar(@PathVariable("id") UUID id){
        return service
                .pesquisar(id)
                .map(livro -> {
                    ResultadoPesquisaLivroDTO dto = mapper.toDTO(livro);
                    return ResponseEntity.ok(dto);
                }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deletar(@PathVariable("id") UUID id){
        Optional<Livro> a = service.pesquisar(id);
        if (a.isPresent()){
            service.deletar(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.status(HttpStatusCode.valueOf(404)).body("Livro inexistente");
    }

    @GetMapping
    public List<Autor> search(@RequestParam(value = "isbn") String isbn,
                              @RequestParam(value = "titulo") String titulo,
                              @RequestParam(value = "nome_autor") String nome_autor,
                              @RequestParam(value = "genero") GeneroLivro genero,
                              @RequestParam(value = "data_publicacao") LocalDate data_publicacao){

    }

}
