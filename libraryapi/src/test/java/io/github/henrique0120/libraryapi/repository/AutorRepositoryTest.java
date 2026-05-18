package io.github.henrique0120.libraryapi.repository;

import io.github.henrique0120.libraryapi.model.Autor;
import io.github.henrique0120.libraryapi.model.GeneroLivro;
import io.github.henrique0120.libraryapi.model.Livro;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@SpringBootTest
class AutorRepositoryTest {

    @Autowired
    AutorRepository repository;

    @Autowired
    LivroRepository livroRepository;

    @Test
    void salvarTest(){
        Autor autor = new Autor();
        autor.setNome("Jesus Criso");
        autor.setNacionalidade("Belém");
        autor.setDataNascimento(LocalDate.of(0, 12, 25));

        var autorSalvo = repository.save(autor);
        System.out.println(autorSalvo);
    }

    @Test
    void atualizarTest(){
        var id = UUID.fromString("e4550e77-fa12-4a17-9f38-66fc033963b0");

        Optional<Autor> possivelAutor = repository.findById(id);

        if(possivelAutor.isPresent()){

            Autor autorEncontrado = possivelAutor.get();
            System.out.println("Dados do Autor:");
            System.out.println(possivelAutor.get());

            autorEncontrado.setDataNascimento(LocalDate.of(1960, 1, 1));

            repository.save(autorEncontrado);
        }
    }

    @Test
    void listTest(){
       List<Autor> list = repository.findAll();

       list.forEach(System.out::println);

    }

    @Test
    void countTest(){
        System.out.println("Contagem de autores: " + repository.count());
    }

    @Test
    void deleteForIdTest(){
        var id = UUID.fromString("cca77b99-654f-4601-9baa-cb9be3084ae6");
        repository.deleteById(id);
    }

    @Test
    void salvarAutorComLivrosTest(){
        Autor autor = new Autor();
        autor.setNome("Antonio");
        autor.setNacionalidade("Americano");
        autor.setDataNascimento(LocalDate.of(1923, 7, 25));

        Livro livro = new Livro();
        livro.setIsbn("1234982739023");
        livro.setPreco(BigDecimal.valueOf(300));
        livro.setGenero(GeneroLivro.MISTERIO);
        livro.setTitulo("Mister M");
        livro.setDataPublicacao(LocalDate.of(1950, 7, 25));
        livro.setAutor(autor);

        Livro livro0 = new Livro();
        livro0.setIsbn("098567543678456");
        livro0.setPreco(BigDecimal.valueOf(150));
        livro0.setGenero(GeneroLivro.FICCAO);
        livro0.setTitulo("UFO");
        livro0.setDataPublicacao(LocalDate.of(1979, 7, 25));
        livro0.setAutor(autor);

        autor.setLivros(new ArrayList<>());
        autor.getLivros().add(livro);
        autor.getLivros().add(livro0);

        repository.save(autor);
        //livroRepository.saveAll(autor.getLivros());

    }

}
