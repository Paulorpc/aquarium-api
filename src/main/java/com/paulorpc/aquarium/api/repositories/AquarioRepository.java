package com.paulorpc.aquarium.api.repositories;

import com.paulorpc.aquarium.api.entities.Aquario;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AquarioRepository extends JpaRepository<Aquario, Long> {

  Optional<Aquario> findByIdAndStatusIsTrue(Long id);

  List<Aquario> findAllByStatusIsTrue();
}
