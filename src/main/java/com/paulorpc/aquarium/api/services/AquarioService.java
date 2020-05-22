package com.paulorpc.aquarium.api.services;

import java.util.List;
import java.util.Optional;

import com.paulorpc.aquarium.api.dtos.AquarioDto;
import com.paulorpc.aquarium.api.entities.Aquario;

public interface AquarioService {

  /***
   * Busca aquarios pelo id
   * 
   * @return List<Aquario>
   */
  Optional<Aquario> buscar(int id);

  /***
   * Busca todos aquarios cadastrados
   * 
   * @return List<Aquario>
   */
  List<Aquario> buscarTodos();

  /***
   * Busca todos aquarios ativos (não excluídos pelo usuário)
   * 
   * @return List<Aquario>
   */
  List<Aquario> buscarTodosAtivos();

  /***
   * Cadastra novo aquário
   * 
   * @param aquario
   * @return Aquario
   */
  Aquario persistir(Aquario aquario);

  /***
   * Atualiza cadastro de aquário
   * 
   * @param aquario
   * @return Aquario
   */
  // TODO corrigir o parametro para int
  Optional<Aquario> alterar(AquarioDto aquario);

  /***
   * Deleta um aquario cadastrado. Status é alterado para FALSE.
   * 
   * @param id
   * @return Optional<Aquario>
   */
  Optional<Aquario> deletar(int id);

}
