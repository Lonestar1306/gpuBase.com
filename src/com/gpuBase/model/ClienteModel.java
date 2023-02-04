package com.gpuBase.model;

import java.sql.SQLException;
import java.util.Collection;

public interface ClienteModel {



    public boolean doDelete(String mailCliente) throws SQLException;

    public ClienteBean doRetrieveByKey(String mailCliente) throws SQLException;

    public Collection<ClienteBean> doRetrieveAll(String orderParameter) 
            throws SQLException;

    void doSave(ClienteBean cliente) throws SQLException;


}