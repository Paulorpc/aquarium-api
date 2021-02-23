package com.paulorpc.aquarium.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.paulorpc.aquarium.api.entities.ProcedimentoTeste;

@Repository
public interface ProcedimentoTesteRepository extends JpaRepository<ProcedimentoTeste, Long>{

}
