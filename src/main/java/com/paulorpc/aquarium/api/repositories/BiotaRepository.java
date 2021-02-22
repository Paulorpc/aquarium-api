package com.paulorpc.aquarium.api.repositories;

import com.paulorpc.aquarium.api.entities.Biota;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BiotaRepository extends JpaRepository<Biota, Long> {

  @Query(value = "SELECT * FROM biota WHERE deletado = 1", nativeQuery = true)
  List<Biota> findAllDeletadoIsTrue();
}
