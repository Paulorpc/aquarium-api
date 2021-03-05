package com.paulorpc.aquarium.api.entities;

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
@Table(name = "teste")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Teste {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "idTeste", nullable = false)
  private Long id;

  @ManyToOne(optional = false, fetch = FetchType.LAZY)
  @JoinColumn(name = "idAquario")
  private Aquario aquario;

  @ManyToOne(optional = false, fetch = FetchType.LAZY)
  @JoinColumn(name = "idParametro")
  private Parametro parametro;

  private Double vlrResultado;

  private String observacao;

  private String unidadeMedida;

  @CreationTimestamp private LocalDateTime dtCadastro;

  @UpdateTimestamp private LocalDateTime dtAtualizacao;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Aquario getAquario() {
    return aquario;
  }

  public void setAquario(Aquario aquario) {
    this.aquario = aquario;
  }

  public Parametro getParametro() {
    return parametro;
  }

  public void setParametro(Parametro parametro) {
    this.parametro = parametro;
  }

  public Double getVlrResultado() {
    return vlrResultado;
  }

  public void setVlrResultado(Double vlrResultado) {
    this.vlrResultado = vlrResultado;
  }

  public String getObservacao() {
    return observacao;
  }

  public void setObservacao(String observacao) {
    this.observacao = observacao;
  }

  public String getUnidadeMedida() {
    return unidadeMedida;
  }

  public void setUnidadeMedida(String unidadeMedida) {
    this.unidadeMedida = unidadeMedida;
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
    return "Teste [id="
        + id
        + ", vlrResultado="
        + vlrResultado
        + ", unidadeMedida="
        + unidadeMedida
        + ", dtCadastro="
        + dtCadastro
        + "]";
  }
}
