package com.paulorpc.aquarium.api.repositories;

import com.paulorpc.aquarium.api.entities.Teste;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TesteRepository extends JpaRepository<Teste, Long> {}
