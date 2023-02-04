package com.gpuBase.model;


import java.sql.SQLException;
import java.util.Collection;


public interface OrdinazioneModel {


	public void doSave(OrdinazioneBean ordinazione) throws SQLException;

	public boolean doDelete(int idOrdinazione) throws SQLException;

	public OrdinazioneBean doRetrieveByKey(int idOrdinazione) throws SQLException;
	
	public Collection<OrdinazioneBean> doRetrieveAll(String orderParameter) throws SQLException;
	
	public Collection<OrdinazioneBean> doRetrieveAllByUser(String mailCliente) throws SQLException;
	
	public Collection<OrdinazioneBean> doRetrieveAllByUserBetweenDates(String mailCliente, String dataInizio, String dataFine) throws SQLException;
	
	
}

