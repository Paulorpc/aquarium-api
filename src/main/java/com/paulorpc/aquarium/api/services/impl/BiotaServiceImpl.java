package com.paulorpc.aquarium.api.services.impl;

import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.paulorpc.aquarium.api.controllers.BiotaController;
import com.paulorpc.aquarium.api.dtos.BiotaDto;
import com.paulorpc.aquarium.api.entities.Biota;
import com.paulorpc.aquarium.api.repositories.BiotaRepository;
import com.paulorpc.aquarium.api.services.BiotaService;

@Service
public class BiotaServiceImpl implements BiotaService {

  private final Logger log = LoggerFactory.getLogger(BiotaServiceImpl.class);

  @Autowired
  private BiotaRepository biotaRep;

  /***
   * Busca seres vivos pelo id
   * 
   * @return List<Biota>
   */
  public Optional<Biota> buscar(int id) {
    log.info("Buscando seres vivos (biota). Id: {} ", id);
    return biotaRep.findByIdAndDeletadoIsFalse(id);
  }

  /***
   * Busca todos seres vivos cadastrados
   * 
   * @return List<Biota>
   */
  public List<Biota> buscarTodos() {
    log.info("Buscando todos seres vivos.");
    return biotaRep.findAll();

  }

  /***
   * Busca todos seres vivos ativos (não excluídos pelo usuário)
   * 
   * @return List<Biota>
   */
  public List<Biota> buscarTodosAtivos() {
    log.info("Buscando todos seres vivos ativos.");
    return biotaRep.findByDeletadoIsFalse();
  }

  /***
   * Cadastra novo ser vivo
   * 
   * @param Biota
   * @return Biota
   */
  public Biota cadastrar(Biota Biota) {
    log.info("Cadastrando um novo ser vivo. Biota: {}", Biota.toString());
    return biotaRep.save(Biota);
  }

  /***
   * Atualiza cadastro de aquário
   * 
   * @param Biota
   * @return Biota
   */
  // TODO corrigir o parametro para int
  public Optional<Biota> alterar(BiotaDto Biota) {
    log.info("Alterando um aquário. Biota: {}", Biota.toString());
    Optional<Biota> BiotaOpt = Biota.getId().flatMap(id -> biotaRep.findByIdAndDeletadoIsFalse(id));
    if (BiotaOpt.isPresent()) {
      Biota BiotaUpd = BiotaController.converteDtoParaObjeto(Biota, BiotaOpt.get());
      BiotaOpt = Optional.of(biotaRep.save(BiotaUpd));
    }
    return BiotaOpt;
  }

  /***
   * Deleta um Biota cadastrado. Status é alterado para FALSE.
   * 
   * @param id
   * @return Optional<Biota>
   */
  public Optional<Biota> deletar(int id) {
    log.info("Deletando um ser vivo. Id: {}", id);
    return biotaRep.findByIdAndDeletadoIsFalse(id).map(v -> {
      v.setDeletado(true);
      return biotaRep.save(v);
    });
  }


}
