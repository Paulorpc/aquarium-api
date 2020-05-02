package com.paulorpc.aquarium.api.dtos;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Optional;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Positive;


public class AquarioDto {
	
	private Optional<Integer> id = Optional.empty();
	private Optional<String> nome = Optional.empty();
	private Optional<Date> dtInicio = Optional.empty();
	private Optional<Date> dtFinal = Optional.empty();
	private Optional<String> tipoAgua = Optional.empty();
	private Optional<String> tamanho = Optional.empty();
	private Optional<Integer> volume = Optional.empty();
	private Optional<String> iluminacao = Optional.empty();
	private Optional<String> filtragem = Optional.empty();
	private Optional<String> sistemaCO2 = Optional.empty();
	private Optional<String> dosagem = Optional.empty();
	private Optional<String> substrato = Optional.empty();
	private Optional<String> foto = Optional.empty();
	private Optional<String> observacao = Optional.empty();
	private Optional<Boolean> status = Optional.empty();
	private Optional<Integer> idTipoAquario = Optional.empty();
	private Optional<LocalDateTime> dtCadastro = Optional.empty();
	private Optional<LocalDateTime> dtAtualizacao = Optional.empty();
	
	public interface Cadastrar {}
	public interface Alterar {}
	
	public Optional<@NotNull(message="Campo 'id' obrigatório para método de alteração.", groups= {Alterar.class}) Integer> getId() {
		return id;
	}
	public void setId(Optional<Integer> id) {
		this.id = id;
	}
	public Optional<@NotEmpty(message="Campo 'nome' é obrigatório.", groups= {Cadastrar.class}) String> getNome() {
		return nome;
	}
	public void setNome(Optional<String> nome) {
		this.nome = nome;
	}
	public Optional<@PastOrPresent(message="Campo 'dtInicio' não pode ser posterior a data atual.", groups= {Cadastrar.class})
					@NotNull(message="Campo 'dtInicio' não pode ser nulo.", groups= {Cadastrar.class}) Date> getDtInicio() {
		return dtInicio;
	}
	public void setDtInicio(Optional<Date> dtInicio) {
		this.dtInicio = dtInicio;
	}
	public Optional<Date> getDtFinal() {
		return dtFinal;
	}
	public void setDtFinal(Optional<Date> dtFinal) {
		this.dtFinal = dtFinal;
	}
	public Optional<String> getTipoAgua() {
		return tipoAgua;
	}
	public void setTipoAgua(Optional<String> tipoAgua) {
		this.tipoAgua = tipoAgua;
	}
	public Optional<String> getTamanho() {
		return tamanho;
	}
	public void setTamanho(Optional<String> tamanho) {
		this.tamanho = tamanho;
	}
	public Optional<@Positive(message="Campo 'volume' deve ser maior que zero.", groups= {Cadastrar.class}) Integer> getVolume() {
		return volume;
	}
	public void setVolume(Optional<Integer> volume) {
		this.volume = volume;
	}
	public Optional<String> getIluminacao() {
		return iluminacao;
	}
	public void setIluminacao(Optional<String> iluminacao) {
		this.iluminacao = iluminacao;
	}
	public Optional<String> getFiltragem() {
		return filtragem;
	}
	public void setFiltragem(Optional<String> filtragem) {
		this.filtragem = filtragem;
	}
	public Optional<String> getSistemaCO2() {
		return sistemaCO2;
	}
	public void setSistemaCO2(Optional<String> sistemaCO2) {
		this.sistemaCO2 = sistemaCO2;
	}
	public Optional<String> getDosagem() {
		return dosagem;
	}
	public void setDosagem(Optional<String> dosagem) {
		this.dosagem = dosagem;
	}
	public Optional<String> getSubstrato() {
		return substrato;
	}
	public void setSubstrato(Optional<String> substrato) {
		this.substrato = substrato;
	}
	public Optional<String> getFoto() {
		return foto;
	}
	public void setFoto(Optional<String> foto) {
		this.foto = foto;
	}
	public Optional<String> getObservacao() {
		return observacao;
	}
	public void setObservacao(Optional<String> observacao) {
		this.observacao = observacao;
	}
	public Optional<@NotNull(message="Campo 'status' não pode ser nulo.", groups= {Cadastrar.class}) Boolean> getStatus() {
		return status;
	}
	public void setStatus(Optional<Boolean> status) {
		this.status = status;
	}
	public Optional<Integer> getIdTipoAquario() {
		return idTipoAquario;
	}
	public void setIdTipoAquario(Optional<Integer> idTipoAquario) {
		this.idTipoAquario = idTipoAquario;
	}
	public Optional<LocalDateTime> getDtCadastro() {
		return dtCadastro;
	}
	public void setDtCadastro(Optional<LocalDateTime> dtCadastro) {
		this.dtCadastro = dtCadastro;
	}
	public Optional<LocalDateTime> getDtAtualizacao() {
		return dtAtualizacao;
	}
	public void setDtAtualizacao(Optional<LocalDateTime> dtAtualizacao) {
		this.dtAtualizacao = dtAtualizacao;
	}
	
}
