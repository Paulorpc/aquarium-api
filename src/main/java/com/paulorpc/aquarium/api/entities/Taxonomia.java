package com.paulorpc.aquarium.api.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "BiotaTaxonomia")
public class Taxonomia {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "idTaxonomia", nullable = false)
  private int idTaxonomia;  

  @Column(name = "dominio", nullable = true)
  private String dominio;

  @Column(name = "reino", nullable = true)
  private String reino;

  @Column(name = "filo", nullable = true)
  private String filo;

  @Column(name = "classe", nullable = true)
  private String classe;

  @Column(name = "ordem", nullable = true)
  private String ordem;

  @Column(name = "familia", nullable = true)
  private String familia;

  @Column(name = "genero", nullable = true)
  private String genero;

  @Column(name = "especie", nullable = true)
  private String especie;

  public Taxonomia() {}

  public int getIdTaxonomia() {
    return idTaxonomia;
  }

  public void setIdTaxonomia(int idTaxonomia) {
    this.idTaxonomia = idTaxonomia;
  }

  public String getDominio() {
    return dominio;
  }

  public void setDominio(String dominio) {
    this.dominio = dominio;
  }

  public String getReino() {
    return reino;
  }

  public void setReino(String reino) {
    this.reino = reino;
  }

  public String getFilo() {
    return filo;
  }

  public void setFilo(String filo) {
    this.filo = filo;
  }

  public String getClasse() {
    return classe;
  }

  public void setClasse(String classe) {
    this.classe = classe;
  }

  public String getOrdem() {
    return ordem;
  }

  public void setOrdem(String ordem) {
    this.ordem = ordem;
  }

  public String getFamilia() {
    return familia;
  }

  public void setFamilia(String familia) {
    this.familia = familia;
  }

  public String getGenero() {
    return genero;
  }

  public void setGenero(String genero) {
    this.genero = genero;
  }

  public String getEspecie() {
    return especie;
  }

  public void setEspecie(String especie) {
    this.especie = especie;
  }

  @Override
  public String toString() {
    return "Taxonomia [idTaxonomia=" + idTaxonomia + ", dominio=" + dominio
        + ", reino=" + reino + ", filo=" + filo + ", classe=" + classe + ", ordem=" + ordem
        + ", familia=" + familia + ", genero=" + genero + ", especie=" + especie + "]";
  }

}
