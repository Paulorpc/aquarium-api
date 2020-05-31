package com.paulorpc.aquarium.api.repositories;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.paulorpc.aquarium.api.entities.Biota;

@Repository
public interface BiotaRepository extends JpaRepository<Biota, Integer> {

  Optional<Biota> findByIdAndDeletadoIsFalse(int id);

  List<Biota> findByDeletadoIsFalse();

}
