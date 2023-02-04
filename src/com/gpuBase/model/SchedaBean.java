package com.gpuBase.model;

import java.io.Serializable;
import java.sql.Date;

public class SchedaBean implements Serializable {

	
	private static final long serialVersionUID = 1L;
	
	public SchedaBean() {
        super();
        this.idScheda = "";
        this.produttore = "";
        this.dataUscita = null;
        this.nomeCore = "";
        this.numeroCore = -1;
        this.TDP = -1;
        this.frequenzaCore = -1;
        this.frequenzaMemoria = -1;
        this.generazioneMemoria = "";
        this.dimensioneMemoria = -1;
    }
	
	public SchedaBean(String idScheda, String produttore, Date dataUscita, String nomeCore, int numeroCore, int TDP,
			int frequenzaCore, int frequenzaMemoria, String generazioneMemoria, double dimensioneMemoria) {
	
		this.idScheda = idScheda;
		this.produttore = produttore;
		this.dataUscita = dataUscita;
		this.nomeCore = nomeCore;
		this.numeroCore = numeroCore;
		this.TDP = TDP;
		this.frequenzaCore = frequenzaCore;
		this.frequenzaMemoria = frequenzaMemoria;
		this.generazioneMemoria = generazioneMemoria;
		this.dimensioneMemoria = dimensioneMemoria;
	}
	
	public String getIdScheda() {
		return idScheda;
	}
	public void setIdScheda(String idScheda) {
		this.idScheda = idScheda;
	}
	public String getProduttore() {
		return produttore;
	}
	public void setProduttore(String produttore) {
		this.produttore = produttore;
	}
	public Date getDataUscita() {
		return dataUscita;
	}
	public void setDataUscita(Date dataUscita) {
		this.dataUscita = dataUscita;
	}
	public String getNomeCore() {
		return nomeCore;
	}
	public void setNomeCore(String nomeCore) {
		this.nomeCore = nomeCore;
	}
	public int getNumeroCore() {
		return numeroCore;
	}
	public void setNumeroCore(int numeroCore) {
		this.numeroCore = numeroCore;
	}
	public int getTDP() {
		return TDP;
	}
	public void setTDP(int tDP) {
		TDP = tDP;
	}
	public int getFrequenzaCore() {
		return frequenzaCore;
	}
	public void setFrequenzaCore(int frequenzaCore) {
		this.frequenzaCore = frequenzaCore;
	}
	public int getFrequenzaMemoria() {
		return frequenzaMemoria;
	}
	public void setFrequenzaMemoria(int frequenzaMemoria) {
		this.frequenzaMemoria = frequenzaMemoria;
	}
	public String getGenerazioneMemoria() {
		return generazioneMemoria;
	}
	public void setGenerazioneMemoria(String generazioneMemoria) {
		this.generazioneMemoria = generazioneMemoria;
	}
	public double getDimensioneMemoria() {
		return dimensioneMemoria;
	}
	public void setDimensioneMemoria(double dimensioneMemoria) {
		this.dimensioneMemoria = dimensioneMemoria;
	}
	
	
	private String idScheda;
	private String produttore;
	private Date dataUscita;
	private String nomeCore;
	private int numeroCore;
	private int TDP;
	private int frequenzaCore;
	private int frequenzaMemoria;
	private String generazioneMemoria;
	private double dimensioneMemoria;
}
