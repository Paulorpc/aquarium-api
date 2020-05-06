package com.paulorpc.aquarium.api.dtos;

import java.util.Date;
import java.util.Optional;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

import com.fasterxml.jackson.annotation.JsonFormat;

import enums.TipoAguaEnum;

public class TipoAquarioDto {

  Optional<Integer> id = Optional.empty();
  Optional<String> tipo = Optional.empty();
  Optional<TipoAguaEnum> tipoAgua = Optional.empty();
  Optional<Boolean> status = Optional.empty();
  Optional<String> descricao = Optional.empty();
  Date dtCadastro;
  Date dtAtualizacao;

  public interface Cadastrar {
  }

  public interface Alterar {
  }

  @NotNull(message = "Campo 'id' é obrigatório.", groups = {Alterar.class})
  public Optional<Integer> getId() {
    return id;
  }

  public void setId(Optional<Integer> idTipoAquario) {
    this.id = idTipoAquario;
  }

  public Optional<@NotEmpty(message = "Campo 'tipo' é obrigatório.",
      groups = {Cadastrar.class}) String> getTipo() {
    return tipo;
  }

  public void setTipo(Optional<String> tipo) {
    this.tipo = tipo;
  }

  public Optional<@NotNull(message = "Campo 'tipoAgua' é obrigatório.",
      groups = {Cadastrar.class}) TipoAguaEnum> getTipoAgua() {
    return tipoAgua;
  }

  public void setTipoAgua(Optional<TipoAguaEnum> tipoAgua) {
    this.tipoAgua = tipoAgua;
  }

  public Optional<@NotNull(message = "Campo 'status' é obrigatório.",
      groups = {Cadastrar.class}) Boolean> isStatus() {
    return status;
  }

  public void setStatus(Optional<Boolean> status) {
    this.status = status;
  }

  public Optional<@NotNull(message = "Campo 'descricao' é obrigatório.",
      groups = {Cadastrar.class}) String> getDescricao() {
    return descricao;
  }

  public void setDescricao(Optional<String> descricao) {
    this.descricao = descricao;
  }

  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  @Null(message = "Campo 'dtCadastro' deve ser nulo.", groups = {Cadastrar.class, Alterar.class})
  public Date getDtCadastro() {
    return dtCadastro;
  }

  public void setDtCadastro(Date dtCadastro) {
    this.dtCadastro = dtCadastro;
  }

  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  @Null(message = "Campo 'dtCadastro' deve ser nulo.", groups = {Cadastrar.class, Alterar.class})
  public Date getDtAtualizacao() {
    return dtAtualizacao;
  }

  public void setDtAtualizacao(Date dtAtualizacao) {
    this.dtAtualizacao = dtAtualizacao;
  }

}
