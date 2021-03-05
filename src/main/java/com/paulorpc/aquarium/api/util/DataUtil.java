package com.paulorpc.aquarium.api.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class DataUtil {

  /***
   * Retorna data e hora de agora
   *
   * @return String
   */
  public static String getDataHora() {
    return LocalDateTime.now().toString();
  }

  /***
   * Retorna LocalDateTime com hora 00:00
   *
   * @return
   */
  public static LocalDateTime getLocalDateTime(int ano, int mes, int dia) {
    return LocalDateTime.of(LocalDate.of(ano, mes, dia), LocalTime.MIN);
  }
}
