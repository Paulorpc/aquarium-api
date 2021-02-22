package com.paulorpc.aquarium.api.repositories;

import com.paulorpc.aquarium.api.entities.Equipamento;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EquipamentoRepository extends JpaRepository<Equipamento, Long> {

  @Query(value = "FROM Equipamento e JOIN FETCH e.aquarios a WHERE a.id = :idAquario")
  List<Equipamento> findAllByAquarioIdRetrieveAquario(@Param("idAquario") Long idAquario);

  @Query(value = "FROM Equipamento e JOIN FETCH e.aquarios a")
  List<Equipamento> findAllRetrieveAquario();
}
