package com.paulorpc.aquarium.api.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.paulorpc.aquarium.api.entities.TipoAquario;

import enums.TipoAguaEnum;

@Repository
public interface TipoAquarioRepository extends JpaRepository<TipoAquario, Integer> {

  Optional<TipoAquario> findByTipo(String tipo);

  Optional<TipoAquario> findById(int id);

  List<TipoAquario> findByTipoAgua(TipoAguaEnum tipoAgua);

  List<TipoAquario> findByStatusIsTrue();

}
