package com.paulorpc.aquarium.api.dtos;

import java.util.Date;
import java.util.Optional;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.paulorpc.aquarium.api.enums.NivelCuidadoEnum;
import com.paulorpc.aquarium.api.enums.TipoAguaEnum;

public class BiotaDto {
  
  Optional<Integer> id = Optional.empty();  
  Optional<String> nomePopular = Optional.empty();
  Optional<String> nomeCientifico = Optional.empty();
  Optional<TipoAguaEnum> tipoAgua = Optional.empty();    
  Optional<NivelCuidadoEnum> nivelCuidado = Optional.empty();    
  Optional<Boolean> bloqueadoAlteracao = Optional.empty();
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
  
  public Optional<String> getNomePopular() {
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

  public Optional<TipoAguaEnum> getTipoAgua() {
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

  public Optional<Boolean> getBloqueadoAlteracao() {
    return bloqueadoAlteracao;
  }

  public void setBloqueadoAlteracao(Optional<Boolean> bloqueadoAlteracao) {
    this.bloqueadoAlteracao = bloqueadoAlteracao;
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
