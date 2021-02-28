package com.paulorpc.aquarium.api.services;

import com.paulorpc.aquarium.api.entities.Parametro;
import java.util.List;
import java.util.Optional;

public interface ParametroService {

  /***
   * Busca parâmetro pelo id
   *
   * @return Optional<Parametro>
   */
  Optional<Parametro> buscar(Long id);

  /***
   * Busca parâmetro pelo id e recuperar os procedimentosTeste do parâmetro.
   *
   * @return Optional<Parametro>
   */
  Optional<Parametro> buscarRetornandoProcedimentosTeste(Long id);

  /***
   * Busca todos parâmetros cadastrados
   *
   * @return List<Parametro>
   */
  List<Parametro> buscarTodos();

  /***
   * Busca todos parâmetros cadastrados recuperando os procedimentosTeste do parâmetro.
   *
   * @return List<Parametro>
   */
  List<Parametro> buscarTodosRetornandoProcedimentosTeste();

  /***
   * Busca todos parâmetros de um determinado aquário.
   *
   * @return List<Parametro>
   */
  List<Parametro> buscarTodosDoAquario(Long idAquario);

  /***
   * Busca todos parâmetros de um determinado aquário recuperando todos os procedimentosTeste do parâmetro.
   *
   * @return List<Parametro>
   */
  List<Parametro> buscarTodosDoAquarioRetornandoProcedimentosTeste(Long idAquario);

  /***
   * Cadastra um novo parâmetro
   *
   * @param parametro
   * @return Parametro
   * @throws Exception
   */
  Parametro persistir(Parametro parametro) throws Exception;

  /***
   * Persiti uma lista de novos parâmetros
   * @param parametros
   * @return List<Parametro>
   * @throws Exception
   */
  List<Parametro> persistirTodos(List<Parametro> parametros) throws Exception;

  /***
   * Atualiza um parâmetro
   *
   * @param Parametro
   * @return Parametro
   * @throws Exception
   */
  Parametro alterar(Parametro Parametro) throws Exception;

  /***
   * Deleta um parâmetro pelo id.
   *
   * @param id
   * @return Parametro
   */
  Parametro deletar(Long id) throws Exception;

  /***
   * Deleta todos parâmetros de um determinado aquário.
   *
   * @param IdAquario
   * @return Integer qtde registros deletados
   */
  Integer deletarTodosDoAquario(Long idAquario) throws Exception;
}
