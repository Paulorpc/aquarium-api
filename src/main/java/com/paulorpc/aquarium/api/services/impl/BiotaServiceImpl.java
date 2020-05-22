package com.paulorpc.aquarium.api.services.impl;

import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.paulorpc.aquarium.api.controllers.BiotaController;
import com.paulorpc.aquarium.api.dtos.BiotaDto;
import com.paulorpc.aquarium.api.entities.Biota;
import com.paulorpc.aquarium.api.entities.Taxonomia;
import com.paulorpc.aquarium.api.repositories.BiotaRepository;
import com.paulorpc.aquarium.api.repositories.TaxonomiaRepository;
import com.paulorpc.aquarium.api.services.BiotaService;

@Service
public class BiotaServiceImpl implements BiotaService {

  private final Logger log = LoggerFactory.getLogger(BiotaServiceImpl.class);

  @Autowired
  private BiotaRepository biotaRep;

  @Autowired
  private TaxonomiaRepository taxonomiaRep;

  public Optional<Biota> buscar(int id) {
    log.info("Buscando seres vivos (biota). Id: {} ", id);
    return biotaRep.findByIdAndDeletadoIsFalse(id);
  }

  public List<Biota> buscarTodos() {
    log.info("Buscando todos seres vivos.");
    return biotaRep.findAll();

  }

  public List<Biota> buscarTodosAtivos() {
    log.info("Buscando todos seres vivos ativos.");
    return biotaRep.findByDeletadoIsFalse();
  }

  @Transactional
  public Biota persistir(Biota biota) throws Exception {
    log.info("Cadastrando um novo ser vivo. Biota: {}", biota.toString());
    Taxonomia taxonomia = biota.getTaxonomia();
    biota.setTaxonomia(null);
    Biota biotaNovo = biotaRep.save(biota);
    taxonomia.setId(biotaNovo.getId());
    taxonomiaRep.save(taxonomia);
    biota.setTaxonomia(taxonomia);
    return biota;
  }

  public Optional<Biota> alterar(BiotaDto biotaDto) throws Exception {
    log.info("Alterando um ser vivo. Biota: {}", biotaDto.toString());

    int id = biotaDto.getId().orElse(0);
    Optional<Biota> biotaOpt = biotaRep.findByIdAndDeletadoIsFalse(id);

    if (biotaOpt.isPresent()) {
      Biota biotaUpd = BiotaController.converteDtoParaObjeto(biotaDto, biotaOpt.get());
      biotaOpt = Optional.of(persistir(biotaUpd));
    }

    return biotaOpt;
  }

  public Optional<Biota> deletar(int id) {
    log.info("Deletando um ser vivo. Id: {}", id);
    return biotaRep.findByIdAndDeletadoIsFalse(id).map(v -> {
      v.setDeletado(true);
      return biotaRep.save(v);
    });
  }

}
