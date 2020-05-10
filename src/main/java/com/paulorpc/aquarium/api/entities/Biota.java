
package com.paulorpc.aquarium.api.entities;

import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import com.paulorpc.aquarium.api.enums.NivelCuidadoEnum;
import com.paulorpc.aquarium.api.enums.RiscoExtincaoEnum;
import com.paulorpc.aquarium.api.enums.TamanhoBiotaEnum;
import com.paulorpc.aquarium.api.enums.TipoAguaEnum;

@Entity
@Table(name = "Biota")
public class Biota {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "idBiota", nullable = false)
  private int idBiota;

  @Column(name = "nomePopular", nullable = false)
  private String nomePopular;

  @Column(name = "nomeCientifico", nullable = true)
  private String nomeCientifico;

  @Column(name = "nomeCientifico", nullable = true)
  private TipoAguaEnum tipoAgua;

  @Column(name = "nivelCuidado", nullable = true)
  private NivelCuidadoEnum nivelCuidado;

  @Column(name = "volumeMinAquario", nullable = true)
  private int volumeMinAquario;

  @Column(name = "alimentacao", nullable = true)
  private String alimentacao;

  @Column(name = "habitat", nullable = true)
  private String habitat;

  @Column(name = "regiao", nullable = true)
  private String regiao;

  @Column(name = "tamanho", nullable = true)
  private TamanhoBiotaEnum tamanho;

  @Column(name = "riscoExtincao", nullable = true)
  private RiscoExtincaoEnum riscoExtincao;

  @Column(name = "infoAdicional", nullable = true)
  private String infoAdicional;

  @OneToOne
  private Taxonomia taxonomia;

  @ManyToMany(fetch = FetchType.LAZY)
  //@JoinColumn
  private List<String> fotos;

  @Column(name = "avaliacao", nullable = true)
  private Double avaliacao;

  @Column(name = "excluido", nullable = true)
  private boolean excluido;

  @Column(name = "dtCadastro", nullable = false)
  private Date dtCadastro;

  @Column(name = "dtAtualizacao", nullable = true)
  private Date dtAtualizacao;

  @Column(name = "usuarioAtualizacao", nullable = true)
  private String usuarioAtualizacao;

  @Column(name = "bloqueadoAlteracao", nullable = true)
  private boolean bloqueadoAlteracao;
  
  //@oneToMany(fetch = FetchType.LAZY)
  //@JoinColumn
  //private List<?> aquarioBiota;

  public int getIdBiota() {
    return idBiota;
  }

  public void setIdBiota(int idBiota) {
    this.idBiota = idBiota;
  }

  public String getNomePopular() {
    return nomePopular;
  }

  public void setNomePopular(String nomePopular) {
    this.nomePopular = nomePopular;
  }

  public String getNomeCientifico() {
    return nomeCientifico;
  }

  public void setNomeCientifico(String nomeCientifico) {
    this.nomeCientifico = nomeCientifico;
  }

  public TipoAguaEnum getTipoAgua() {
    return tipoAgua;
  }

  public void setTipoAgua(TipoAguaEnum tipoAgua) {
    this.tipoAgua = tipoAgua;
  }

  public NivelCuidadoEnum getNivelCuidado() {
    return nivelCuidado;
  }

  public void setNivelCuidado(NivelCuidadoEnum nivelCuidado) {
    this.nivelCuidado = nivelCuidado;
  }

  public int getVolumeMinAquario() {
    return volumeMinAquario;
  }

  public void setVolumeMinAquario(int volumeMinAquario) {
    this.volumeMinAquario = volumeMinAquario;
  }

  public String getAlimentacao() {
    return alimentacao;
  }

  public void setAlimentacao(String alimentacao) {
    this.alimentacao = alimentacao;
  }

  public String getHabitat() {
    return habitat;
  }

  public void setHabitat(String habitat) {
    this.habitat = habitat;
  }

  public String getRegiao() {
    return regiao;
  }

  public void setRegiao(String regiao) {
    this.regiao = regiao;
  }

  public TamanhoBiotaEnum getTamanho() {
    return tamanho;
  }

  public void setTamanho(TamanhoBiotaEnum tamanho) {
    this.tamanho = tamanho;
  }

  public RiscoExtincaoEnum getRiscoExtincao() {
    return riscoExtincao;
  }

  public void setRiscoExtincao(RiscoExtincaoEnum riscoExtincao) {
    this.riscoExtincao = riscoExtincao;
  }

  public String getInfoAdicional() {
    return infoAdicional;
  }

  public void setInfoAdicional(String infoAdicional) {
    this.infoAdicional = infoAdicional;
  }

  public Taxonomia getTaxonomia() {
    return taxonomia;
  }

  public void setTaxonomia(Taxonomia taxonomia) {
    this.taxonomia = taxonomia;
  }

  public List<String> getFotos() {
    return fotos;
  }

  public void setFotos(List<String> fotos) {
    this.fotos = fotos;
  }

  public Double getAvaliacao() {
    return avaliacao;
  }

  public void setAvaliacao(Double avaliacao) {
    this.avaliacao = avaliacao;
  }

  public boolean isExcluido() {
    return excluido;
  }

  public void setExcluido(boolean excluido) {
    this.excluido = excluido;
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

  @PreUpdate
  public void preUpdate() {
    dtAtualizacao = new Date();
    // usuarioAtualizacao = getSession().getUser().nomeUsuario();
  }

  @PrePersist
  public void prePersist() {
    Date hojeHora = new Date();
    dtCadastro = hojeHora;
    dtAtualizacao = hojeHora;
    // usuarioAtualizacao = getSession().getUser().nomeUsuario();
  }


  @Override
  public String toString() {
    return "Biota [idBiota=" + idBiota + ", nomePopular=" + nomePopular + ", nomeCientifico="
        + nomeCientifico + ", tipoAgua=" + tipoAgua + ", nivelCuidado=" + nivelCuidado
        + ", volumeMinAquario=" + volumeMinAquario + ", alimentacao=" + alimentacao + ", habitat="
        + habitat + ", regiao=" + regiao + ", tamanho=" + tamanho + ", riscoExtincao="
        + riscoExtincao + ", infoAdicional=" + infoAdicional + ", taxonomia=" + taxonomia
        + ", fotos=" + fotos + ", avaliacao=" + avaliacao + ", excluido=" + excluido
        + ", dtCadastro=" + dtCadastro + ", dtAtualizacao=" + dtAtualizacao
        + ", usuarioAtualizacao=" + usuarioAtualizacao + ", bloqueadoAlteracao="
        + bloqueadoAlteracao + "]";
  }

}
