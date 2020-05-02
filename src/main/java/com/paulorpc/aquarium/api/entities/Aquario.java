package com.paulorpc.aquarium.api.entities;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.*;


@Entity
@Table(name = "Aquario")
public class Aquario implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="idAquario")
	private int id;
	
	@Column(name="nome", nullable=false)
	private String nome;
	
	@Column(name="dtInicio", nullable=false)
	private Date dtInicio;
	
	@Column(name="dtFinal", nullable=true)
	private Date dtFinal;
	
	@Column(name="tipoAgua", nullable=true)
	private String tipoAgua;
	
	@Column(name="tamanho", nullable=true)
	private String tamanho;
	
	@Column(name="volume", nullable=true)
	private int volume;
	
	@Column(name="iluminacao", nullable=true)
	private String iluminacao;
	
	@Column(name="filtragem", nullable=true)
	private String filtragem;
	
	@Column(name="sistemaCO2", nullable=true)
	private String sistemaCO2;
	
	@Column(name="dosagem", nullable=true)
	private String dosagem;
	
	@Column(name="substrato", nullable=true)
	private String substrato;
	
	// private parametros;
	// private foto;
	
	@Column(name="observacao", nullable=true)
	private String observacao;
	
	@Column(name="status", nullable=true)
	private boolean status;
	
	@Column(name="idTipoAquario", nullable=true)
	private int idTipoAquario;
	
	@Column(name="dtCadastro", nullable=false)
	private LocalDateTime dtCadastro;	
	
	@Column(name="dtAtualizacao", nullable=false)
	private LocalDateTime dtAtualizacao;
	
	
	public Aquario() {}
	
	
	public int getId() {
		return id;
	}

	public void setId(int idAquario) {
		this.id = idAquario;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

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
	
	@PreUpdate
	public void preUpdate() {
		dtAtualizacao = LocalDateTime.now();
	}

	@PrePersist
	public void prePersist() {
		LocalDateTime hojeHora = LocalDateTime.now();
		dtCadastro = hojeHora;
		dtAtualizacao = hojeHora;
	}


	@Override
	public String toString() {
		return "Aquario [idAquario=" + id + ", nome=" + nome + ", dtInicio=" + dtInicio + ", dtFinal=" + dtFinal
				+ ", tipoAgua=" + tipoAgua + ", tamanho=" + tamanho + ", volume=" + volume + ", iluminacao="
				+ iluminacao + ", filtragem=" + filtragem + ", sistemaCO2=" + sistemaCO2 + ", dosagem=" + dosagem
				+ ", substrato=" + substrato + ", observacao=" + observacao + ", idTipoAquario=" + idTipoAquario
				+ ", dtCadastro=" + dtCadastro + ", dtAtualizacao=" + dtAtualizacao + "]";
	}
	
	

}
