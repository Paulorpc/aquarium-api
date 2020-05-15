package com.paulorpc.aquarium.api.services;

import java.util.List;
import java.util.Optional;
import com.paulorpc.aquarium.api.dtos.BiotaDto;
import com.paulorpc.aquarium.api.entities.Biota;

public interface BiotaService {

  /***
   * Busca seres vivos pelo id
   *
   * @return List<Biota>
   */
  Optional<Biota> buscar(int id);

  /***
   * Busca todos seres vivos cadastrados
   *
   * @return List<Biota>
   */
  List<Biota> buscarTodos();

  /***
   * Busca todos seres vivos ativos (não excluídos pelo usuário)
   *
   * @return List<Biota>
   */
  List<Biota> buscarTodosAtivos();

  /***
   * Cadastra novo ser vivo
   *
   * @param Biota
   * @return Biota
   */
  Biota cadastrar(Biota Biota);

  /***
   * Atualiza cadastro de aquário
   *
   * @param Biota
   * @return Biota
   */
  // TODO corrigir o parametro para int
  Optional<Biota> alterar(BiotaDto Biota);

  /***
   * Deleta um Biota cadastrado. Status é alterado para FALSE.
   *
   * @param id
   * @return Optional<Biota>
   */
  Optional<Biota> deletar(int id);

}
