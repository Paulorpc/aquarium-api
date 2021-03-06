package com.paulorpc.aquarium.api.entities;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
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
@Table(name = "procedimentoTeste")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProcedimentoTeste {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "idProcedimento", nullable = false)
  private Long id;

  @ManyToOne(optional = false)
  @JoinColumn(name = "idParametro")
  private Parametro parametro;

  @Column(nullable = false)
  private Integer nroEtapa;

  @Column(nullable = false)
  private String procedimento;

  @CreationTimestamp private LocalDateTime dtCadastro;

  @UpdateTimestamp private LocalDateTime dtAtualizacao;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Parametro getParametro() {
    return parametro;
  }

  public void setParametro(Parametro parametro) {
    this.parametro = parametro;
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

  @Override
  public String toString() {
    return "ProcedimentoTeste [id="
        + id
        + ", parametro="
        + parametro
        + ", nroEtapa="
        + nroEtapa
        + ", procedimento="
        + procedimento
        + "]";
  }
}
