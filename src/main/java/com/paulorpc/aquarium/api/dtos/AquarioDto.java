package com.paulorpc.aquarium.api.dtos;

import java.util.Date;

import javax.validation.constraints.*;


public class AquarioDto {
	
	private int idAquario;
	private String nome;
	private Date dtInicio;
	private Date dtFinal;
	private String tipoAgua;
	private String tamanho;
	private int volume;
	private String iluminacao;
	private String filtragem;
	private String sistemaCO2;
	private String dosagem;
	private String substrato;
	private String observacao;
	private boolean status;
	private int idTipoAquario;
	
	
	public int getIdAquario() {
		return idAquario;
	}
	public void setIdAquario(int idAquario) {
		this.idAquario = idAquario;
	}
	
	@NotEmpty(message="Campo 'nome' não pode ser nulo.")
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	@PastOrPresent(message="Campo 'dtInicio' não pode ser posterior a data atual.")
	@NotNull(message="Campo 'dtInicio' não pode ser nulo.")	
	public Date getDtInicio() {
		return dtInicio;
	}
	public void setDtInicio(Date dtInicio) {
		this.dtInicio = dtInicio;
	}
	public Date getDtFinal() {
		return dtFinal;
	}
	public void setDtFinal(Date dtFinal) {
		this.dtFinal = dtFinal;
	}
	public String getTipoAgua() {
		return tipoAgua;
	}
	public void setTipoAgua(String tipoAgua) {
		this.tipoAgua = tipoAgua;
	}
	
	public String getTamanho() {
		return tamanho;
	}
	public void setTamanho(String tamanho) {
		this.tamanho = tamanho;
	}
	
	@Positive(message="Campo 'volume' deve ser maior que zero.")	
	public int getVolume() {
		return volume;
	}
	public void setVolume(int volume) {
		this.volume = volume;
	}
	public String getIluminacao() {
		return iluminacao;
	}
	public void setIluminacao(String iluminacao) {
		this.iluminacao = iluminacao;
	}
	public String getFiltragem() {
		return filtragem;
	}
	public void setFiltragem(String filtragem) {
		this.filtragem = filtragem;
	}
	public String getSistemaCO2() {
		return sistemaCO2;
	}
	public void setSistemaCO2(String sistemaCO2) {
		this.sistemaCO2 = sistemaCO2;
	}
	public String getDosagem() {
		return dosagem;
	}
	public void setDosagem(String dosagem) {
		this.dosagem = dosagem;
	}
	public String getSubstrato() {
		return substrato;
	}
	public void setSubstrato(String substrato) {
		this.substrato = substrato;
	}
	public String getObservacao() {
		return observacao;
	}
	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}
	
	@NotNull(message="Campo 'status' não pode ser nulo.")
	public boolean getStatus() {
		return status;
	}
	
	public void setStatus(boolean status) {
		this.status = status;
	}
	public int getIdTipoAquario() {
		return idTipoAquario;
	}
	public void setIdTipoAquario(int idTipoAquario) {
		this.idTipoAquario = idTipoAquario;
	}
}
