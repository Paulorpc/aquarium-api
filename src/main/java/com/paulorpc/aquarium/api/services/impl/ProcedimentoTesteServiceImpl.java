package com.paulorpc.aquarium.api.services.impl;

import com.paulorpc.aquarium.api.entities.ProcedimentoTeste;
import com.paulorpc.aquarium.api.exceptions.NotFoundException;
import com.paulorpc.aquarium.api.repositories.ProcedimentoTesteRepository;
import com.paulorpc.aquarium.api.services.ProcedimentoTesteService;
import java.util.List;
import java.util.Optional;
import javax.persistence.EntityExistsException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProcedimentoTesteServiceImpl implements ProcedimentoTesteService {

  private final Logger log = LoggerFactory.getLogger(ProcedimentoTesteServiceImpl.class);

  @Autowired private ProcedimentoTesteRepository procedimentoRep;

  @Override
  public Optional<ProcedimentoTeste> buscar(Long id) {
    log.info("Buscando procedimento de teste: {} ", id);
    return procedimentoRep.findById(id);
  }

  @Override
  public List<ProcedimentoTeste> buscarTodos(Optional<Long> idParametro) {
    String logInfo = "Buscando todos procedimento de teste";
    if (idParametro.isEmpty()) {
      log.info(logInfo + " filtrando por parâmetro: {}", idParametro.get());
      return procedimentoRep.findAll();
    }
    log.info(logInfo);
    return procedimentoRep.findAllByParametroId(idParametro.get());
  }

  @Override
  public ProcedimentoTeste persistir(ProcedimentoTeste procedimento) throws Exception {
    log.info("Persistindo procedimento de teste: {} ", procedimento);
    if (procedimento.getId() != null) {
      throw new EntityExistsException("Objeto já persistido anteriormente.");
    }
    return procedimentoRep.save(procedimento);
  }

  @Override
  public ProcedimentoTeste alterar(ProcedimentoTeste procedimento) throws Exception {
    log.info("Alterando procedimento de teste: {} ", procedimento);
    if (!procedimentoRep.existsById(procedimento.getId())) {
      throw new NotFoundException(
          "Não foi possível localizar o procedimentoTeste. Id: " + procedimento.getId());
    }
    return procedimentoRep.save(procedimento);
  }

  @Override
  public ProcedimentoTeste deletar(Long id) throws Exception {
    log.info("Deletando procedimento de teste: {} ", id);
    return procedimentoRep
        .findById(id)
        .map(
            p -> {
              procedimentoRep.delete(p);
              return p;
            })
        .orElseThrow(
            () ->
                new NotFoundException("Não foi possível localizar o procedimentoTeste. Id: " + id));
  }

  @Override
  public Integer deletarTodosDoParametro(Long idParametro) throws Exception {
    log.info("Deletando todos procedimentos de teste do parâmetro: {} ", idParametro);
    List<ProcedimentoTeste> procedimentos = buscarTodos(Optional.of(idParametro));
    procedimentoRep.deleteAll(procedimentos);
    return procedimentos.size();
  }
}
