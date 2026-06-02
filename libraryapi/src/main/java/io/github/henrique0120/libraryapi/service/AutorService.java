package io.github.henrique0120.libraryapi.service;

import io.github.henrique0120.libraryapi.controller.dto.AutorDTO;
import io.github.henrique0120.libraryapi.exceptions.LivroAutorException;
import io.github.henrique0120.libraryapi.exceptions.RegistroDuplicadoException;
import io.github.henrique0120.libraryapi.model.Autor;
import io.github.henrique0120.libraryapi.repository.AutorRepository;
import io.github.henrique0120.libraryapi.repository.LivroRepository;
import io.github.henrique0120.libraryapi.validator.AutorValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AutorService {

    private final AutorRepository repository;
    private final AutorValidator validator;
    private final LivroRepository livroRepository;

    public Autor salvar(Autor autor){
        validator.validar(autor);
        return repository.save(autor);
    }

    public void update(Autor autor){
        validator.validar(autor);
        if(autor.getId() == null){
            throw new IllegalArgumentException("Para atualizar, é necessário que o autor esteja cadastrado no banco.");
        }
        repository.save(autor);
    }

    public Optional<Autor> obterPorId(UUID id){
        return repository.findById(id);
    }

    public void deletar(Autor autor){
        if(possuiLivro(autor)){
            throw new LivroAutorException("Não é permitido excluir um Autor que possui Ator possui livros cadastrados!");
        }
        repository.delete(autor);
    }

    public List<Autor> search(String nome, String nacionalidade){
        if ( nome != null && nacionalidade != null){
            return repository.findByNomeAndNacionalidade(nome, nacionalidade);
        }if(nome != null){
            return repository.findByNome(nome);
        }if(nacionalidade != null){
            return repository.findByNacionalidade(nacionalidade);
        }
        return repository.findAll();
    }

    public boolean possuiLivro(Autor autor){
        return livroRepository.existsByAutor(autor);
    }

}
