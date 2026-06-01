package io.github.henrique0120.libraryapi.controller.dto;


import io.github.henrique0120.libraryapi.model.Autor;
import io.github.henrique0120.libraryapi.model.GeneroLivro;
import io.github.henrique0120.libraryapi.model.Livro;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public record LivroDTO (
        UUID id,
        String isbn,
        String titulo,
        LocalDate dataPublicacao,
        GeneroLivro genero,
        BigDecimal preco,
        Autor autor
){
    public Livro mapearParaLivro(){
        Livro livro = new Livro();
        livro.setIsbn(this.isbn);
        livro.setTitulo(this.titulo);
        livro.setDataPublicacao(this.dataPublicacao);
        livro.setGenero(this.genero);
        livro.setPreco(this.preco);
        livro.setAutor(this.autor);
        return livro;
    }
}
