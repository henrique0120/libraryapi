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

    public ResponseEntity<Livro> register(Livro livro){
        validator.validar(livro.getIsbn());
        repository.save(livro);
        return ResponseEntity.ok().build();
    }

    public List<Livro> validarExclusao(Autor autor) {
        Autor sla = new Autor();
        return sla.getLivros();
    }

    public Optional<Livro> pesquisar(UUID id){
        return repository.findById(id);
    }

    public void deletar(UUID id){
        repository.deleteById(id);
    }

    public ResponseEntity<Livro> atualizar (UUID id, LivroDTO dto){
        Optional<Livro> encontrado = repository.findById(id);
        if (encontrado.isPresent()){
            Livro a = encontrado.get();
            a.setTitulo(dto.titulo());
            repository.save(a);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

}
