package com.paulorpc.aquarium.api.repositories;

import com.paulorpc.aquarium.api.entities.Teste;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TesteRepository extends JpaRepository<Teste, Long> {

  @Query("FROM Teste t JOIN t.aquario a WHERE a.id = ?1")
  List<Teste> findAllFromAquario(Long idAquario);

  @Query("FROM Teste t JOIN t.aquario a WHERE a.id = ?1 and t.dtCadastro BETWEEN ?2 and ?3")
  List<Teste> findAllFromAquario(Long idAquario, LocalDateTime inicio, LocalDateTime fim);

  @Query("FROM Teste t JOIN t.aquario a JOIN t.parametro p WHERE a.id = ?1 and p.id = ?2")
  List<Teste> findAllFromAquarioEParametro(Long idAquario, Long idParametro);

  @Query(
      "FROM Teste t JOIN t.aquario a JOIN t.parametro p WHERE a.id = ?1 and p.id = ?2 and t.dtCadastro BETWEEN ?3 and ?4")
  List<Teste> findAllFromAquarioEParametroPorPeriodo(
      Long idAquario, Long idParametro, LocalDateTime inicio, LocalDateTime fim);
}
