
package com.paulorpc.aquarium.api.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.Optional;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import org.hibernate.annotations.ColumnDefault;
import com.paulorpc.aquarium.api.enums.NivelCuidadoEnum;
import com.paulorpc.aquarium.api.enums.RiscoExtincaoEnum;
import com.paulorpc.aquarium.api.enums.TamanhoBiotaEnum;
import com.paulorpc.aquarium.api.enums.TipoAguaEnum;

@Entity
@Table(name = "Biota")
public class Biota implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "idBiota", nullable = false)
  private int id;

  @Column(name = "nomePopular", nullable = false)
  private String nomePopular;

  @Column(name = "nomeCientifico", nullable = true)
  private String nomeCientifico;

  @Enumerated(EnumType.STRING)
  @Column(name = "tipoAgua", nullable = true)
  private TipoAguaEnum tipoAgua;

  @Enumerated(EnumType.STRING)
  @Column(name = "nivelCuidado", nullable = true)
  private NivelCuidadoEnum nivelCuidado;

  @Column(name = "reefSafe", nullable = true)
  private Boolean reefSafe;

  @Column(name = "volumeMinAquario", nullable = true)
  private Double volumeMinAquario;

  @Column(name = "alimentacao", nullable = true)
  private String alimentacao;

  @Column(name = "habitat", nullable = true)
  private String habitat;

  @Column(name = "regiao", nullable = true)
  private String regiao;

  @Enumerated(EnumType.STRING)
  @Column(name = "tamanho", nullable = true)
  private TamanhoBiotaEnum tamanho;

  @Enumerated(EnumType.STRING)
  @Column(name = "riscoExtincao", nullable = true)
  private RiscoExtincaoEnum riscoExtincao;

  @Column(name = "infoAdicional", nullable = true)
  private String infoAdicional;

  @OneToOne(mappedBy = "biota", cascade = CascadeType.ALL)
  private Taxonomia taxonomia;

  // @ManyToMany(fetch = FetchType.LAZY)
  // @JoinColumn
  // private List<String> fotos;

  @Column(name = "avaliacao", nullable = true)
  private Double avaliacao;

  @Column(name = "deletado", nullable = false)
  @ColumnDefault(value = "false")
  private boolean deletado;

  @Column(name = "dtCadastro", nullable = false)
  private Date dtCadastro;

  @Column(name = "dtAtualizacao", nullable = false)
  private Date dtAtualizacao;

  @Column(name = "usuarioCadastro", nullable = false)
  private String usuarioCadastro;

  @Column(name = "usuarioAtualizacao", nullable = false)
  private String usuarioAtualizacao;

  // @oneToMany(fetch = FetchType.LAZY)
  // @JoinColumn
  // private List<?> aquarioBiota;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
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

  public Boolean isReefSafe() {
    return reefSafe;
  }

  public void setReefSafe(Boolean reefSafe) {
    this.reefSafe = reefSafe;
  }

  public Double getVolumeMinAquario() {
    return volumeMinAquario;
  }

  public void setVolumeMinAquario(Double volumeMinAquario) {
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

    if (taxonomia != null) {

      Optional<String> genero = Optional.ofNullable(taxonomia.getGenero());
      Optional<String> especie = Optional.ofNullable(taxonomia.getEspecie());
      if (!nomeCientifico.isEmpty()) {
        String[] nomes = nomeCientifico.split(" ");
        if (nomes.length == 2) {
          taxonomia.setGenero(genero.orElse(nomes[0].trim()));
          taxonomia.setEspecie(especie.orElse(nomes[1].trim()));
        }
      } else if (genero.isPresent() && especie.isPresent()) {
        nomeCientifico = genero.get() + " " + especie.get();
      }
    }

    this.taxonomia = taxonomia;
  }

  // public List<String> getFotos() {
  // return fotos;
  // }

  // public void setFotos(List<String> fotos) {
  // this.fotos = fotos;
  // }

  public Double getAvaliacao() {
    return avaliacao;
  }

  public void setAvaliacao(Double avaliacao) {
    this.avaliacao = avaliacao;
  }

  public boolean isDeletado() {
    return deletado;
  }

  public void setDeletado(boolean deletado) {
    this.deletado = deletado;
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

  public String getUsuarioCadastro() {
    return usuarioCadastro;
  }

  public void setUsuarioCadastro(String usuarioCadastro) {
    this.usuarioCadastro = usuarioCadastro;
  }

  public String getUsuarioAtualizacao() {
    return usuarioAtualizacao;
  }

  public void setUsuarioAtualizacao(String usuarioAtualizacao) {
    this.usuarioAtualizacao = usuarioAtualizacao;
  }

  @PreUpdate
  public void preUpdate() {
    dtAtualizacao = new Date();
    usuarioAtualizacao = "USUARIO_SESSAO";
  }

  @PrePersist
  public void prePersist() {
    Date hojeHora = new Date();
    dtCadastro = hojeHora;
    dtAtualizacao = hojeHora;
    usuarioCadastro = "USUARIO_SESSAO";
    usuarioAtualizacao = "USUARIO_SESSAO";
  }

  @Override
  public String toString() {
    return "Biota [id=" + id + ", nomePopular=" + nomePopular + ", nomeCientifico=" + nomeCientifico
        + ", tipoAgua=" + tipoAgua + ", nivelCuidado=" + nivelCuidado + ", reefSafe=" + reefSafe
        + ", volumeMinAquario=" + volumeMinAquario + ", alimentacao=" + alimentacao + ", habitat="
        + habitat + ", regiao=" + regiao + ", tamanho=" + tamanho + ", riscoExtincao="
        + riscoExtincao + ", infoAdicional=" + infoAdicional + ", taxonomia=" + taxonomia
        + ", avaliacao=" + avaliacao + ", deletado=" + deletado + ", dtCadastro=" + dtCadastro
        + ", dtAtualizacao=" + dtAtualizacao + ", usuarioCadastro=" + usuarioCadastro
        + ", usuarioAtualizacao=" + usuarioAtualizacao + "]";
  }

}
