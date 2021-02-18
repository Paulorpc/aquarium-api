package com.paulorpc.aquarium.api.repositories;

import com.paulorpc.aquarium.api.entities.Taxonomia;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaxonomiaRepository extends JpaRepository<Taxonomia, Long> {

  Optional<Taxonomia> findByEspecie(String especie);

  Optional<Taxonomia> findByGeneroAndEspecie(String genero, String especie);
}
