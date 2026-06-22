package io.github.henrique0120.libraryapi.service;

import io.github.henrique0120.libraryapi.controller.dto.CadastroLivroDTO;
import io.github.henrique0120.libraryapi.controller.dto.LivroDTO;
import io.github.henrique0120.libraryapi.controller.mappers.LivroMapper;
import io.github.henrique0120.libraryapi.model.Autor;
import io.github.henrique0120.libraryapi.model.GeneroLivro;
import io.github.henrique0120.libraryapi.model.Livro;
import io.github.henrique0120.libraryapi.repository.AutorRepository;
import io.github.henrique0120.libraryapi.repository.LivroRepository;
import io.github.henrique0120.libraryapi.repository.specs.LivroSpecs;
import io.github.henrique0120.libraryapi.validator.LivroValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static io.github.henrique0120.libraryapi.repository.specs.LivroSpecs.*;

@Service
@RequiredArgsConstructor
public class LivroService {

    private final LivroRepository repository;
    private final AutorRepository autorRepository;
    private final LivroValidator validator;
    private final LivroMapper mapper;

    public Livro register(Livro livro) {
        validator.validar(livro);
        return repository.save(livro);
    }

    public List<Livro> validarExclusao(Autor autor) {
        Autor sla = new Autor();
        return sla.getLivros();
    }

    public Optional<Livro> obterPorId(UUID id) {
        return repository.findById(id);
    }

    public void deletar(Livro livro) {
        repository.delete(livro);
    }

    public void atualizar(Livro livro) {
        if (livro.getId() == null) {
            throw new IllegalArgumentException("Para atualizar, é necessário que o livro esteja cadastrado no banco.");
        }
        validator.validar(livro);
        repository.save(livro);
    }

    public Page<Livro> pesquisa(
            String isbn,
            String titulo,
            GeneroLivro genero,
            String nomeAutor,
            Integer anoPublicacao,
            Integer pagina,
            Integer tamanhoPagina){

        // select * from livro where isbn = :isbn and nomeAutor =

//        Specification<Livro> specs = Specification
//                .where(LivroSpecs.isbnEqual(isbn))
//                .and(LivroSpecs.tituloLike(titulo))
//                .and(LivroSpecs.generoEqual(genero))
//                ;

        Specification<Livro> specs = Specification.where((root, query, cb) -> cb.conjunction());

        if (isbn != null) {
            //query = query and isbn = :isbn
            specs = specs.and(isbnEqual(isbn));
        }

        if (titulo != null) {
            specs = specs.and(tituloLike(titulo));
        }

        if (genero != null) {
            specs = specs.and(generoEqual(genero));
        }

        if (anoPublicacao != null) {
            specs = specs.and(anoPublicacaoEqual(anoPublicacao));
        }

        if (nomeAutor != null) {
            specs = specs.and(nomeAutorLike(nomeAutor));
        }

        Pageable pageRequest = PageRequest.of(pagina, tamanhoPagina);

        return repository.findAll(specs, pageRequest);
    }

}
