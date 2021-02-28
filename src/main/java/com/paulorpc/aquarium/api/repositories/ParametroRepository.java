package com.paulorpc.aquarium.api.repositories;

import com.paulorpc.aquarium.api.entities.Parametro;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ParametroRepository extends JpaRepository<Parametro, Long> {

  @Query(
      value = "FROM Parametro p LEFT JOIN FETCH p.procedimentosTeste pt where p.id = :idParametro")
  Optional<Parametro> findByIdRetrieveProcedimentosTeste(@Param("idParametro") Long id);

  @Query(value = "FROM Parametro p LEFT JOIN FETCH p.procedimentosTeste pt")
  List<Parametro> findAllRetrieveProcedimentosTeste();

  @Query(value = "FROM Parametro p JOIN p.aquario a where a.id = :idAquario")
  List<Parametro> findAllFromAquario(@Param("idAquario") Long idAquario);

  @Query(value = "FROM Parametro p JOIN FETCH p.aquario a where a.id = :idAquario")
  List<Parametro> findAllFromAquarioRetrieveAquario(@Param("idAquario") Long idAquario);

  @Query(
      value =
          "FROM Parametro p JOIN p.aquario a LEFT JOIN FETCH p.procedimentosTeste pt where a.id = :idAquario")
  List<Parametro> findAllFromAquarioRetrieveProcedimentosTeste(@Param("idAquario") Long idAquario);
}
