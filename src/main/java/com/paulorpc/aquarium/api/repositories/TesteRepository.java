package com.paulorpc.aquarium.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.paulorpc.aquarium.api.entities.Teste;

@Repository
public interface TesteRepository extends JpaRepository<Teste, Long>{

}
