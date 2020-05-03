package com.paulorpc.aquarium.api.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.paulorpc.aquarium.api.entities.Aquario;

@Repository
public interface AquarioRepository extends JpaRepository<Aquario, Integer> {
	
	Optional<Aquario> findByIdAndStatusIsTrue(int id);
	
	List<Aquario> findByStatusIsTrue();

}
