package com.gpuBase.model;

import java.io.Serializable;

public class CustomBean implements Serializable{
	
	
	private static final long serialVersionUID = 1L;
	

	public CustomBean() {
		super();
		this.idCustom = -1;
		this.idScheda = "";
		this.profondita = -1;
		this.larghezza = -1;
		this.lunghezza = -1;
		this.peso = -1;
		this.nomeCustom = "";
		this.azienda = "";
		this.risoluzioneMax = "";
		this.porteDP = -1;
		this.porteVGA = -1;
		this.porteDVI = -1;
		this.porteHDMI = -1;
	}

	
	public CustomBean(int idCustom, String idScheda, double profondita, double larghezza, double lunghezza, double peso,
			String nomeCustom, String azienda, String risoluzioneMax, int porteDP, int porteVGA, int porteDVI,
			int porteHDMI) {
		super();
		this.idCustom = idCustom;
		this.idScheda = idScheda;
		this.profondita = profondita;
		this.larghezza = larghezza;
		this.lunghezza = lunghezza;
		this.peso = peso;
		this.nomeCustom = nomeCustom;
		this.azienda = azienda;
		this.risoluzioneMax = risoluzioneMax;
		this.porteDP = porteDP;
		this.porteVGA = porteVGA;
		this.porteDVI = porteDVI;
		this.porteHDMI = porteHDMI;
	}

	public int getIdCustom() {
		return idCustom;
	}

	public void setIdCustom(int idCustom) {
		this.idCustom = idCustom;
	}

	public String getIdScheda() {
		return idScheda;
	}

	public void setIdScheda(String idScheda) {
		this.idScheda = idScheda;
	}

	public double getProfondita() {
		return profondita;
	}

	public void setProfondita(double profondità) {
		this.profondita = profondità;
	}

	public double getLarghezza() {
		return larghezza;
	}

	public void setLarghezza(double larghezza) {
		this.larghezza = larghezza;
	}

	public double getLunghezza() {
		return lunghezza;
	}

	public void setLunghezza(double lunghezza) {
		this.lunghezza = lunghezza;
	}

	public double getPeso() {
		return peso;
	}

	public void setPeso(double peso) {
		this.peso = peso;
	}

	public String getNomeCustom() {
		return nomeCustom;
	}

	public void setNomeCustom(String nomeCustom) {
		this.nomeCustom = nomeCustom;
	}

	public String getAzienda() {
		return azienda;
	}

	public void setAzienda(String azienda) {
		this.azienda = azienda;
	}

	public String getRisoluzioneMax() {
		return risoluzioneMax;
	}

	public void setRisoluzioneMax(String risoluzioneMax) {
		this.risoluzioneMax = risoluzioneMax;
	}

	public int getPorteDP() {
		return porteDP;
	}

	public void setPorteDP(int porteDP) {
		this.porteDP = porteDP;
	}

	public int getPorteVGA() {
		return porteVGA;
	}

	public void setPorteVGA(int porteVGA) {
		this.porteVGA = porteVGA;
	}

	public int getPorteDVI() {
		return porteDVI;
	}

	public void setPorteDVI(int porteDVI) {
		this.porteDVI = porteDVI;
	}

	public int getPorteHDMI() {
		return porteHDMI;
	}

	public void setPorteHDMI(int porteHDMI) {
		this.porteHDMI = porteHDMI;
	}

	@Override
	public String toString() {
		return "customBean [idCustom=" + idCustom + ", idScheda=" + idScheda + ", profondità=" + profondita
				+ ", larghezza=" + larghezza + ", lunghezza=" + lunghezza + ", peso=" + peso + ", nomeCustom="
				+ nomeCustom + ", azienda=" + azienda + ", risoluzioneMax=" + risoluzioneMax + ", porteDP=" + porteDP
				+ ", porteVGA=" + porteVGA + ", porteDVI=" + porteDVI + ", porteHDMI=" + porteHDMI + "]";
	}
	
	
	private int idCustom;
	private String idScheda;
	private double profondita;
	private double larghezza;
	private double lunghezza;
	private double peso;
	private String nomeCustom;
	private String azienda;
	private String risoluzioneMax;
	private int porteDP;
	private int porteVGA;
	private int porteDVI;
	private int porteHDMI;
	
}
