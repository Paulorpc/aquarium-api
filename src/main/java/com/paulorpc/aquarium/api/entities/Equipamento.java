package com.paulorpc.aquarium.api.entities;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Equipamento {

  private int idEquipamento;
  private String nome;
  private LocalDate dtAquisicao;
  private String tipo;
  private BigDecimal vlrUnitario;
  private int qtde;
  // private foto;
  private String fabricante;
  private String modelo;
  private String potencia;
  private String dtSubstituicao;
  private String observacao;
  // private Double avaliacao;
  private String dtCadastro;
  private String dtAtualizacao;

  public Equipamento() {}

  public int getIdEquipamento() {
    return idEquipamento;
  }

  public void setIdEquipamento(int idEquipamento) {
    this.idEquipamento = idEquipamento;
  }

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public LocalDate getDtAquisicao() {
    return dtAquisicao;
  }

  public void setDtAquisicao(LocalDate dtAquisicao) {
    this.dtAquisicao = dtAquisicao;
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

  public int getQtde() {
    return qtde;
  }

  public void setQtde(int qtde) {
    this.qtde = qtde;
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

  public String getDtSubstituicao() {
    return dtSubstituicao;
  }

  public void setDtSubstituicao(String dtSubstituicao) {
    this.dtSubstituicao = dtSubstituicao;
  }

  public String getObservacao() {
    return observacao;
  }

  public void setObservacao(String observacao) {
    this.observacao = observacao;
  }

  public String getDtCadastro() {
    return dtCadastro;
  }

  public void setDtCadastro(String dtCadastro) {
    this.dtCadastro = dtCadastro;
  }

  public String getDtAtualizacao() {
    return dtAtualizacao;
  }

  public void setDtAtualizacao(String dtAtualizacao) {
    this.dtAtualizacao = dtAtualizacao;
  }
}
