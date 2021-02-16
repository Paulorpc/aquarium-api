package com.paulorpc.aquarium.api.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AquarioDto {

  public interface Post {}

  public interface Put {}

  public interface Patch {}

  @NotNull(
      message = "Campo 'id' obrigatório para alteração.",
      groups = {Put.class, Patch.class})
  private Long id;

  @NotEmpty(
      message = "Campo 'nome' é obrigatório.",
      groups = {Post.class, Put.class})
  private String nome;

  @JsonFormat(pattern = "yyyy-MM-dd")
  @PastOrPresent(
      message = "Campo 'dtInicio' não pode ser posterior a data atual.",
      groups = {Post.class, Put.class})
  @NotNull(
      message = "Campo 'dtInicio' não pode ser nulo.",
      groups = {Post.class, Put.class})
  private LocalDate dtInicio;

  @JsonFormat(pattern = "yyyy-MM-dd")
  private LocalDate dtFinal;

  private String tipoAgua;

  private String tamanho;

  @Positive(
      message = "Campo 'volume' deve ser maior que zero.",
      groups = {Post.class, Put.class})
  private Integer volume;

  private String iluminacao;

  private String filtragem;

  private String sistemaCO2;

  private String dosagem;

  private String substrato;

  private String foto;

  private String observacao;

  @NotNull(
      message = "Campo 'status' não pode ser nulo.",
      groups = {Post.class, Put.class})
  private Boolean status;

  private Long idTipoAquario;

  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  @Null(
      message = "Campo 'dtCadastro' deve ser nulo.",
      groups = {Post.class, Put.class})
  private LocalDateTime dtCadastro;

  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  @Null(
      message = "Campo 'dtAtualizacao' deve ser nulo.",
      groups = {Post.class, Put.class})
  private LocalDateTime dtAtualizacao;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
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

  public Integer getVolume() {
    return volume;
  }

  public void setVolume(Integer volume) {
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

  public String getFoto() {
    return foto;
  }

  public void setFoto(String foto) {
    this.foto = foto;
  }

  public String getObservacao() {
    return observacao;
  }

  public void setObservacao(String observacao) {
    this.observacao = observacao;
  }

  public Boolean getStatus() {
    return status;
  }

  public void setStatus(Boolean status) {
    this.status = status;
  }

  public Long getIdTipoAquario() {
    return idTipoAquario;
  }

  public void setIdTipoAquario(Long idTipoAquario) {
    this.idTipoAquario = idTipoAquario;
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
}
