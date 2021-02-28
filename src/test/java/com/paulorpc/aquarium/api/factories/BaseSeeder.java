package com.paulorpc.aquarium.api.factories;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public interface BaseSeeder<E, D> {

  /***
   * Gera uma instância E (Entidade) populada fakes.
   * @return T
   */
  public E seed();

  /***
   * Gera uma instância D (DTO) populada fakes.
   * @return T
   */
  public D seedDto();

  /***
   * Gera uma lista de instâncias E(Entidade) fakes.
   * @param n número de entidades
   * @return
   */
  public default List<E> seed(int n) {
    List<E> list = new ArrayList<>();
    IntStream.range(0, n)
        .forEach(
            i -> {
              list.add(seed());
            });
    return list;
  }

  /***
   * Gera uma lista de instâncias D fakes.
   * @param n número de entidades
   * @return
   */
  public default List<D> seedDto(int n) {
    List<D> list = new ArrayList<>();
    IntStream.range(0, n)
        .forEach(
            i -> {
              list.add(seedDto());
            });
    return list;
  }
}
