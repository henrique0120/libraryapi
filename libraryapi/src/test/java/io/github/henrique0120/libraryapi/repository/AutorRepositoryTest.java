package io.github.henrique0120.libraryapi.repository;

import io.github.henrique0120.libraryapi.model.Autor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@SpringBootTest
class AutorRepositoryTest {

    @Autowired
    AutorRepository repository;

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

}
