package com.paulorpc.aquarium.api.entities;

import java.time.LocalDateTime;

public abstract class Organismo {
		
	private int idOrganismo;
	private String nome;
	private String nomeCientifico;
	private String nomePopular;	
	//private Taxonomia taxonomia;
	private Double avaliacao;
	//private foto;	
	private String tipoAgua;
	private String nivelCuidado;
	private String disposicao;
	private int volumeMinAquario;
	private String alimentacao;
	private String regiao;
	private String tamanho;
	private String observaocao;
	private LocalDateTime dtCadastro;
	private LocalDateTime dtAtualizacao;

	public Organismo() {}

	public int getIdOrganismo() {
		return idOrganismo;
	}

	public void setIdOrganismo(int idOrganismo) {
		this.idOrganismo = idOrganismo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getNomeCientifico() {
		return nomeCientifico;
	}

	public void setNomeCientifico(String nomeCientifico) {
		this.nomeCientifico = nomeCientifico;
	}

	public String getNomePopular() {
		return nomePopular;
	}

	public void setNomePopular(String nomePopular) {
		this.nomePopular = nomePopular;
	}

	public Double getAvaliacao() {
		return avaliacao;
	}

	public void setAvaliacao(Double avaliacao) {
		this.avaliacao = avaliacao;
	}

	public String getTipoAgua() {
		return tipoAgua;
	}

	public void setTipoAgua(String tipoAgua) {
		this.tipoAgua = tipoAgua;
	}

	public String getNivelCuidado() {
		return nivelCuidado;
	}

	public void setNivelCuidado(String nivelCuidado) {
		this.nivelCuidado = nivelCuidado;
	}

	public String getDisposicao() {
		return disposicao;
	}

	public void setDisposicao(String disposicao) {
		this.disposicao = disposicao;
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

	public String getRegiao() {
		return regiao;
	}

	public void setRegiao(String regiao) {
		this.regiao = regiao;
	}

	public String getTamanho() {
		return tamanho;
	}

	public void setTamanho(String tamanho) {
		this.tamanho = tamanho;
	}

	public String getObservaocao() {
		return observaocao;
	}

	public void setObservaocao(String observaocao) {
		this.observaocao = observaocao;
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
