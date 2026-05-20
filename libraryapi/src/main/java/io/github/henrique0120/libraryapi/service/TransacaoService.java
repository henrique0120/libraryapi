package io.github.henrique0120.libraryapi.service;

import io.github.henrique0120.libraryapi.model.Autor;
import io.github.henrique0120.libraryapi.model.GeneroLivro;
import io.github.henrique0120.libraryapi.model.Livro;
import io.github.henrique0120.libraryapi.repository.AutorRepository;
import io.github.henrique0120.libraryapi.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Service
public class TransacaoService {

    @Autowired
    private AutorRepository autorRepository;
    @Autowired
    private LivroRepository livroRepository;


    @Transactional
    public void atualizacaoSemAtualizar(){
        var livro = livroRepository
                .findById(UUID.fromString("7b65acaa-6d27-4e63-9344-dd8d54c0675c"))
                .orElse(null);

        livro.setDataPublicacao(LocalDate.of(2026,5,20));
    }

    @Transactional
    public void executar(){
        //salva o autor
        Autor autor = new Autor();
        autor.setNome("Test Francisca");
        autor.setNacionalidade("Brasileira");
        autor.setDataNascimento(LocalDate.of(1976, 8, 25));

        autorRepository.save(autor);


        //salva o livro
        Livro livro = new Livro();
        livro.setIsbn("0965847563");
        livro.setPreco(BigDecimal.valueOf(100));
        livro.setGenero(GeneroLivro.FICCAO);
        livro.setTitulo("Test Biblia");
        livro.setDataPublicacao(LocalDate.of(1976, 8, 25));

        livro.setAutor(autor);

        livroRepository.save(livro);

        if(autor.getNome().equals("Test Francisca")){
            throw new RuntimeException("Rollback!");
        }

    }

}
