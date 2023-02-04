package com.gpuBase.model;

import java.io.Serializable;

public class FotoBean implements Serializable{


	private static final long serialVersionUID = 1L;
	
	private int idCustom; 
	private String path; //path di memorizzazione foto + nome foto .png
	byte[] file;  //ogni foto memorizzata rappresenta un insieme di byte
	/*nel DB viene salvata come mediumblob*/
	
	
	public FotoBean() {
		super();
	}

	public FotoBean(int idCustom, String path,byte[] file) {
		super();
		this.idCustom = idCustom;
		this.path = path;
		this.file=file;
	}

	public int getIdCustom() {
		return idCustom;
	}

	public void setIdCustom(int idCustom) {
		this.idCustom = idCustom;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	public byte[] getFile() {
		return file;
	}

	public void setFile(byte[] file) {
		this.file = file;
	}

	@Override
	public String toString() {
		return "FotoBean [idCustom=" + idCustom + ", path=" + path + "]";
	}

	

}
