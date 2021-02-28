package com.paulorpc.aquarium.api.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.paulorpc.aquarium.api.dtos.ValidationAction.Default;
import com.paulorpc.aquarium.api.dtos.ValidationAction.Patch;
import com.paulorpc.aquarium.api.dtos.ValidationAction.Put;
import java.time.LocalDateTime;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProcedimentoTesteDto {

  @Positive(
      message = "Campo 'id' deve ser maior que zero.",
      groups = {Put.class, Patch.class})
  @NotNull(
      message = "Campo 'id' obrigatório para alteração.",
      groups = {Put.class, Patch.class})
  private Long id;

  @Positive(
      message = "Campo 'nroEtapa' deve ser maior que zero.",
      groups = {Default.class})
  @NotNull(
      message = "Campo 'nroEtapa' obrigatório.",
      groups = {Default.class})
  private Integer nroEtapa;

  @NotEmpty(
      message = "Campo 'procedimento' obrigatório.",
      groups = {Default.class})
  private String procedimento;

  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  @Null(
      message = "Campo 'dtCadastro' deve ser nulo.",
      groups = {Default.class})
  private LocalDateTime dtCadastro;

  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  @Null(
      message = "Campo 'dtCadastro' deve ser nulo.",
      groups = {Default.class})
  private LocalDateTime dtAtualizacao;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Integer getNroEtapa() {
    return nroEtapa;
  }

  public void setNroEtapa(Integer nroEtapa) {
    this.nroEtapa = nroEtapa;
  }

  public String getProcedimento() {
    return procedimento;
  }

  public void setProcedimento(String procedimento) {
    this.procedimento = procedimento;
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
