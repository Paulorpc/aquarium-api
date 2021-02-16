package com.paulorpc.aquarium.api.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.paulorpc.aquarium.api.enums.NivelCuidadoEnum;
import com.paulorpc.aquarium.api.enums.RiscoExtincaoEnum;
import com.paulorpc.aquarium.api.enums.TamanhoBiotaEnum;
import com.paulorpc.aquarium.api.enums.TipoAguaEnum;
import java.time.LocalDateTime;
import java.util.Optional;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

public class BiotaDto {

  private Optional<Long> id = Optional.empty();
  private Optional<String> nomePopular = Optional.empty();
  private Optional<String> nomeCientifico = Optional.empty();
  private Optional<TipoAguaEnum> tipoAgua = Optional.empty();
  private Optional<NivelCuidadoEnum> nivelCuidado = Optional.empty();
  private Optional<Boolean> reefSafe = Optional.empty();
  private Optional<Double> volumeMinAquario = Optional.empty();
  private Optional<String> alimentacao = Optional.empty();
  private Optional<String> habitat = Optional.empty();
  private Optional<String> regiao = Optional.empty();
  private Optional<TamanhoBiotaEnum> tamanho = Optional.empty();
  private Optional<RiscoExtincaoEnum> riscoExtincao = Optional.empty();
  private Optional<String> infoAdicional = Optional.empty();
  private Optional<TaxonomiaDto> taxonomiaDto = Optional.empty();
  private Optional<Double> avaliacao = Optional.empty();
  private LocalDateTime dtCadastro;
  private LocalDateTime dtAtualizacao;
  private String usuarioCadastro;
  private String usuarioAtualizacao;

  public interface Cadastrar {}

  public interface Alterar {}

  public Optional<
          @NotNull(
              message = "Campo 'id' é obrigatório.",
              groups = {Alterar.class})
          Long>
      getId() {
    return id;
  }

  public void setId(Optional<Long> id) {
    this.id = id;
  }

  public Optional<
          @NotEmpty(
              message = "Campo 'nomePopular' é obrigatório.",
              groups = {Cadastrar.class, Alterar.class})
          String>
      getNomePopular() {
    return nomePopular;
  }

  public void setNomePopular(Optional<String> nomePopular) {
    this.nomePopular = nomePopular;
  }

  public Optional<String> getNomeCientifico() {
    return nomeCientifico;
  }

  public void setNomeCientifico(Optional<String> nomeCientifico) {
    this.nomeCientifico = nomeCientifico;
  }

  public Optional<
          @NotNull(
              message = "Campo 'tipoAgua' é obrigatório.",
              groups = {Cadastrar.class, Alterar.class})
          TipoAguaEnum>
      getTipoAgua() {
    return tipoAgua;
  }

  public void setTipoAgua(Optional<TipoAguaEnum> tipoAgua) {
    this.tipoAgua = tipoAgua;
  }

  public Optional<NivelCuidadoEnum> getNivelCuidado() {
    return nivelCuidado;
  }

  public void setNivelCuidado(Optional<NivelCuidadoEnum> nivelCuidado) {
    this.nivelCuidado = nivelCuidado;
  }

  public Optional<Boolean> getReefSafe() {
    return reefSafe;
  }

  public void setReefSafe(Optional<Boolean> reefSafe) {
    this.reefSafe = reefSafe;
  }

  public Optional<Double> getVolumeMinAquario() {
    return volumeMinAquario;
  }

  public void setVolumeMinAquario(Optional<Double> volumeMinAquario) {
    this.volumeMinAquario = volumeMinAquario;
  }

  public Optional<String> getAlimentacao() {
    return alimentacao;
  }

  public void setAlimentacao(Optional<String> alimentacao) {
    this.alimentacao = alimentacao;
  }

  public Optional<String> getHabitat() {
    return habitat;
  }

  public void setHabitat(Optional<String> habitat) {
    this.habitat = habitat;
  }

  public Optional<String> getRegiao() {
    return regiao;
  }

  public void setRegiao(Optional<String> regiao) {
    this.regiao = regiao;
  }

  public Optional<TamanhoBiotaEnum> getTamanho() {
    return tamanho;
  }

  public void setTamanho(Optional<TamanhoBiotaEnum> tamanho) {
    this.tamanho = tamanho;
  }

  public Optional<RiscoExtincaoEnum> getRiscoExtincao() {
    return riscoExtincao;
  }

  public void setRiscoExtincao(Optional<RiscoExtincaoEnum> riscoExtincao) {
    this.riscoExtincao = riscoExtincao;
  }

  public Optional<String> getInfoAdicional() {
    return infoAdicional;
  }

  public void setInfoAdicional(Optional<String> infoAdicional) {
    this.infoAdicional = infoAdicional;
  }

  public Optional<TaxonomiaDto> getTaxonomia() {
    return taxonomiaDto;
  }

  public void setTaxonomia(Optional<TaxonomiaDto> taxonomia) {
    this.taxonomiaDto = taxonomia;
  }

  public Optional<Double> getAvaliacao() {
    return avaliacao;
  }

  public void setAvaliacao(Optional<Double> avaliacao) {
    this.avaliacao = avaliacao;
  }

  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  @Null(
      message = "Campo 'dtCadastro' deve ser nulo.",
      groups = {Cadastrar.class, Alterar.class})
  public LocalDateTime getDtCadastro() {
    return dtCadastro;
  }

  public void setDtCadastro(LocalDateTime dtCadastro) {
    this.dtCadastro = dtCadastro;
  }

  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  @Null(
      message = "Campo 'dtAtualizacao' deve ser nulo.",
      groups = {Cadastrar.class, Alterar.class})
  public LocalDateTime getDtAtualizacao() {
    return dtAtualizacao;
  }

  public void setDtAtualizacao(LocalDateTime dtAtualizacao) {
    this.dtAtualizacao = dtAtualizacao;
  }

  @Null(
      message = "Campo 'usuarioCadastro' deve ser nulo.",
      groups = {Cadastrar.class, Alterar.class})
  public String getUsuarioCadastro() {
    return usuarioCadastro;
  }

  public void setUsuarioCadastro(String usuarioCadastro) {
    this.usuarioCadastro = usuarioCadastro;
  }

  @Null(
      message = "Campo 'usuarioAtualizacao' deve ser nulo.",
      groups = {Cadastrar.class, Alterar.class})
  public String getUsuarioAtualizacao() {
    return usuarioAtualizacao;
  }

  public void setUsuarioAtualizacao(String usuarioAtualizacao) {
    this.usuarioAtualizacao = usuarioAtualizacao;
  }

  @Override
  public String toString() {
    return "BiotaDto [id="
        + id
        + ", nomePopular="
        + nomePopular
        + ", nomeCientifico="
        + nomeCientifico
        + ", tipoAgua="
        + tipoAgua
        + ", nivelCuidado="
        + nivelCuidado
        + ", reefSafe="
        + reefSafe
        + ", volumeMinAquario="
        + volumeMinAquario
        + ", alimentacao="
        + alimentacao
        + ", habitat="
        + habitat
        + ", regiao="
        + regiao
        + ", tamanho="
        + tamanho
        + ", riscoExtincao="
        + riscoExtincao
        + ", infoAdicional="
        + infoAdicional
        + ", taxonomiaDto="
        + taxonomiaDto
        + ", avaliacao="
        + avaliacao
        + ", dtCadastro="
        + dtCadastro
        + ", dtAtualizacao="
        + dtAtualizacao
        + ", usuarioCadastro="
        + usuarioCadastro
        + ", usuarioAtualizacao="
        + usuarioAtualizacao
        + "]";
  }
}
