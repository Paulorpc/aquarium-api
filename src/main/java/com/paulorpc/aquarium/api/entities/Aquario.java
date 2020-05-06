package com.paulorpc.aquarium.api.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

@Entity
@Table(name = "Aquario")
public class Aquario implements Serializable {

  private static final long serialVersionUID = 1L;

  private int id;
  private String nome;
  private Date dtInicio;
  private Date dtFinal;
  private String tipoAgua;
  private String tamanho;
  private int volume;
  private String iluminacao;
  private String filtragem;
  private String sistemaCO2;
  private String dosagem;
  private String substrato;
  // private foto;
  private String observacao;
  private boolean status;
  private Date dtCadastro;
  private Date dtAtualizacao;

  private TipoAquario tipoAquario;
  // private Parametro parametros;

  public Aquario() {}

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "idAquario")
  public int getId() {
    return id;
  }

  public void setId(int idAquario) {
    this.id = idAquario;
  }

  @Column(name = "nome", nullable = false)
  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  @Column(name = "dtInicio", nullable = false)
  public Date getDtInicio() {
    return dtInicio;
  }

  public void setDtInicio(Date dtInicio) {
    this.dtInicio = dtInicio;
  }

  @Column(name = "dtFinal", nullable = true)
  public Date getDtFinal() {
    return dtFinal;
  }

  public void setDtFinal(Date dtFinal) {
    this.dtFinal = dtFinal;
  }

  @Column(name = "tipoAgua", nullable = true)
  public String getTipoAgua() {
    return tipoAgua;
  }

  public void setTipoAgua(String tipoAgua) {
    this.tipoAgua = tipoAgua;
  }

  @Column(name = "tamanho", nullable = true)
  public String getTamanho() {
    return tamanho;
  }

  public void setTamanho(String tamanho) {
    this.tamanho = tamanho;
  }

  @Column(name = "volume", nullable = true)
  public int getVolume() {
    return volume;
  }

  public void setVolume(int volume) {
    this.volume = volume;
  }

  @Column(name = "iluminacao", nullable = true)
  public String getIluminacao() {
    return iluminacao;
  }

  public void setIluminacao(String iluminacao) {
    this.iluminacao = iluminacao;
  }

  @Column(name = "filtragem", nullable = true)
  public String getFiltragem() {
    return filtragem;
  }

  public void setFiltragem(String filtragem) {
    this.filtragem = filtragem;
  }

  @Column(name = "sistemaCO2", nullable = true)
  public String getSistemaCO2() {
    return sistemaCO2;
  }

  public void setSistemaCO2(String sistemaCO2) {
    this.sistemaCO2 = sistemaCO2;
  }

  @Column(name = "dosagem", nullable = true)
  public String getDosagem() {
    return dosagem;
  }

  public void setDosagem(String dosagem) {
    this.dosagem = dosagem;
  }

  @Column(name = "substrato", nullable = true)
  public String getSubstrato() {
    return substrato;
  }

  public void setSubstrato(String substrato) {
    this.substrato = substrato;
  }

  @Column(name = "observacao", nullable = true)
  public String getObservacao() {
    return observacao;
  }

  public void setObservacao(String observacao) {
    this.observacao = observacao;
  }

  @Column(name = "status", nullable = false)
  public boolean getStatus() {
    return status;
  }

  public void setStatus(boolean status) {
    this.status = status;
  }

  @Column(name = "dtCadastro", nullable = false)
  public Date getDtCadastro() {
    return dtCadastro;
  }

  public void setDtCadastro(Date dtCadastro) {
    this.dtCadastro = dtCadastro;
  }

  @Column(name = "dtAtualizacao", nullable = false)
  public Date getDtAtualizacao() {
    return dtAtualizacao;
  }

  public void setDtAtualizacao(Date dtAtualizacao) {
    this.dtAtualizacao = dtAtualizacao;
  }

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "idTipoAquario")
  public TipoAquario getTipoAquario() {
    return tipoAquario;
  }

  public void setTipoAquario(TipoAquario tipoAquario) {
    this.tipoAquario = tipoAquario;
  }

  @PreUpdate
  public void preUpdate() {
    dtAtualizacao = new Date();
  }

  @PrePersist
  public void prePersist() {
    Date hojeHora = new Date();
    dtCadastro = hojeHora;
    dtAtualizacao = hojeHora;
  }

  @Override
  public String toString() {
    return "Aquario [id=" + id + ", nome=" + nome + ", dtInicio=" + dtInicio + ", dtFinal="
        + dtFinal + ", tipoAgua=" + tipoAgua + ", tamanho=" + tamanho + ", volume=" + volume
        + ", iluminacao=" + iluminacao + ", filtragem=" + filtragem + ", sistemaCO2=" + sistemaCO2
        + ", dosagem=" + dosagem + ", substrato=" + substrato + ", observacao=" + observacao
        + ", status=" + status + ", dtCadastro=" + dtCadastro + ", dtAtualizacao=" + dtAtualizacao
        + ", tipoAquario=" + tipoAquario + "]";
  }

}
