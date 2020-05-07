package com.paulorpc.aquarium.api.entities;

import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "Biota")
public class Biota {

  private int idBiota;

  private String nomePopular;

  private String nomeCientifico;

  private Enum tipoAgua;

  // 3 a 5 níveis no máximo. Talvez 3.
  private Enum nivelCuidado;

  private int volumeMinAquario;

  private String alimentação;

  private String habitat;

  private String regiao;

  // tabelas de tamanhos, permitindo add tamanhos personalizados
  private String tamanho;

  // Segundo iucnredlist.org
  private Enum estadoConservacao;

  private String infoAdicional;

  private Taxonomia taxonomia;

  private List<String> fotos;

  private Double avaliacao;

  private boolean excluido;

  private Date dtCadastro;

  private Date dtAtualizacao;

  private String usuarioAtualizacao;

  private boolean bloqueadoAlteracao;

  public int getIdBiota() {
    return idBiota;
  }

  public void setIdBiota(int idBiota) {
    this.idBiota = idBiota;
  }

  public String getNomePopular() {
    return nomePopular;
  }

  public void setNomePopular(String nomePopular) {
    this.nomePopular = nomePopular;
  }

  public String getNomeCientifico() {
    return nomeCientifico;
  }

  public void setNomeCientifico(String nomeCientifico) {
    this.nomeCientifico = nomeCientifico;
  }

  public Enum getTipoAgua() {
    return tipoAgua;
  }

  public void setTipoAgua(Enum tipoAgua) {
    this.tipoAgua = tipoAgua;
  }

  public Enum getNivelCuidado() {
    return nivelCuidado;
  }

  public void setNivelCuidado(Enum nivelCuidado) {
    this.nivelCuidado = nivelCuidado;
  }

  public int getVolumeMinAquario() {
    return volumeMinAquario;
  }

  public void setVolumeMinAquario(int volumeMinAquario) {
    this.volumeMinAquario = volumeMinAquario;
  }

  public String getAlimentação() {
    return alimentação;
  }

  public void setAlimentação(String alimentação) {
    this.alimentação = alimentação;
  }

  public String getHabitat() {
    return habitat;
  }

  public void setHabitat(String habitat) {
    this.habitat = habitat;
  }

  public String getRegiao() {
    return regiao;
  }

  public void setRegiao(String regiao) {
    this.regiao = regiao;
  }

  public String getTamanho() {
    return tamanho;
  }

  public void setTamanho(String tamanho) {
    this.tamanho = tamanho;
  }

  public Enum getEstadoConservacao() {
    return estadoConservacao;
  }

  public void setEstadoConservacao(Enum estadoConservacao) {
    this.estadoConservacao = estadoConservacao;
  }

  public String getInfoAdicional() {
    return infoAdicional;
  }

  public void setInfoAdicional(String infoAdicional) {
    this.infoAdicional = infoAdicional;
  }

  public Taxonomia getTaxonomia() {
    return taxonomia;
  }

  public void setTaxonomia(Taxonomia taxonomia) {
    this.taxonomia = taxonomia;
  }

  public List<String> getFotos() {
    return fotos;
  }

  public void setFotos(List<String> fotos) {
    this.fotos = fotos;
  }

  public Double getAvaliacao() {
    return avaliacao;
  }

  public void setAvaliacao(Double avaliacao) {
    this.avaliacao = avaliacao;
  }

  public boolean isExcluido() {
    return excluido;
  }

  public void setExcluido(boolean excluido) {
    this.excluido = excluido;
  }

  public Date getDtCadastro() {
    return dtCadastro;
  }

  public void setDtCadastro(Date dtCadastro) {
    this.dtCadastro = dtCadastro;
  }

  public Date getDtAtualizacao() {
    return dtAtualizacao;
  }

  public void setDtAtualizacao(Date dtAtualizacao) {
    this.dtAtualizacao = dtAtualizacao;
  }

  public String getUsuarioAtualizacao() {
    return usuarioAtualizacao;
  }

  public void setUsuarioAtualizacao(String usuarioAtualizacao) {
    this.usuarioAtualizacao = usuarioAtualizacao;
  }

  public boolean isBloqueadoAlteracao() {
    return bloqueadoAlteracao;
  }

  public void setBloqueadoAlteracao(boolean bloqueadoAlteracao) {
    this.bloqueadoAlteracao = bloqueadoAlteracao;
  }

  @Override
  public String toString() {
    return "Biota [idBiota=" + idBiota + ", nomePopular=" + nomePopular + ", nomeCientifico="
        + nomeCientifico + ", tipoAgua=" + tipoAgua + ", nivelCuidado=" + nivelCuidado
        + ", volumeMinAquario=" + volumeMinAquario + ", alimentação=" + alimentação + ", habitat="
        + habitat + ", regiao=" + regiao + ", tamanho=" + tamanho + ", estadoConservacao="
        + estadoConservacao + ", infoAdicional=" + infoAdicional + ", taxonomia=" + taxonomia
        + ", fotos=" + fotos + ", avaliacao=" + avaliacao + ", excluido=" + excluido
        + ", dtCadastro=" + dtCadastro + ", dtAtualizacao=" + dtAtualizacao
        + ", usuarioAtualizacao=" + usuarioAtualizacao + ", bloqueadoAlteracao="
        + bloqueadoAlteracao + "]";
  }

}
