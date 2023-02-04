package com.gpuBase.model;

import java.sql.SQLException;
import java.util.Collection;

public interface FotoModel {
	
	public void doSave(FotoBean foto) throws SQLException;

	public boolean doDelete(String path) throws SQLException;

	public FotoBean doRetrieveByKey(String path) throws SQLException;
	
	public Collection<FotoBean> doRetrieveAll(String orderParameter) 
			throws SQLException;
	
	
	/**/
	public  Collection<FotoBean> doRetrieveByCustom(int idCustom) 
			throws SQLException;
	
	public  FotoBean doRetrieveOneByCustom(int idCustom) 
			throws SQLException;

	public void doDeleteByCustom(int idVendita)
			throws SQLException;


	public int getCountFoto(int idCustom)
			throws SQLException;
	
	public  FotoBean doRetrieveOneByCustomFlag(int idCustom, int flagFoto) 
			throws SQLException;
	
}
