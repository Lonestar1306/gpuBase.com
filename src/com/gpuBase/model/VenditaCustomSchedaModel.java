package com.gpuBase.model;

import java.sql.SQLException;
import java.util.Collection;

public interface VenditaCustomSchedaModel {
	
	
	public Collection<VenditaCustomSchedaBean> doRetrieveAll() throws SQLException;
	
	public VenditaCustomSchedaBean doRetrieveByKey(int key) throws SQLException;
	
}