package com.paulorpc.aquarium.api.repositories;

import com.paulorpc.aquarium.api.entities.Taxonomia;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaxonomiaRepository extends JpaRepository<Taxonomia, Long> {}
