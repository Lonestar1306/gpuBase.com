package com.gpuBase.model;

import java.io.Serializable;

public class OrdinazioneBean implements Serializable{


	private static final long serialVersionUID = 1L;
	
	public OrdinazioneBean() {
		super();
	}


	public OrdinazioneBean(int idOrdine, String idCliente, int idCustom, String data, double prezzo, double iva,
			int pezzi) {
		super();
		this.idOrdine = idOrdine;
		this.mailCliente = idCliente;
		this.idCustom = idCustom;
		this.data = data;
		this.prezzo = prezzo;
		this.iva = iva;
		this.pezzi = pezzi;
	}


	public int getIdOrdine() {
		return idOrdine;
	}


	public void setIdOrdine(int idOrdine) {
		this.idOrdine = idOrdine;
	}


	public String getMailCliente() {
		return mailCliente;
	}


	public void setMailCliente(String idCliente) {
		this.mailCliente = idCliente;
	}


	public int getIdCustom() {
		return idCustom;
	}


	public void setIdCustom(int idCustom) {
		this.idCustom = idCustom;
	}


	public String getData() {
		return data;
	}


	public void setData(String data) {
		this.data = data;
	}


	public double getPrezzo() {
		return prezzo;
	}


	public void setPrezzo(double prezzo) {
		this.prezzo = prezzo;
	}


	public double getIva() {
		return iva;
	}


	public void setIva(double iva) {
		this.iva = iva;
	}


	public int getPezzi() {
		return pezzi;
	}


	public void setPezzi(int pezzi) {
		this.pezzi = pezzi;
	}

	
	/*calcola il prezzo totale dovuto del prodotto*/
	public double getTotale() {
		
		return (
				(prezzo+(prezzo/100*iva))*pezzi
			);
	}
	

	@Override
	public String toString() {
		return "OrdinazioneBean [idOrdine=" + idOrdine + ", idCliente=" + mailCliente + ", idCustom=" + idCustom
				+ ", data=" + data + ", prezzo=" + prezzo + ", iva=" + iva + ", pezzi=" + pezzi + "]";
	}
	
	
	int idOrdine;
	String mailCliente;
	int idCustom;
	String data;
	double prezzo;
	double iva;
	int pezzi;
	

}
