package io.github.henrique0120.libraryapi.service;

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
public class  LivroService {

    private final LivroRepository repository;
    private final AutorRepository autorRepository;
    private final LivroValidator validator;
    private final LivroMapper mapper;

    public Livro register(Livro livro){
        validator.validar(livro.getIsbn());
        return repository.save(livro);
    }

    public List<Livro> validarExclusao(Autor autor) {
        Autor sla = new Autor();
        return sla.getLivros();
    }

    public Optional<Livro> obterPorId(UUID id){
        return repository.findById(id);
    }

    public void deletar(Livro livro){
        repository.delete(livro);
    }

    public ResponseEntity<LivroDTO> atualizar (UUID id, LivroDTO dto){
        Optional<Livro> encontrado = repository.findById(id);

        if (encontrado.isPresent()){
            Livro a = encontrado.get();
            a.setIsbn(dto.isbn());
            a.setTitulo(dto.titulo());
            a.setDataPublicacao(dto.dataPublicacao());
            a.setGenero(dto.genero());
            a.setPreco(dto.preco());
            a.setAutor(dto.autor());
            repository.save(a);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    public List<Livro> pesquisa(
            String isbn, String titulo, GeneroLivro genero, String nomeAutor, Integer anoPublicacao){

        // select * from livro where isbn = :isbn and nomeAutor =

//        Specification<Livro> specs = Specification
//                .where(LivroSpecs.isbnEqual(isbn))
//                .and(LivroSpecs.tituloLike(titulo))
//                .and(LivroSpecs.generoEqual(genero))
//                ;

        Specification<Livro> specs = Specification.where((root, query, cb) -> cb.conjunction());

        if(isbn != null){
            //query = query and isbn = :isbn
            specs = specs.and(isbnEqual(isbn));
        }

        if(titulo != null){
            specs = specs.and(tituloLike(titulo));
        }

        if(genero != null){
            specs = specs.and(generoEqual(genero));
        }

        if(anoPublicacao != null){
            specs = specs.and(anoPublicacaoEqual(anoPublicacao));
        }

        return repository.findAll(specs);
    }

}
