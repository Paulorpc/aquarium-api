package com.paulorpc.aquarium.api.entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "aquario")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Aquario implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "idAquario")
  private Long id;

  @Column(name = "nome", nullable = false)
  private String nome;

  @Column(name = "dtInicio", nullable = false)
  private LocalDate dtInicio;

  @Column(name = "dtFinal", nullable = true)
  private LocalDate dtFinal;

  @Column(name = "tipoAgua", nullable = true)
  private String tipoAgua;

  @Column(name = "tamanho", nullable = true)
  private String tamanho;

  @Column(name = "volume", nullable = true)
  private int volume;

  @Column(name = "iluminacao", nullable = true)
  private String iluminacao;

  @Column(name = "filtragem", nullable = true)
  private String filtragem;

  @Column(name = "sistemaCO2", nullable = true)
  private String sistemaCO2;

  @Column(name = "dosagem", nullable = true)
  private String dosagem;

  @Column(name = "substrato", nullable = true)
  private String substrato;

  // private foto;

  @Column(name = "observacao", nullable = true)
  private String observacao;

  @Column(name = "status", nullable = false)
  private boolean status;

  @CreationTimestamp
  @Column(name = "dtCadastro", nullable = false)
  private LocalDateTime dtCadastro;

  @UpdateTimestamp
  @Column(name = "dtAtualizacao", nullable = false)
  private LocalDateTime dtAtualizacao;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "idTipoAquario")
  private TipoAquario tipoAquario;

  // private Parametro parametros;

  public Long getId() {
    return id;
  }

  public void setId(Long idAquario) {
    this.id = idAquario;
  }

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public LocalDate getDtInicio() {
    return dtInicio;
  }

  public void setDtInicio(LocalDate dtInicio) {
    this.dtInicio = dtInicio;
  }

  public LocalDate getDtFinal() {
    return dtFinal;
  }

  public void setDtFinal(LocalDate dtFinal) {
    this.dtFinal = dtFinal;
  }

  public String getTipoAgua() {
    return tipoAgua;
  }

  public void setTipoAgua(String tipoAgua) {
    this.tipoAgua = tipoAgua;
  }

  public String getTamanho() {
    return tamanho;
  }

  public void setTamanho(String tamanho) {
    this.tamanho = tamanho;
  }

  public int getVolume() {
    return volume;
  }

  public void setVolume(int volume) {
    this.volume = volume;
  }

  public String getIluminacao() {
    return iluminacao;
  }

  public void setIluminacao(String iluminacao) {
    this.iluminacao = iluminacao;
  }

  public String getFiltragem() {
    return filtragem;
  }

  public void setFiltragem(String filtragem) {
    this.filtragem = filtragem;
  }

  public String getSistemaCO2() {
    return sistemaCO2;
  }

  public void setSistemaCO2(String sistemaCO2) {
    this.sistemaCO2 = sistemaCO2;
  }

  public String getDosagem() {
    return dosagem;
  }

  public void setDosagem(String dosagem) {
    this.dosagem = dosagem;
  }

  public String getSubstrato() {
    return substrato;
  }

  public void setSubstrato(String substrato) {
    this.substrato = substrato;
  }

  public String getObservacao() {
    return observacao;
  }

  public void setObservacao(String observacao) {
    this.observacao = observacao;
  }

  public boolean getStatus() {
    return status;
  }

  public void setStatus(boolean status) {
    this.status = status;
  }

  public LocalDateTime getDtCadastro() {
    return dtCadastro;
  }

  public void setDtCadastro(LocalDateTime dtCadastro) {
    this.dtCadastro = dtCadastro;
  }

  public LocalDateTime getDtAtualizacao() {
    return dtAtualizacao;
  }
  
  public void setDtAtualizacao(LocalDateTime dtAtualizacao) {
    this.dtAtualizacao = dtAtualizacao;
  }

  public TipoAquario getTipoAquario() {
    if (tipoAquario == null) {
      tipoAquario = new TipoAquario();
    }
    return tipoAquario;
  }

  public void setTipoAquario(TipoAquario tipoAquario) {
    this.tipoAquario = tipoAquario;
  }

  @Override
  public String toString() {
    return "Aquario [id="
        + id
        + ", nome="
        + nome
        + ", tipoAgua="
        + tipoAgua
        + ", tamanho="
        + tamanho
        + ", volume="
        + volume
        + ", observacao="
        + observacao
        + ", status="
        + status
        + ", dtCadastro="
        + dtCadastro
        + ", dtAtualizacao="
        + dtAtualizacao
        + ", tipoAquario="
        + tipoAquario
        + "]";
  }
}
