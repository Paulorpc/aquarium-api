package com.paulorpc.aquarium.api.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
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
public class ParametroDto implements ValidationAction {

  @Positive(
      message = "Campo 'id' deve ser maior que zero.",
      groups = {Put.class, Patch.class})
  @NotNull(
      message = "Campo 'id' obrigatório para alteração.",
      groups = {Put.class, Patch.class})
  private Long id;

  @NotEmpty(
      message = "Campo 'nome' é obrigatório.",
      groups = {Default.class})
  private String nome;

  private String abreviacaoNome;

  @Positive(
      message = "Campo 'escalaInicio' deve ser maior que zero.",
      groups = {Default.class})
  private Double escalaInicio;

  @Positive(
      message = "Campo 'escalaFim' deve ser maior que zero.",
      groups = {Default.class})
  private Double escalaFim;

  @Positive(
      message = "Campo 'vlrIdealInicio' deve ser maior que zero.",
      groups = {Default.class})
  private Double vlrIdealInicio;

  @Positive(
      message = "Campo 'vlrIdealFim' deve ser maior que zero.",
      groups = {Default.class})
  private Double vlrIdealFim;

  private String unidadeMedida;

  @Positive(
      message = "Campo 'id' deve ser maior que zero.",
      groups = {Default.class})
  @NotNull(
      message = "Campo 'aquarioId' obrigatório.",
      groups = {Default.class})
  private Long aquarioId;

  @Null(
      message = "Campo 'procedimentosTeste' deve ser nulo.",
      groups = {Default.class})
  @Builder.Default
  private List<ProcedimentoTesteDto> procedimentosTeste = new ArrayList<>();

  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  @Null(
      message = "Campo 'dtCadastro' deve ser nulo.",
      groups = {Default.class})
  private LocalDateTime dtCadastro;

  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  @Null(
      message = "Campo 'dtAtualizacao' deve ser nulo.",
      groups = {Default.class})
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

  public String getAbreviacaoNome() {
    return abreviacaoNome;
  }

  public void setAbreviacaoNome(String abreviacaoNome) {
    this.abreviacaoNome = abreviacaoNome;
  }

  public Double getEscalaInicio() {
    return escalaInicio;
  }

  public void setEscalaInicio(Double escalaInicio) {
    this.escalaInicio = escalaInicio;
  }

  public Double getEscalaFim() {
    return escalaFim;
  }

  public void setEscalaFim(Double escalaFim) {
    this.escalaFim = escalaFim;
  }

  public Double getVlrIdealInicio() {
    return vlrIdealInicio;
  }

  public void setVlrIdealInicio(Double vlrIdealInicio) {
    this.vlrIdealInicio = vlrIdealInicio;
  }

  public Double getVlrIdealFim() {
    return vlrIdealFim;
  }

  public void setVlrIdealFim(Double vlrIdealFim) {
    this.vlrIdealFim = vlrIdealFim;
  }

  public String getUnidadeMedida() {
    return unidadeMedida;
  }

  public void setUnidadeMedida(String unidadeMedida) {
    this.unidadeMedida = unidadeMedida;
  }

  public Long getAquarioId() {
    return aquarioId;
  }

  public void setAquarioId(Long aquarioId) {
    this.aquarioId = aquarioId;
  }

  public List<ProcedimentoTesteDto> getProcedimentosTeste() {
    return procedimentosTeste;
  }

  public void setProcedimentosTeste(List<ProcedimentoTesteDto> procedimentosTeste) {
    this.procedimentosTeste = procedimentosTeste;
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
