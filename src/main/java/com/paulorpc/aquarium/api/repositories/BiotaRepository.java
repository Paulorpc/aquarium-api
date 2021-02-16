package com.paulorpc.aquarium.api.repositories;

import com.paulorpc.aquarium.api.entities.Biota;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BiotaRepository extends JpaRepository<Biota, Long> {

  Optional<Biota> findByIdAndDeletadoIsFalse(Long id);

  List<Biota> findByDeletadoIsFalse();
}
