package io.github.henrique0120.libraryapi.repository;

import io.github.henrique0120.libraryapi.model.Autor;
import io.github.henrique0120.libraryapi.model.GeneroLivro;
import io.github.henrique0120.libraryapi.model.Livro;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

@SpringBootTest
class LivroRepositoryTest {

    @Autowired
    LivroRepository repository;

    @Autowired
    AutorRepository autorRepository;

    @Test
    void salvarTest(){

        Livro livro = new Livro();
        livro.setIsbn("1234567891023");
        livro.setPreco(BigDecimal.valueOf(50));
        livro.setGenero(GeneroLivro.CIENCIA);
        livro.setTitulo("Biblia");
        livro.setDataPublicacao(LocalDate.ofEpochDay(16/5/2026));


        Autor autor = autorRepository
                .findById(UUID.fromString("0b680d93-0cc1-457e-a9de-15403f49ae59"))
                .orElse(null);

        livro.setAutor(autor);

        repository.save(livro);
    }

    @Test
    void salvarCascadeTest(){

        Livro livro = new Livro();
        livro.setIsbn("1234567891023");
        livro.setPreco(BigDecimal.valueOf(50));
        livro.setGenero(GeneroLivro.CIENCIA);
        livro.setTitulo("Biblia");
        livro.setDataPublicacao(LocalDate.ofEpochDay(16/5/2026));

        Autor autor = new Autor();
        autor.setNome("João");
        autor.setNacionalidade("Pará");
        autor.setDataNascimento(LocalDate.of(1983, 7, 10));

        livro.setAutor(autor);

        repository.save(livro);
    }

}