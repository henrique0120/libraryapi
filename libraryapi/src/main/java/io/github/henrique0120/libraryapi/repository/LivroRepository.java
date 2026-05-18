package io.github.henrique0120.libraryapi.repository;

import io.github.henrique0120.libraryapi.model.Autor;
import io.github.henrique0120.libraryapi.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface LivroRepository extends JpaRepository<Livro, UUID> {

    //Query Method
    //select * from livro where id_autor = id
    List<Livro> findByAutor(Autor autor);

    //select * from livro where titulo = titulo
    List<Livro> findByTitulo(String titulo);

    List<Livro> findByIsbn(String isbn);

    List<Livro> findByTituloIsbn(String titulo, String isbn);
}
