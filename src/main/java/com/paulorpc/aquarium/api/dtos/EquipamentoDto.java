package com.paulorpc.aquarium.api.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.paulorpc.aquarium.api.enums.AvaliacaoEnum;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;
import javax.validation.constraints.Future;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
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
public class EquipamentoDto {

  public interface Default {}

  public interface Post extends Default {}

  public interface Put extends Default {}

  public interface Patch extends Default {}

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

  @Positive(
      message = "Campo 'qtde' deve ser maior que zero.",
      groups = {Default.class})
  private int qtde;

  private Set<AquarioDto> aquarios;

  private String tipo;

  @Positive(
      message = "Campo 'vlrUnitario' deve ser maior que zero.",
      groups = {Default.class})
  private BigDecimal vlrUnitario;

  private String fabricante;

  private String modelo;

  @Positive(
      message = "Campo 'potencia' deve ser maior que zero.",
      groups = {Default.class})
  private String potencia;

  @JsonFormat(pattern = "yyyy-MM-dd")
  @Future(
      message = "Campo 'dtSubstituicao' deve ser posterior a data atual.",
      groups = {Default.class})
  private LocalDate dtSubstituicao;

  private String observacao;

  @Min(1)
  @Max(5)
  private AvaliacaoEnum avaliacao;

  @JsonFormat(pattern = "yyyy-MM-dd")
  @PastOrPresent(
      message = "Campo 'dtAquisicao' não pode ser posterior a data atual.",
      groups = {Default.class})
  private LocalDate dtAquisicao;

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

  public int getQtde() {
    return qtde;
  }

  public void setQtde(int qtde) {
    this.qtde = qtde;
  }

  public Set<AquarioDto> getAquarios() {
    return aquarios;
  }

  public void setAquarios(Set<AquarioDto> aquarios) {
    this.aquarios = aquarios;
  }

  public String getTipo() {
    return tipo;
  }

  public void setTipo(String tipo) {
    this.tipo = tipo;
  }

  public BigDecimal getVlrUnitario() {
    return vlrUnitario;
  }

  public void setVlrUnitario(BigDecimal vlrUnitario) {
    this.vlrUnitario = vlrUnitario;
  }

  public String getFabricante() {
    return fabricante;
  }

  public void setFabricante(String fabricante) {
    this.fabricante = fabricante;
  }

  public String getModelo() {
    return modelo;
  }

  public void setModelo(String modelo) {
    this.modelo = modelo;
  }

  public String getPotencia() {
    return potencia;
  }

  public void setPotencia(String potencia) {
    this.potencia = potencia;
  }

  public LocalDate getDtSubstituicao() {
    return dtSubstituicao;
  }

  public void setDtSubstituicao(LocalDate dtSubstituicao) {
    this.dtSubstituicao = dtSubstituicao;
  }

  public String getObservacao() {
    return observacao;
  }

  public void setObservacao(String observacao) {
    this.observacao = observacao;
  }

  public AvaliacaoEnum getAvaliacao() {
    return avaliacao;
  }

  public void setAvaliacao(AvaliacaoEnum avaliacao) {
    this.avaliacao = avaliacao;
  }

  public LocalDate getDtAquisicao() {
    return dtAquisicao;
  }

  public void setDtAquisicao(LocalDate dtAquisicao) {
    this.dtAquisicao = dtAquisicao;
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
