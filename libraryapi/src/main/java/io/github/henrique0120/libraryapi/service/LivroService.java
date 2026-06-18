package io.github.henrique0120.libraryapi.service;

import io.github.henrique0120.libraryapi.controller.dto.LivroDTO;
import io.github.henrique0120.libraryapi.controller.mappers.LivroMapper;
import io.github.henrique0120.libraryapi.model.Autor;
import io.github.henrique0120.libraryapi.model.GeneroLivro;
import io.github.henrique0120.libraryapi.model.Livro;
import io.github.henrique0120.libraryapi.repository.AutorRepository;
import io.github.henrique0120.libraryapi.repository.LivroRepository;
import io.github.henrique0120.libraryapi.validator.LivroValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

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
            String isbn, GeneroLivro genero, String nomeAutor, Integer anoPublicacao){
        Specification<Livro> find = null;

        //where isbn = :isbn
        Specification<Livro> isbnEqual = (root, query, cb) -> cb.equal(root.get("isbn"), isbn);
        return repository.findAll(isbnEqual);
    }

}
