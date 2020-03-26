package com.paulorpc.aquarium.api.entities;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Aquario {

	private int idAquario;
	private String nome;
	private LocalDate dtAquisicao;
	private LocalDateTime dtInicio;
	private LocalDateTime dtFinal;
	private String tipoAgua;
	private int tamanho;
	private int volume;
	private String iluminacao;
	private String filtragem;
	private String sistemaCO2;
	private String dosagem;
	private String substrato;
	// private parametros;
	private int status;
	// private foto;
	private String observacao;
	private int idTipoAquario;
	private LocalDateTime dtCadastro;
	private LocalDateTime dtAtualizacao;
	
	
	public Aquario() {
		
	}
	
	
	public int getIdAquario() {
		return idAquario;
	}

	public void setIdAquario(int idAquario) {
		this.idAquario = idAquario;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public LocalDate getDtAquisicao() {
		return dtAquisicao;
	}

	public void setDtAquisicao(LocalDate dtAquisicao) {
		this.dtAquisicao = dtAquisicao;
	}

	public LocalDateTime getDtInicio() {
		return dtInicio;
	}

	public void setDtInicio(LocalDateTime dtInicio) {
		this.dtInicio = dtInicio;
	}

	public LocalDateTime getDtFinal() {
		return dtFinal;
	}

	public void setDtFinal(LocalDateTime dtFinal) {
		this.dtFinal = dtFinal;
	}

	public String getTipoAgua() {
		return tipoAgua;
	}

	public void setTipoAgua(String tipoAgua) {
		this.tipoAgua = tipoAgua;
	}

	public int getTamanho() {
		return tamanho;
	}

	public void setTamanho(int tamanho) {
		this.tamanho = tamanho;
	}

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

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public int getIdTipoAquario() {
		return idTipoAquario;
	}

	public void setIdTipoAquario(int idTipoAquario) {
		this.idTipoAquario = idTipoAquario;
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

}
