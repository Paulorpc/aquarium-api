package com.paulorpc.aquarium.api.repositories;

import com.paulorpc.aquarium.api.entities.ProcedimentoTeste;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProcedimentoTesteRepository extends JpaRepository<ProcedimentoTeste, Long> {

  @Query(value = "FROM ProcedimentoTeste pt JOIN pt.parametro p WHERE p.id = :idParametro")
  List<ProcedimentoTeste> findAllByParametroId(Long idParametro);
}
