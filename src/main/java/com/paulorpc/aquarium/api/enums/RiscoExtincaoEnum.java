package com.paulorpc.aquarium.api.enums;

import java.util.Arrays;
import java.util.Optional;
import com.fasterxml.jackson.annotation.JsonCreator;

/* 
 * https://www.iucnredlist.org/
 *
 * Not Evaluated          NE  
 * Data Deficient         DD  
 * Least Concern          LC
 * Near Threatened        NT
 * Vulnerable             VU
 * Endangered             EN
 * Critically Endangered  CR
 * Extinct in the Wild    EW
 * Extinct                EX
 * 
*/

public enum RiscoExtincaoEnum {
    
  NE("NA", "Não avaliado"),
  DD("DI", "Dados Insuficientes"),
  LC("MP", "Menor Preocupação"),
  NT("QA", "Quase Ameaçada"),
  VU("VU", "Vulnerável"),
  EN("AE", "Ameaçadas de Extinção"),
  CR("CP", "Criticamente em Perigo"),
  EW("EN", "Extinto na Natureza"),
  EX("EX", "Extinto");

  private String codigo;
  private String descricao;  

  RiscoExtincaoEnum(String codigo, String descricao) {
    this.codigo = codigo;      
  }
  
  public String getCodigo() {
    return codigo;
  }

  public String getDescricao() {
    return descricao;
  }

  /***
   * Método com propriedade JsonCreator para forçar caseinsensitive na desserialização JSON.
   * 
   * @param value
   * @return
   */
  @JsonCreator
  public static RiscoExtincaoEnum setValue(String value) {
    Optional<RiscoExtincaoEnum> tipo = Arrays.stream(RiscoExtincaoEnum.values())
        .filter(t -> t.toString().equalsIgnoreCase(value)).findAny();

    return tipo.orElse(null);
  }

}
