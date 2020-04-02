package com.paulorpc.aquarium.api.entities;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class Biota {
	
	 private int idBiota;
	 private int idAquario;
	 //private Organismo organismo;
	 private LocalDate dtAquisicao;
	 private LocalDate dtNascimento;
	 //private LocalDate dtObito;	 	 
	 //private tipoBiota;
	 private String genero;
	 private BigDecimal vlrUnitario;
	 private int qtde;
	 //private foto;
	 private Double avaliacao;
	 private String observacao;	 		
	 private LocalDateTime dtCadastro;
	 private LocalDateTime dtAtualizacao;

	public Biota() {}

	public int getIdBiota() {
		return idBiota;
	}

	public void setIdBiota(int idBiota) {
		this.idBiota = idBiota;
	}

	public int getIdAquario() {
		return idAquario;
	}

	public void setIdAquario(int idAquario) {
		this.idAquario = idAquario;
	}

	public LocalDate getDtAquisicao() {
		return dtAquisicao;
	}

	public void setDtAquisicao(LocalDate dtAquisicao) {
		this.dtAquisicao = dtAquisicao;
	}

	public LocalDate getDtNascimento() {
		return dtNascimento;
	}

	public void setDtNascimento(LocalDate dtNascimento) {
		this.dtNascimento = dtNascimento;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public BigDecimal getVlrUnitario() {
		return vlrUnitario;
	}

	public void setVlrUnitario(BigDecimal vlrUnitario) {
		this.vlrUnitario = vlrUnitario;
	}

	public int getQtde() {
		return qtde;
	}

	public void setQtde(int qtde) {
		this.qtde = qtde;
	}

	public Double getAvaliacao() {
		return avaliacao;
	}

	public void setAvaliacao(Double avaliacao) {
		this.avaliacao = avaliacao;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
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
