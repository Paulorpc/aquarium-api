package com.paulorpc.aquarium.api.entities;

public abstract class Taxonomia {
	
	private String reino;
	private String filo;
	private String classe;
	private String ordem;
	private String família;
	private String gênero;
	private String espécie;

	public Taxonomia() {}

	public String getReino() {
		return reino;
	}

	public void setReino(String reino) {
		this.reino = reino;
	}

	public String getFilo() {
		return filo;
	}

	public void setFilo(String filo) {
		this.filo = filo;
	}

	public String getClasse() {
		return classe;
	}

	public void setClasse(String classe) {
		this.classe = classe;
	}

	public String getOrdem() {
		return ordem;
	}

	public void setOrdem(String ordem) {
		this.ordem = ordem;
	}

	public String getFamília() {
		return família;
	}

	public void setFamília(String família) {
		this.família = família;
	}

	public String getGênero() {
		return gênero;
	}

	public void setGênero(String gênero) {
		this.gênero = gênero;
	}

	public String getEspécie() {
		return espécie;
	}

	public void setEspécie(String espécie) {
		this.espécie = espécie;
	}
	
	

}
