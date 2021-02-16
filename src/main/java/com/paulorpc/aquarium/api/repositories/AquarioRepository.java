package com.paulorpc.aquarium.api.repositories;

import com.paulorpc.aquarium.api.entities.Aquario;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AquarioRepository extends JpaRepository<Aquario, Long> {

  // TODO ALTERAR O PADRÃO DE FIND/FINDALL COM STATUS=TRUE IMPLICITO

  Optional<Aquario> findByIdAndStatusIsTrue(Long id);

  List<Aquario> findByStatusIsTrue();
}
