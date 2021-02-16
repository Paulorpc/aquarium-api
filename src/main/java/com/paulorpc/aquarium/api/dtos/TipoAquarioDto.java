package com.paulorpc.aquarium.api.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.paulorpc.aquarium.api.enums.TipoAguaEnum;
import java.time.LocalDateTime;
import java.util.Optional;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

public class TipoAquarioDto {

  private Optional<Long> id = Optional.empty();
  private Optional<String> tipo = Optional.empty();
  private Optional<TipoAguaEnum> tipoAgua = Optional.empty();
  private Optional<Boolean> status = Optional.empty();
  private Optional<String> descricao = Optional.empty();
  private LocalDateTime dtCadastro;
  private LocalDateTime dtAtualizacao;

  public interface Cadastrar {}

  public interface Alterar {}

  @NotNull(
      message = "Campo 'id' é obrigatório.",
      groups = {Alterar.class})
  public Optional<Long> getId() {
    return id;
  }

  public void setId(Optional<Long> idTipoAquario) {
    this.id = idTipoAquario;
  }

  public Optional<
          @NotEmpty(
              message = "Campo 'tipo' é obrigatório.",
              groups = {Cadastrar.class})
          String>
      getTipo() {
    return tipo;
  }

  public void setTipo(Optional<String> tipo) {
    this.tipo = tipo;
  }

  public Optional<
          @NotNull(
              message = "Campo 'tipoAgua' é obrigatório.",
              groups = {Cadastrar.class})
          TipoAguaEnum>
      getTipoAgua() {
    return tipoAgua;
  }

  public void setTipoAgua(Optional<TipoAguaEnum> tipoAgua) {
    this.tipoAgua = tipoAgua;
  }

  public Optional<
          @NotNull(
              message = "Campo 'status' é obrigatório.",
              groups = {Cadastrar.class})
          Boolean>
      getStatus() {
    return status;
  }

  public void setStatus(Optional<Boolean> status) {
    this.status = status;
  }

  public Optional<
          @NotNull(
              message = "Campo 'descricao' é obrigatório.",
              groups = {Cadastrar.class})
          String>
      getDescricao() {
    return descricao;
  }

  public void setDescricao(Optional<String> descricao) {
    this.descricao = descricao;
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
}
