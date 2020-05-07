package com.paulorpc.aquarium.api.entities;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="AquarioBiota")
public class AquarioBiota {

  private int idAquario;
  private int idBiota;
  private String nome;
  private Date dtAquisicao;
  private Date dtNascimento;
  private Date dtObito;
  private int qtde;
  private Date dtCadastro;
  private Date dtAtualizacao;
  private String usuarioAtualizacao;
  private boolean bloqueadoAlteracao;

  public int getIdAquario() {
    return idAquario;
  }

  public void setIdAquario(int idAquario) {
    this.idAquario = idAquario;
  }

  public int getIdBiota() {
    return idBiota;
  }

  public void setIdBiota(int idBiota) {
    this.idBiota = idBiota;
  }

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public Date getDtAquisicao() {
    return dtAquisicao;
  }

  public void setDtAquisicao(Date dtAquisicao) {
    this.dtAquisicao = dtAquisicao;
  }

  public Date getDtNascimento() {
    return dtNascimento;
  }

  public void setDtNascimento(Date dtNascimento) {
    this.dtNascimento = dtNascimento;
  }

  public Date getDtObito() {
    return dtObito;
  }

  public void setDtObito(Date dtObito) {
    this.dtObito = dtObito;
  }

  public int getQtde() {
    return qtde;
  }

  public void setQtde(int qtde) {
    this.qtde = qtde;
  }

  public Date getDtCadastro() {
    return dtCadastro;
  }

  public void setDtCadastro(Date dtCadastro) {
    this.dtCadastro = dtCadastro;
  }

  public Date getDtAtualizacao() {
    return dtAtualizacao;
  }

  public void setDtAtualizacao(Date dtAtualizacao) {
    this.dtAtualizacao = dtAtualizacao;
  }

  public String getUsuarioAtualizacao() {
    return usuarioAtualizacao;
  }

  public void setUsuarioAtualizacao(String usuarioAtualizacao) {
    this.usuarioAtualizacao = usuarioAtualizacao;
  }

  public boolean isBloqueadoAlteracao() {
    return bloqueadoAlteracao;
  }

  public void setBloqueadoAlteracao(boolean bloqueadoAlteracao) {
    this.bloqueadoAlteracao = bloqueadoAlteracao;
  }

  @Override
  public String toString() {
    return "AquarioBiota [idAquario=" + idAquario + ", idBiota=" + idBiota + ", nome=" + nome
        + ", dtAquisicao=" + dtAquisicao + ", dtNascimento=" + dtNascimento + ", dtObito=" + dtObito
        + ", qtde=" + qtde + ", dtCadastro=" + dtCadastro + ", dtAtualizacao=" + dtAtualizacao
        + ", usuarioAtualizacao=" + usuarioAtualizacao + ", bloqueadoAlteracao="
        + bloqueadoAlteracao + "]";
  }

}
