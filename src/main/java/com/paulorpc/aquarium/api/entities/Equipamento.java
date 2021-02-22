package com.paulorpc.aquarium.api.entities;

import com.paulorpc.aquarium.api.enums.AvaliacaoEnum;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "equipamento")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Equipamento {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "idEquipamento")
  private Long id;

  @Column(nullable = false)
  private String nome;

  @Column(nullable = false)
  private int qtde;

  @Builder.Default
  @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
  @JoinTable(
      name = "equipamento_aquario",
      joinColumns = @JoinColumn(name = "idEquipamento"),
      inverseJoinColumns = @JoinColumn(name = "idAquario"))
  private Set<Aquario> aquarios = new LinkedHashSet<Aquario>();

  private String tipo;

  private BigDecimal vlrUnitario;

  // private foto;

  private String fabricante;

  private String modelo;

  private String potencia;

  private LocalDate dtSubstituicao;

  private String observacao;

  private AvaliacaoEnum avaliacao;

  private LocalDate dtAquisicao;

  @CreationTimestamp private LocalDateTime dtCadastro;

  @UpdateTimestamp private LocalDateTime dtAtualizacao;

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

  public Set<Aquario> getAquarios() {
    return this.aquarios;
  }

  public void setAquarios(Set<Aquario> aquarios) {
    this.aquarios = aquarios;
  }

  public void addAquario(Aquario aquario) {
    this.aquarios.add(aquario);
    aquario.getEquipamentos().add(this);
  }

  public void removeAquario(Aquario aquario) {
    this.aquarios.remove(aquario);
    aquario.getEquipamentos().remove(this);
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
