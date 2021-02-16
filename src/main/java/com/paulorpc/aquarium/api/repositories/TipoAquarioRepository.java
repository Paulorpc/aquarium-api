package com.paulorpc.aquarium.api.repositories;

import com.paulorpc.aquarium.api.entities.TipoAquario;
import com.paulorpc.aquarium.api.enums.TipoAguaEnum;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoAquarioRepository extends JpaRepository<TipoAquario, Long> {

  Optional<TipoAquario> findByTipo(String tipo);

  Optional<TipoAquario> findById(Long id);

  List<TipoAquario> findByTipoAgua(TipoAguaEnum tipoAgua);

  List<TipoAquario> findByStatusIsTrue();
}
