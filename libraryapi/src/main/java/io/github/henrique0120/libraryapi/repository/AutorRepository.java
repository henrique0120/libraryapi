package io.github.henrique0120.libraryapi.repository;

import io.github.henrique0120.libraryapi.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface AutorRepository extends JpaRepository<Autor, UUID> {

    @Query("select n from Autor n where n.nome = :nome and n.nacionalidade = :nacionalidade")
    Autor find(@Param("nome") String nome,
               @Param("nacionalidade") String nacionalidade);

}
