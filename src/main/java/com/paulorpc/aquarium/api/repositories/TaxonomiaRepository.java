package com.paulorpc.aquarium.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.paulorpc.aquarium.api.entities.Taxonomia;

public interface TaxonomiaRepository extends JpaRepository<Taxonomia, Integer>{

}
