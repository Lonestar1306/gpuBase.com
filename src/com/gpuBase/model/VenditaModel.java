package com.gpuBase.model;

import java.sql.SQLException;
import java.util.Collection;

public interface VenditaModel {
	
	
	public void doSave(VenditaBean vendita) throws SQLException;

	public boolean doDelete(int idVendita) throws SQLException;

	public VenditaBean doRetrieveByKey(int idVendita) throws SQLException;
	
	public VenditaBean doRetrieveByKeyDetailed(int idVendita) throws SQLException;
	
	public Collection<VenditaBean> doRetrieveAll(String orderParameter) throws SQLException;
	
	public Collection<VenditaBean> doRetrieveAllDetailed(String orderParameter) throws SQLException;
	
	public boolean doUpdatePz(int idVendita,int pezzi)throws SQLException;

	
}
