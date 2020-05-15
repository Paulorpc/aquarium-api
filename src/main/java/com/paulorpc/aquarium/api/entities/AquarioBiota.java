//package com.paulorpc.aquarium.api.entities;
//
//import java.util.Date;
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.FetchType;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.JoinColumn;
//import javax.persistence.ManyToOne;
//import javax.persistence.Table;
//
//@Entity
//@Table(name="AquarioBiota")
//public class AquarioBiota {
//
//  @Id
//  @GeneratedValue(strategy = GenerationType.IDENTITY)
//  @Column(name = "idAquario", nullable = false)
//  private int idAquario;
//  
//  @Column(name = "idBiota", nullable = true)
//  private int idBiota;
//  
//  @Column(name = "nome", nullable = true)
//  private String nome;
//  
//  @Column(name = "dtAquisicao", nullable = true)
//  private Date dtAquisicao;
//  
//  @Column(name = "dtNascimento", nullable = true)
//  private Date dtNascimento;
//  
//  @Column(name = "dtObito", nullable = true)
//  private Date dtObito;
//  
//  @Column(name = "qtde", nullable = true)
//  private int qtde;
//  
//  @Column(name = "dtCadastro", nullable = true)
//  private Date dtCadastro;
//  
//  @Column(name = "dtAtualizacao", nullable = true)
//  private Date dtAtualizacao;
//  
//  @Column(name = "usuarioAtualizacao", nullable = true)
//  private String usuarioAtualizacao;
//  
//  @Column(name = "bloqueadoAlteracao", nullable = true)
//  private boolean bloqueadoAlteracao;
//  
//  @ManyToOne(fetch = FetchType.LAZY)
//  @JoinColumn(name = "idBiota")
//  private Biota biota;
//
//  
//  public int getIdAquario() {
//    return idAquario;
//  }
//
//  public void setIdAquario(int idAquario) {
//    this.idAquario = idAquario;
//  }
//
//  public int getIdBiota() {
//    return idBiota;
//  }
//
//  public void setIdBiota(int idBiota) {
//    this.idBiota = idBiota;
//  }
//
//  public String getNome() {
//    return nome;
//  }
//
//  public void setNome(String nome) {
//    this.nome = nome;
//  }
//
//  public Date getDtAquisicao() {
//    return dtAquisicao;
//  }
//
//  public void setDtAquisicao(Date dtAquisicao) {
//    this.dtAquisicao = dtAquisicao;
//  }
//
//  public Date getDtNascimento() {
//    return dtNascimento;
//  }
//
//  public void setDtNascimento(Date dtNascimento) {
//    this.dtNascimento = dtNascimento;
//  }
//
//  public Date getDtObito() {
//    return dtObito;
//  }
//
//  public void setDtObito(Date dtObito) {
//    this.dtObito = dtObito;
//  }
//
//  public int getQtde() {
//    return qtde;
//  }
//
//  public void setQtde(int qtde) {
//    this.qtde = qtde;
//  }
//
//  public Date getDtCadastro() {
//    return dtCadastro;
//  }
//
//  public void setDtCadastro(Date dtCadastro) {
//    this.dtCadastro = dtCadastro;
//  }
//
//  public Date getDtAtualizacao() {
//    return dtAtualizacao;
//  }
//
//  public void setDtAtualizacao(Date dtAtualizacao) {
//    this.dtAtualizacao = dtAtualizacao;
//  }
//
//  public String getUsuarioAtualizacao() {
//    return usuarioAtualizacao;
//  }
//
//  public void setUsuarioAtualizacao(String usuarioAtualizacao) {
//    this.usuarioAtualizacao = usuarioAtualizacao;
//  }
//
//  public boolean isBloqueadoAlteracao() {
//    return bloqueadoAlteracao;
//  }
//
//  public void setBloqueadoAlteracao(boolean bloqueadoAlteracao) {
//    this.bloqueadoAlteracao = bloqueadoAlteracao;
//  }
//
//  @Override
//  public String toString() {
//    return "AquarioBiota [idAquario=" + idAquario + ", idBiota=" + idBiota + ", nome=" + nome
//        + ", dtAquisicao=" + dtAquisicao + ", dtNascimento=" + dtNascimento + ", dtObito=" + dtObito
//        + ", qtde=" + qtde + ", dtCadastro=" + dtCadastro + ", dtAtualizacao=" + dtAtualizacao
//        + ", usuarioAtualizacao=" + usuarioAtualizacao + ", bloqueadoAlteracao="
//        + bloqueadoAlteracao + "]";
//  }
//
//}
