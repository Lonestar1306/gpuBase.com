package com.gpuBase.model;

import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ClienteModelDS implements ClienteModel{

	private static DataSource ds;
	
	static {
		try {
			Context initCtx = new InitialContext();
			Context envCtx = (Context) initCtx.lookup("java:comp/env");

			ds = (DataSource) envCtx.lookup("jdbc/gpuBase");

		} catch (NamingException e) {
			System.out.println("Error:" + e.getMessage());
		}
	}

	private static final String TABLE_NAME = "Cliente";
	/****************************************************************/
	@Override
	public void doSave(ClienteBean cliente) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String insertSQL = "INSERT INTO " + ClienteModelDS.TABLE_NAME
				+ " (mailCliente,password,indirizzo,telefono)"
				+ " VALUES (?, ?, ?, ?)";

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);
			

			
			preparedStatement.setString(1,cliente.getMailCliente());
			preparedStatement.setString(2,cliente.getPassword());
			preparedStatement.setString(3,cliente.getIndirizzo());
			preparedStatement.setString(4, cliente.getTelefono());
			
			
			preparedStatement.executeUpdate();

			
		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				if (connection != null)
					connection.close();
			}
		}
	}
	/*************************************************************/
	@Override
	public boolean doDelete(String mailCliente) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		int result = 0;

		String deleteSQL = "DELETE FROM " + ClienteModelDS.TABLE_NAME + " WHERE mailCliente = ?";

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(deleteSQL);
			preparedStatement.setString(1, mailCliente);

			result = preparedStatement.executeUpdate();

		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				if (connection != null)
					connection.close();
			}
		}
		return (result != 0);
	}

	/******************************************/
	@Override
	public ClienteBean doRetrieveByKey(String mailCliente) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		ClienteBean bean = new ClienteBean();

		String selectSQL = "SELECT * FROM " + ClienteModelDS.TABLE_NAME + " WHERE mailCliente = ?";

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setString(1,mailCliente);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				bean.setMailCliente(rs.getString("mailCliente"));
				bean.setPassword(rs.getString("password"));
				bean.setIndirizzo(rs.getString("indirizzo"));
				bean.setTelefono(rs.getString("telefono"));
			}

		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				if (connection != null)
					connection.close();
			}
		}
		return bean;
	}

	/***********************************************/
	@Override
	public Collection<ClienteBean> doRetrieveAll(String orderParameter) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		Collection<ClienteBean> clienti = new LinkedList<ClienteBean>();

		String selectSQL = "SELECT * FROM " + ClienteModelDS.TABLE_NAME;

		if (orderParameter != null && !orderParameter.equals("")) {
			selectSQL += " ORDER BY " + orderParameter;
		}

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				ClienteBean bean = new ClienteBean();

				bean.setMailCliente(rs.getString("mailCliente"));
				bean.setPassword(rs.getString("password"));
				bean.setIndirizzo(rs.getString("indirizzo"));
				bean.setTelefono(rs.getString("telefono"));
				
				clienti.add(bean);
			}

		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				if (connection != null)
					connection.close();
			}
		}
		return clienti;
	}

}
