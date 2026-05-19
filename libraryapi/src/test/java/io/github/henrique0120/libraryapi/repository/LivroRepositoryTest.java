package io.github.henrique0120.libraryapi.repository;

import io.github.henrique0120.libraryapi.model.Autor;
import io.github.henrique0120.libraryapi.model.GeneroLivro;
import io.github.henrique0120.libraryapi.model.Livro;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
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
    void salvarAutorELivroTest(){

        Livro livro = new Livro();
        livro.setIsbn("0394928839454");
        livro.setPreco(BigDecimal.valueOf(100));
        livro.setGenero(GeneroLivro.CIENCIA);
        livro.setTitulo("UFO");
        livro.setDataPublicacao(LocalDate.ofEpochDay(21/9/2025));

        Autor autor = new Autor();
        autor.setNome("Stephen Hawking");
        autor.setNacionalidade("Reino Unido");
        autor.setDataNascimento(LocalDate.of(1946, 12, 25));

        autorRepository.save(autor);

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

    @Test
    void atualizarAutorDoLivro(){
        UUID id = UUID.fromString("cf0b7de2-16ed-4040-9c3f-182e43f683f3");
        var livroParaAtualizar = repository.findById(id).orElse(null);

        UUID idAutor = UUID.fromString("0b680d93-0cc1-457e-a9de-15403f49ae59");
        Autor jesus = autorRepository.findById(idAutor).orElse(null);

        livroParaAtualizar.setAutor(jesus);

        repository.save(livroParaAtualizar);
    }

    @Test
    void deletar(){
        //desabilitar cascade na entidade
        UUID id = UUID.fromString("cf0b7de2-16ed-4040-9c3f-182e43f683f3");
        repository.deleteById(id);
    }

    @Test
    void deletarCascade(){
        //habilitar cascade na entidade
        UUID id = UUID.fromString("cf0b7de2-16ed-4040-9c3f-182e43f683f3");
        repository.deleteById(id);
    }

    @Test
    //transactional serve para fazer mais uma consulta no banco para carregar os dados que você precisa
    //quando o fetch estiver como LAZY
    @Transactional
    void buscarLivroTest(){
        UUID id = UUID.fromString("579dbcfa-3b6e-4be5-99b8-8a319c0c7e59");
        Livro livro = repository.findById(id).orElse(null);
        System.out.println(livro.getTitulo());
        System.out.println(livro.getAutor().getNome());
    }

    @Test
    void buscarLivroByName(){

        List<Livro> byName = repository.findByTitulo("UFO");
        byName.forEach(System.out::println);

    }
//    @Test
//    void buscarLivroByISBN(){
//
//        List<Livro> byISBN = repository.findByIsbn("1234982739023");
//        byISBN.forEach(System.out::println);
//
//    }
    @Test
    void buscarLivroByTituloIsbn(){
        var title = "UFO";
        var isbn = "0394928839454";

        List<Livro> byISBN = repository.findByTituloAndIsbn(title, isbn);
        byISBN.forEach(System.out::println);

    }


}