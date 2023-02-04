package com.gpuBase.model;

import java.sql.SQLException;
import java.util.Collection;

public interface CustomModel {
	
	
	public void doSave(CustomBean custom) throws SQLException;

	public boolean doDelete(int idCustom) throws SQLException;

	public CustomBean doRetrieveByKey(int idCustom) throws SQLException;
	
	public Collection<CustomBean> doRetrieveAll(String orderParameter) 
			throws SQLException;

	
}
