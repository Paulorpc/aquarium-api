package com.paulorpc.aquarium.api.services.impl;

import com.paulorpc.aquarium.api.entities.Biota;
import com.paulorpc.aquarium.api.entities.Taxonomia;
import com.paulorpc.aquarium.api.repositories.BiotaRepository;
import com.paulorpc.aquarium.api.repositories.TaxonomiaRepository;
import com.paulorpc.aquarium.api.services.BiotaService;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BiotaServiceImpl implements BiotaService {

  private final Logger log = LoggerFactory.getLogger(BiotaServiceImpl.class);

  @Autowired private BiotaRepository biotaRep;

  @Autowired private TaxonomiaRepository taxonomiaRep;

  @Override
  public Optional<Biota> buscar(Long id) {
    log.info("Buscando seres vivos (biota). Id: {} ", id);
    return biotaRep.findById(id);
  }

  @Override
  public List<Biota> buscarTodos() {
    log.info("Buscando todos seres vivos.");
    return biotaRep.findAll();
  }

  // TODO ao ativar transação gera problema de cadastro corrigir
  // @Transactional
  @Override
  public Biota persistir(Biota biota) throws Exception {
    log.info("Persistindo um novo ser vivo. Biota: {}", biota.toString());
    Taxonomia taxonomia = biota.getTaxonomia();
    biota.setTaxonomia(null);
    Biota biotaNovo = biotaRep.save(biota);
    taxonomia.setId(biotaNovo.getId());
    taxonomiaRep.save(taxonomia);
    biotaNovo.setTaxonomia(taxonomia);
    return biotaNovo;
  }

  @Override
  public Optional<Biota> alterar(Biota biota) throws Exception {
    log.info("Alterando um ser vivo. Biota: {}", biota.toString());

    Optional<Biota> biotaOpt = biotaRep.findByIdAndDeletadoIsFalse(biota.getId());
    if (biotaOpt.isPresent()) {
      biotaOpt = Optional.of(persistir(biota));
    }

    return biotaOpt;
  }

  @Override
  public Optional<Biota> deletar(Long id) {
    log.info("Deletando um ser vivo. Id: {}", id);
    return biotaRep
        .findByIdAndDeletadoIsFalse(id)
        .map(
            v -> {
              v.setDeletado(true);
              return biotaRep.save(v);
            });
  }
}
