package io.github.henrique0120.libraryapi.service;

import io.github.henrique0120.libraryapi.model.Autor;
import io.github.henrique0120.libraryapi.repository.AutorRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class AutoresService {


    private AutorRepository autorRepository;

    public Autor register(Autor autor){
        return this.autorRepository.save(autor);
    }

    public Autor details(UUID id){
        var byId = autorRepository.findById(id).get();
        return byId;
    }

    public Autor find(String nome, String nacionalidade){
        return this.autorRepository.find(nome, nacionalidade);
    }

    public void delete(UUID id){
        autorRepository.deleteById(id);
    }


}
