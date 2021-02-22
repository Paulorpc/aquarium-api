package com.paulorpc.aquarium.api.services;

import com.paulorpc.aquarium.api.entities.Equipamento;
import java.util.List;
import java.util.Optional;

public interface EquipamentoService {

  /***
   * Busca equipamentp pelo id
   *
   * @return List<Equipamento>
   */
  Optional<Equipamento> buscar(Long id);

  /***
   * Busca equipamentp pelo id e recuperar os aquarios relacionados.
   *
   * @return List<Equipamento>
   */
  Optional<Equipamento> buscarRetornandoAquarios(Long id);

  /***
   * Busca todos equipamentos cadastrados
   *
   * @return List<Equipamento>
   */
  List<Equipamento> buscarTodos();

  /***
   * Busca todos equipamentos cadastrados recuperando os aquarios relacionados.
   *
   * @return List<Equipamento>
   */
  List<Equipamento> buscarTodosRetornandoAquarios();

  /***
   * Busca todos equipamentos de um determinado aquário.
   *
   * @return List<Equipamento>
   */
  List<Equipamento> buscarTodosDoAquario(Long idAquario);

  /***
   * Cadastra novo equipamento
   *
   * @param equipamento
   * @return Equipamento
   * @throws Exception
   */
  Equipamento persistir(Equipamento Equipamento) throws Exception;

  /***
   * Persiti uma lista de novos equipamentos
   * @param equipamentos
   * @return List<Equipamento>
   * @throws Exception
   */
  List<Equipamento> persistirTodos(List<Equipamento> equipamentos) throws Exception;

  /***
   * Cadastra novo equipamento a um determinado aquário
   *
   * @param equipamento
   * @return Equipamento
   * @throws Exception
   */
  Equipamento persistir(Equipamento Equipamento, Long aquarioId) throws Exception;

  /***
   * Atualiza cadastro de equipamento
   *
   * @param Equipamento
   * @return Equipamento
   * @throws Exception
   */
  Equipamento alterar(Equipamento Equipamento) throws Exception;

  /***
   * Deleta um Equipamento cadastrado.
   *
   * @param id
   * @return Optional<Equipamento>
   */
  Equipamento deletar(Long id) throws Exception;
}
