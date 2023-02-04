package com.gpuBase.model;

import java.sql.SQLException;
import java.util.Collection;


public interface SchedaModel {

	public void doSave(SchedaBean scheda) throws SQLException;

	public boolean doDelete(String idScheda) throws SQLException;

	public SchedaBean doRetrieveByKey(String idScheda) throws SQLException;
	
	public Collection<SchedaBean> doRetrieveAll(String orderParameter) throws SQLException;
}

