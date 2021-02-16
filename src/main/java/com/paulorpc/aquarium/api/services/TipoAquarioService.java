package com.paulorpc.aquarium.api.services;

import com.paulorpc.aquarium.api.entities.TipoAquario;
import com.paulorpc.aquarium.api.enums.TipoAguaEnum;
import java.util.List;
import java.util.Optional;

public interface TipoAquarioService {

  /***
   * Busca Tipo Aquario pelo seu Tipo
   *
   * @return Optional<TipoAquario>
   */
  Optional<TipoAquario> buscarPorTipo(String tipo);

  /***
   * Busca Tipo Aquario pelo id
   *
   * @return List<TipoAquario>
   */
  Optional<TipoAquario> buscar(Long id);

  /***
   * Busca Tipo Aquario pelo seu tipo de água
   *
   * @return List<TipoAquario>
   */
  List<TipoAquario> buscarPorTipoAgua(TipoAguaEnum tipoAgua);

  /***
   * Busca todos tipos de aquarios cadastrados
   *
   * @return List<TipoAquario>
   */
  List<TipoAquario> buscarTodos();

  /***
   * Busca todos tipos de aquarios ativos
   *
   * @return List<TipoAquario>
   */
  List<TipoAquario> buscarTodosAtivos();

  /***
   * Cadastra novo tipo de aquário
   *
   * @param TipoAquario
   * @return TipoAquario
   */
  TipoAquario persistir(TipoAquario tipoAquario);

  /***
   * Atualiza cadastro de Tipo Aquário
   *
   * @param tipoAquario
   * @return TipoAquario
   */
  Optional<TipoAquario> alterar(TipoAquario tipoAquario);

  /***
   * Deleta um aquario cadastrado.
   *
   * @param id
   * @return Optional<TipoAquario>
   */
  Optional<TipoAquario> deletar(Long id);
}
