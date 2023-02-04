package com.gpuBase.model;

public class VenditaBean {

    

	public VenditaBean() {
		super();
	}
	
	
	public VenditaBean(int idCustom, String nomeAmministratore, double sconto, double prezzo, double iva, int pezzi) {
		super();
		this.idCustom = idCustom;
		this.nomeAmministratore = nomeAmministratore;
		this.sconto = sconto;
		this.prezzo = prezzo;
		this.iva = iva;
		this.pezzi = pezzi;
	}


	
	public VenditaBean(int idCustom, String nomeAmministratore, double sconto, double prezzo, double iva, int pezzi,
			String idScheda, String nomeCustom) {
		super();
		this.idCustom = idCustom;
		this.nomeAmministratore = nomeAmministratore;
		this.sconto = sconto;
		this.prezzo = prezzo;
		this.iva = iva;
		this.pezzi = pezzi;
		this.idScheda = idScheda;
		this.nomeCustom = nomeCustom;
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


	public int getIdVendita() {
		return idCustom;
	}


	public void setIdVendita(int idVendita) {
		this.idCustom = idVendita;
	}


	public String getNomeAmministratore() {
		return nomeAmministratore;
	}


	public void setNomeAmministratore(String nomeAmministratore) {
		this.nomeAmministratore = nomeAmministratore;
	}


	public double getSconto() {
		return sconto;
	}


	public void setSconto(double sconto) {
		this.sconto = sconto;
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
					(prezzo
							+(prezzo/100*iva)
							-(prezzo/100*sconto)
					)
					*pezzi
				);
	}

	

	




	@Override
	public String toString() {
		return "VenditaBean [idCustom=" + idCustom + ", nomeAmministratore=" + nomeAmministratore + ", sconto=" + sconto
				+ ", prezzo=" + prezzo + ", iva=" + iva + ", pezzi=" + pezzi + ", idScheda=" + idScheda
				+ ", nomeCustom=" + nomeCustom + "]";
	}








	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		VenditaBean other = (VenditaBean) obj;
		if (idCustom != other.idCustom)
			return false;
		if (idScheda == null) {
			if (other.idScheda != null)
				return false;
		} else if (!idScheda.equals(other.idScheda))
			return false;
		if (Double.doubleToLongBits(iva) != Double.doubleToLongBits(other.iva))
			return false;
		if (nomeAmministratore == null) {
			if (other.nomeAmministratore != null)
				return false;
		} else if (!nomeAmministratore.equals(other.nomeAmministratore))
			return false;
		if (nomeCustom == null) {
			if (other.nomeCustom != null)
				return false;
		} else if (!nomeCustom.equals(other.nomeCustom))
			return false;
		if (pezzi != other.pezzi)
			return false;
		if (Double.doubleToLongBits(prezzo) != Double.doubleToLongBits(other.prezzo))
			return false;
		if (Double.doubleToLongBits(sconto) != Double.doubleToLongBits(other.sconto))
			return false;
		return true;
	}








	private int idCustom;
    private String nomeAmministratore;
    private double sconto;
    private double prezzo;
    private double iva;
    private int pezzi;
    
    //more info da custom // 
    private String idScheda;
    private String nomeCustom;

}