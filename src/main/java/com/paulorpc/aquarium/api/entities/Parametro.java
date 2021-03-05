package com.paulorpc.aquarium.api.entities;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "parametro")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Parametro {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "idParametro", nullable = false)
  private Long id;

  @Column(nullable = false)
  private String nome;

  private String abreviacaoNome;

  @Column(nullable = false)
  private Double escalaInicio;

  @Column(nullable = false)
  private Double escalaFim;

  @Column(nullable = false)
  private Double vlrIdealInicio;

  @Column(nullable = false)
  private Double vlrIdealFim;

  @Column(nullable = false)
  private String unidadeMedida;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "idAquario")
  private Aquario aquario;

  @Builder.Default
  @OneToMany(mappedBy = "parametro")
  private List<Teste> historicoTestes = new ArrayList<>();

  @Builder.Default
  @OneToMany(mappedBy = "parametro")
  private List<ProcedimentoTeste> procedimentosTeste = new ArrayList<>();

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

  public Aquario getAquario() {
    return aquario;
  }

  public void setAquario(Aquario aquario) {
    this.aquario = aquario;
  }

  public List<Teste> getHistoricoTestes() {
    return historicoTestes;
  }

  public void setHistoricoTestes(List<Teste> historicoTestes) {
    this.historicoTestes = historicoTestes;
  }

  public void addHistoricoTeste(Teste teste) {
    historicoTestes.add(teste);
    teste.setParametro(this);
  }

  public void removeHistoricoTeste(Teste teste) {
    historicoTestes.remove(teste);
    teste.setParametro(null);
  }

  public List<ProcedimentoTeste> getProcedimentosTeste() {
    return procedimentosTeste;
  }

  public void setProcedimentosTeste(List<ProcedimentoTeste> procedimentosTeste) {
    this.procedimentosTeste = procedimentosTeste;
  }

  public void addProcedimentoTeste(ProcedimentoTeste procedimentoTeste) {
    this.procedimentosTeste.add(procedimentoTeste);
    procedimentoTeste.setParametro(this);
  }

  public void removeProcedimentoTeste(ProcedimentoTeste procedimentoTeste) {
    this.procedimentosTeste.remove(procedimentoTeste);
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

  public void setDtAtualizacao(LocalDateTime dtAtualização) {
    this.dtAtualizacao = dtAtualização;
  }

  @Override
  public String toString() {
    return "Parametro [id="
        + id
        + ", nome="
        + nome
        + ", abreviacaoNome="
        + abreviacaoNome
        + ", vlrIdealInicio="
        + vlrIdealInicio
        + ", vlrIdealFim="
        + vlrIdealFim
        + "]";
  }
}
