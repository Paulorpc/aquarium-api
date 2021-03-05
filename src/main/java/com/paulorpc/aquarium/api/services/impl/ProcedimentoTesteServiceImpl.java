package com.paulorpc.aquarium.api.services.impl;

import com.paulorpc.aquarium.api.entities.Aquario;
import com.paulorpc.aquarium.api.entities.Parametro;
import com.paulorpc.aquarium.api.entities.ProcedimentoTeste;
import com.paulorpc.aquarium.api.exceptions.NotFoundException;
import com.paulorpc.aquarium.api.repositories.ProcedimentoTesteRepository;
import com.paulorpc.aquarium.api.services.ProcedimentoTesteService;
import java.util.ArrayList;
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
  public List<ProcedimentoTeste> buscarTodos(Long id, Class<?> classe) {
    StringBuilder logInfo = new StringBuilder("Buscando todos procedimento de teste ");
    if (classe.getClass().equals(Parametro.class)) {
      logInfo.append("do parâmetro: " + id);
      return procedimentoRep.findAllByParametroId(id);
    } else if (classe.getClass().equals(Aquario.class)) {
      logInfo.append("do aquário: " + id);
      return procedimentoRep.findAllByAquarioId(id);
    } else {
      return new ArrayList<>();
    }
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
    List<ProcedimentoTeste> procedimentos = buscarTodos(idParametro, Parametro.class);
    procedimentoRep.deleteAll(procedimentos);
    return procedimentos.size();
  }

  @Override
  public Integer deletarTodosDoAquario(Long idAquario) throws Exception {
    log.info("Deletando todos procedimentos de teste do aquário: {} ", idAquario);
    List<ProcedimentoTeste> procedimentos = buscarTodos(idAquario, Aquario.class);
    procedimentoRep.deleteAll(procedimentos);
    return procedimentos.size();
  }
}
