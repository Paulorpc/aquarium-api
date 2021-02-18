package com.paulorpc.aquarium.api.entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "taxonomia_biota")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Taxonomia implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "idBiota", nullable = false)
  private Long id;

  @OneToOne(mappedBy = "taxonomia")
  private Biota biota;

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

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Biota getBiota() {
    return biota;
  }

  public void setBiota(Biota biota) {
    this.biota = biota;
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
    return "Taxonomia [id="
        + id
        + ", dominio="
        + dominio
        + ", reino="
        + reino
        + ", filo="
        + filo
        + ", classe="
        + classe
        + ", ordem="
        + ordem
        + ", familia="
        + familia
        + ", genero="
        + genero
        + ", especie="
        + especie
        + "]";
  }
}
