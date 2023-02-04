package com.gpuBase.model;

import java.sql.SQLException;
import java.util.Collection;


public interface AmministratoreModel {

    public void doSave(AmministratoreBean amministratore) throws SQLException;

    public boolean doDelete(String nomeAmministratore) throws SQLException;

    public AmministratoreBean doRetrieveByKey(String nomeAmministratore) throws SQLException;

    public Collection<AmministratoreBean> doRetrieveAll(String orderParameter) throws SQLException;
}