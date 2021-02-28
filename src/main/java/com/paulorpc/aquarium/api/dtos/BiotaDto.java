package com.paulorpc.aquarium.api.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.paulorpc.aquarium.api.enums.NivelCuidadoEnum;
import com.paulorpc.aquarium.api.enums.RiscoExtincaoEnum;
import com.paulorpc.aquarium.api.enums.TamanhoBiotaEnum;
import com.paulorpc.aquarium.api.enums.TipoAguaEnum;
import java.time.LocalDateTime;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BiotaDto {

  public interface Post {}

  public interface Put {}

  public interface Patch {}

  @Positive(
      message = "Campo 'id' deve ser maior que zero.",
      groups = {Put.class, Patch.class})
  @NotNull(
      message = "Campo 'id' é obrigatório.",
      groups = {Put.class, Patch.class})
  private Long id;

  @NotEmpty(
      message = "Campo 'nomePopular' é obrigatório.",
      groups = {Post.class, Put.class})
  private String nomePopular;

  private String nomeCientifico;

  @NotNull(
      message = "Campo 'tipoAgua' é obrigatório.",
      groups = {Post.class, Put.class})
  private TipoAguaEnum tipoAgua;

  private NivelCuidadoEnum nivelCuidado;
  private Boolean reefSafe;
  private Double volumeMinAquario;
  private String alimentacao;
  private String habitat;
  private String regiao;
  private TamanhoBiotaEnum tamanho;
  private RiscoExtincaoEnum riscoExtincao;
  private String infoAdicional;
  private TaxonomiaDto taxonomia;
  private Double avaliacao;

  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  @Null(
      message = "Campo 'dtCadastro' deve ser nulo.",
      groups = {Post.class, Put.class})
  private LocalDateTime dtCadastro;

  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  @Null(
      message = "Campo 'dtAtualizacao' deve ser nulo.",
      groups = {Post.class, Put.class})
  private LocalDateTime dtAtualizacao;

  private String usuarioCadastro;

  private String usuarioAtualizacao;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
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

  public TaxonomiaDto getTaxonomia() {
    return taxonomia;
  }

  public void setTaxonomia(TaxonomiaDto taxonomia) {
    this.taxonomia = taxonomia;
  }

  public Double getAvaliacao() {
    return avaliacao;
  }

  public void setAvaliacao(Double avaliacao) {
    this.avaliacao = avaliacao;
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
}
