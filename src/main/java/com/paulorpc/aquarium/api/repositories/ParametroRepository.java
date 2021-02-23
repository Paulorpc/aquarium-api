package com.paulorpc.aquarium.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.paulorpc.aquarium.api.entities.Parametro;

@Repository
public interface ParametroRepository extends JpaRepository<Parametro, Long> {

}
