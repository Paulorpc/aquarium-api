package com.paulorpc.aquarium.api.dtos;

import java.util.Optional;

public class TaxonomiaDto {
  
  private Optional<String> dominio = Optional.empty();
  private Optional<String> reino = Optional.empty();
  private Optional<String> filo = Optional.empty();
  private Optional<String> classe = Optional.empty();
  private Optional<String> ordem = Optional.empty();
  private Optional<String> familia = Optional.empty();
  private Optional<String> genero = Optional.empty();
  private Optional<String> especie = Optional.empty();
  
  public Optional<String> getDominio() {
    return dominio;
  }
  public void setDominio(Optional<String> dominio) {
    this.dominio = dominio;
  }
  public Optional<String> getReino() {
    return reino;
  }
  public void setReino(Optional<String> reino) {
    this.reino = reino;
  }
  public Optional<String> getFilo() {
    return filo;
  }
  public void setFilo(Optional<String> filo) {
    this.filo = filo;
  }
  public Optional<String> getClasse() {
    return classe;
  }
  public void setClasse(Optional<String> classe) {
    this.classe = classe;
  }
  public Optional<String> getOrdem() {
    return ordem;
  }
  public void setOrdem(Optional<String> ordem) {
    this.ordem = ordem;
  }
  public Optional<String> getFamilia() {
    return familia;
  }
  public void setFamilia(Optional<String> familia) {
    this.familia = familia;
  }
  public Optional<String> getGenero() {
    return genero;
  }
  public void setGenero(Optional<String> genero) {
    this.genero = genero;
  }
  public Optional<String> getEspecie() {
    return especie;
  }
  public void setEspecie(Optional<String> especie) {
    this.especie = especie;
  }
  
}
