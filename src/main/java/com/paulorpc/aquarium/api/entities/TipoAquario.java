package com.paulorpc.aquarium.api.entities;

import com.paulorpc.aquarium.api.enums.TipoAguaEnum;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "tipoAquario")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TipoAquario implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "idTipoAquario", nullable = false)
  private Long id;

  @Column(nullable = false)
  private String tipo;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private TipoAguaEnum tipoAgua;

  @Column(nullable = false)
  private boolean status;

  @Column(nullable = false)
  private String descricao;

  @CreationTimestamp
  @Column(name = "dtCadastro", nullable = false)
  private LocalDateTime dtCadastro;

  @UpdateTimestamp
  @Column(name = "dtAtualizacao", nullable = false)
  private LocalDateTime dtAtualizacao;

  @OneToMany(mappedBy = "tipoAquario", fetch = FetchType.EAGER)
  private List<Aquario> aquarios;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getTipo() {
    return tipo;
  }

  public void setTipo(String tipo) {
    this.tipo = tipo;
  }

  public TipoAguaEnum getTipoAgua() {
    return tipoAgua;
  }

  public void setTipoAgua(TipoAguaEnum tipoAgua) {
    this.tipoAgua = tipoAgua;
  }

  public boolean isStatus() {
    return status;
  }

  public void setStatus(boolean status) {
    this.status = status;
  }

  public String getDescricao() {
    return descricao;
  }

  public void setDescricao(String descricao) {
    this.descricao = descricao;
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

  public List<Aquario> getAquarios() {
    return aquarios;
  }

  public void setAquarios(List<Aquario> aquarios) {
    this.aquarios = aquarios;
  }

  @Override
  public String toString() {
    return "TipoAquario [id="
        + id
        + ", tipo="
        + tipo
        + ", tipoAgua="
        + tipoAgua
        + ", status="
        + status
        + ", descricao="
        + descricao
        + ", dtCadastro="
        + dtCadastro
        + ", dtAtualizacao="
        + dtAtualizacao
        + "]";
  }
}
