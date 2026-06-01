package io.github.henrique0120.libraryapi.repository;

import io.github.henrique0120.libraryapi.model.Autor;
import org.springframework.cglib.core.Local;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface AutorRepository extends JpaRepository<Autor, UUID> {

//    @Query("select n from Autor n where n.nome = :nome and n.nacionalidade = :nacionalidade")
//    Optional<Autor> find(@Param("nome") String nome,
//               @Param("nacionalidade") String nacionalidade);

    List<Autor> findByNome(String nome);
    List<Autor> findByNacionalidade(String nacionalidade);
    List<Autor> findByNomeAndNacionalidade(String nome, String nacionalidade);

    Optional<Autor> findByNomeAndDataNascimentoAndNacionalidade(String nome, LocalDate dataNascimento, String nacionalidade);

}
