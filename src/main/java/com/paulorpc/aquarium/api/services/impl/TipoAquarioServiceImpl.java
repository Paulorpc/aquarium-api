package com.paulorpc.aquarium.api.services.impl;

import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.paulorpc.aquarium.api.entities.TipoAquario;
import com.paulorpc.aquarium.api.enums.TipoAguaEnum;
import com.paulorpc.aquarium.api.repositories.TipoAquarioRepository;
import com.paulorpc.aquarium.api.services.TipoAquarioService;

@Service
public class TipoAquarioServiceImpl implements TipoAquarioService {

  private final Logger log = LoggerFactory.getLogger(TipoAquarioServiceImpl.class);

  @Autowired
  private TipoAquarioRepository tipoAquarioRepository;

  /***
   * Busca Tipo Aquario pelo seu Tipo
   *
   * @return Optional<TipoAquario>
   */
  public Optional<TipoAquario> buscarPorTipo(String tipo) {
    log.info("Buscando tipo aquário. Tipo: {} ", tipo);
    return tipoAquarioRepository.findByTipo(tipo);
  }

  /***
   * Busca Tipo Aquario pelo id
   *
   * @return List<TipoAquario>
   */
  public Optional<TipoAquario> buscar(int id) {
    log.info("Buscando tipo aquário. Id: {} ", id);
    return tipoAquarioRepository.findById(id);
  }

  /***
   * Busca Tipo Aquario pelo seu tipo de água
   *
   * @return List<TipoAquario>
   */
  public List<TipoAquario> buscarPorTipoAgua(TipoAguaEnum tipoAgua) {
    log.info("Buscando tipos de aquário pelo tipo de água. TipoAgua: {} ", tipoAgua.getDescricao());
    return tipoAquarioRepository.findByTipoAgua(tipoAgua);
  }

  /***
   * Busca todos tipos de aquarios cadastrados
   *
   * @return List<TipoAquario>
   */
  public List<TipoAquario> buscarTodos() {
    log.info("Buscando todos tipos de aquários.");
    return tipoAquarioRepository.findAll();

  }

  /***
   * Busca todos tipos de aquarios ativos
   *
   * @return List<TipoAquario>
   */
  public List<TipoAquario> buscarTodosAtivos() {
    log.info("Buscando todos tipos de aquários ativos.");
    return tipoAquarioRepository.findByStatusIsTrue();
  }

  /***
   * Cadastra novo tipo de aquário
   *
   * @param TipoAquario
   * @return TipoAquario
   */
  public TipoAquario cadastrar(TipoAquario tipoAquario) {
    log.info("Cadastrando um novo tipo de aquário. TipoAquário: {}", tipoAquario.toString());
    tipoAquario.setStatus(true);
    return tipoAquarioRepository.save(tipoAquario);
  }

  /***
   * Atualiza cadastro de Tipo Aquário
   *
   * @param tipoAquario
   * @return TipoAquario
   */
  public Optional<TipoAquario> alterar(TipoAquario tipoAquario) {
    log.info("Alterando um tipo de aquário. TipoAquario: {}", tipoAquario.toString());
    return tipoAquarioRepository.findById(tipoAquario.getId())
        .map(v -> tipoAquarioRepository.save(tipoAquario));
  }

  /***
   * Deleta um aquario cadastrado.
   *
   * @param id
   * @return Optional<TipoAquario>
   */
  public Optional<TipoAquario> deletar(int id) {
    log.info("Deletando um tipo de aquário. Id: {}", id);
    Optional<TipoAquario> tipoAquario = tipoAquarioRepository.findById(id);
    tipoAquario.ifPresent(v -> tipoAquarioRepository.deleteById(id));
    return tipoAquario;
  }

}
