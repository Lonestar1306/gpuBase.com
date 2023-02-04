package com.gpuBase.model;

import java.sql.Date;

public class VenditaCustomSchedaBean {

	
	
	public VenditaCustomSchedaBean() {
		super();
	}
	
	
	
	public VenditaCustomSchedaBean(int idCustom, double prezzo, String idScheda, String nomeCustom,
			String risoluzioneMax, Date dataUscita, int numeroCore, int tDP, int frequenzaCore, int frequenzaMemoria,
			double dimensioneMemoria) {
		super();
		this.idCustom = idCustom;
		this.prezzo = prezzo;
		this.idScheda = idScheda;
		this.nomeCustom = nomeCustom;
		this.risoluzioneMax = risoluzioneMax;
		this.dataUscita = dataUscita;
		this.numeroCore = numeroCore;
		TDP = tDP;
		this.frequenzaCore = frequenzaCore;
		this.frequenzaMemoria = frequenzaMemoria;
		this.dimensioneMemoria = dimensioneMemoria;
	}



	public int getIdCustom() {
		return idCustom;
	}



	public void setIdCustom(int idCustom) {
		this.idCustom = idCustom;
	}



	public double getPrezzo() {
		return prezzo;
	}



	public void setPrezzo(double prezzo) {
		this.prezzo = prezzo;
	}



	public String getIdScheda() {
		return idScheda;
	}



	public void setIdScheda(String idScheda) {
		this.idScheda = idScheda;
	}



	public String getNomeCustom() {
		return nomeCustom;
	}



	public void setNomeCustom(String nomeCustom) {
		this.nomeCustom = nomeCustom;
	}



	public String getRisoluzioneMax() {
		return risoluzioneMax;
	}



	public void setRisoluzioneMax(String risoluzioneMax) {
		this.risoluzioneMax = risoluzioneMax;
	}



	public Date getDataUscita() {
		return dataUscita;
	}



	public void setDataUscita(Date dataUscita) {
		this.dataUscita = dataUscita;
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



	public double getDimensioneMemoria() {
		return dimensioneMemoria;
	}



	public void setDimensioneMemoria(double dimensioneMemoria) {
		this.dimensioneMemoria = dimensioneMemoria;
	}



	@Override
	public String toString() {
		return "VenditaCustomSchedaBean [idCustom=" + idCustom + ", prezzo=" + prezzo + ", idScheda=" + idScheda
				+ ", nomeCustom=" + nomeCustom + ", risoluzioneMax=" + risoluzioneMax + ", dataUscita=" + dataUscita
				+ ", numeroCore=" + numeroCore + ", TDP=" + TDP + ", frequenzaCore=" + frequenzaCore
				+ ", frequenzaMemoria=" + frequenzaMemoria + ", dimensioneMemoria=" + dimensioneMemoria + "]";
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		VenditaCustomSchedaBean other = (VenditaCustomSchedaBean) obj;
		if (TDP != other.TDP)
			return false;
		if (dataUscita == null) {
			if (other.dataUscita != null)
				return false;
		} else if (!dataUscita.equals(other.dataUscita))
			return false;
		if (Double.doubleToLongBits(dimensioneMemoria) != Double.doubleToLongBits(other.dimensioneMemoria))
			return false;
		if (frequenzaCore != other.frequenzaCore)
			return false;
		if (frequenzaMemoria != other.frequenzaMemoria)
			return false;
		if (idCustom != other.idCustom)
			return false;
		if (idScheda == null) {
			if (other.idScheda != null)
				return false;
		} else if (!idScheda.equals(other.idScheda))
			return false;
		if (nomeCustom == null) {
			if (other.nomeCustom != null)
				return false;
		} else if (!nomeCustom.equals(other.nomeCustom))
			return false;
		if (numeroCore != other.numeroCore)
			return false;
		if (Double.doubleToLongBits(prezzo) != Double.doubleToLongBits(other.prezzo))
			return false;
		if (risoluzioneMax == null) {
			if (other.risoluzioneMax != null)
				return false;
		} else if (!risoluzioneMax.equals(other.risoluzioneMax))
			return false;
		return true;
	}



	private int idCustom;
    //private String nomeAmministratore;
    //private double sconto;
    private double prezzo;
    //private double iva;
    //private int pezzi;
    
    //private int idCustom;
	private String idScheda;
	//private double profondita;
	//private double larghezza;
	//private double lunghezza;
	//private double peso;
	private String nomeCustom;
	//private String azienda;
	private String risoluzioneMax;
	//private int porteDP;
	//private int porteVGA;
	//private int porteDVI;
	//private int porteHDMI;
	

	//private String idScheda;
	//private String produttore;
	private Date dataUscita;
	//private String nomeCore;
	private int numeroCore;
	private int TDP;
	private int frequenzaCore;
	private int frequenzaMemoria;
	//private String generazioneMemoria;
	private double dimensioneMemoria;
}
