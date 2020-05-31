package com.paulorpc.aquarium.api.entities;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import com.paulorpc.aquarium.api.enums.TipoAguaEnum;

@Entity
@Table(name = "TipoAquario")
public class TipoAquario implements Serializable {

  private static final long serialVersionUID = 1L;

  private int id;
  private String tipo;
  private TipoAguaEnum tipoAgua;
  private boolean status;
  private String descricao;
  private Date dtCadastro;
  private Date dtAtualizacao;

  private List<Aquario> aquarios;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "idTipoAquario", nullable = false)
  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  @Column(nullable = false)
  public String getTipo() {
    return tipo;
  }

  public void setTipo(String tipo) {
    this.tipo = tipo;
  }

  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  public TipoAguaEnum getTipoAgua() {
    return tipoAgua;
  }

  public void setTipoAgua(TipoAguaEnum tipoAgua) {
    this.tipoAgua = tipoAgua;
  }

  @Column(nullable = false)
  public boolean isStatus() {
    return status;
  }

  public void setStatus(boolean status) {
    this.status = status;
  }

  @Column(nullable = false)
  public String getDescricao() {
    return descricao;
  }

  public void setDescricao(String descricao) {
    this.descricao = descricao;
  }

  @Column(name = "dtCadastro", nullable = false)
  public Date getDtCadastro() {
    return dtCadastro;
  }

  public void setDtCadastro(Date dtCadastro) {
    this.dtCadastro = dtCadastro;
  }

  @Column(name = "dtAtualizacao", nullable = false)
  public Date getDtAtualizacao() {
    return dtAtualizacao;
  }

  public void setDtAtualizacao(Date dtAtualizacao) {
    this.dtAtualizacao = dtAtualizacao;
  }

  @OneToMany(mappedBy = "tipoAquario", fetch = FetchType.EAGER)
  public List<Aquario> getAquarios() {
    return aquarios;
  }

  public void setAquarios(List<Aquario> aquarios) {
    this.aquarios = aquarios;
  }

  @PreUpdate
  public void preUpdate() {
    dtAtualizacao = new Date();
  }

  @PrePersist
  public void prePersist() {
    Date hojeHora = new Date();
    dtCadastro = hojeHora;
    dtAtualizacao = hojeHora;
  }

  @Override
  public String toString() {
    return "TipoAquario [id=" + id + ", tipo=" + tipo + ", tipoAgua=" + tipoAgua + ", status="
        + status + ", descricao=" + descricao + ", dtCadastro=" + dtCadastro + ", dtAtualizacao="
        + dtAtualizacao + "]";
  }

}
