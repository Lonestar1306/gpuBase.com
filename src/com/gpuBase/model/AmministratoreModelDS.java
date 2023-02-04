package com.gpuBase.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class AmministratoreModelDS implements AmministratoreModel {

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

	private static final String TABLE_NAME = "Amministratore";
	
	
	
	public synchronized void doSave(AmministratoreBean amministratore) throws SQLException {

		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String insertSQL = "INSERT INTO " + AmministratoreModelDS.TABLE_NAME
				+ " (nomeAmministratore,password) VALUES (?, ?)";

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);
			preparedStatement.setString(1, amministratore.getNomeAmministratore());
			preparedStatement.setString(2, amministratore.getPassword());
			
			preparedStatement.executeUpdate();

			connection.commit();
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
			

	public synchronized AmministratoreBean doRetrieveByKey(String nomeAmministratore) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		AmministratoreBean bean = new AmministratoreBean();

		String selectSQL = "SELECT * FROM " + AmministratoreModelDS.TABLE_NAME + " WHERE nomeAmministratore = ?";

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, nomeAmministratore);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				bean.setNomeAmministratore(rs.getString("nomeAmministratore"));
				bean.setPassword(rs.getString("password"));
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
	
	
	public synchronized boolean doDelete(String nomeAmministratore) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		int result = 0;

		String deleteSQL = "DELETE FROM " + AmministratoreModelDS.TABLE_NAME + " WHERE nomeAmministratore = ?";

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(deleteSQL);
			preparedStatement.setString(1, nomeAmministratore);

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
	
	
	
	public synchronized Collection<AmministratoreBean> doRetrieveAll(String order) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		Collection<AmministratoreBean> amministratori = new LinkedList<AmministratoreBean>();

		String selectSQL = "SELECT * FROM " + AmministratoreModelDS.TABLE_NAME;

		if (order != null && !order.equals("")) {
			selectSQL += " ORDER BY " + order;
		}

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				AmministratoreBean bean = new AmministratoreBean();
				/*
				bean.setIdScheda(rs.getString("idScheda"));
				bean.setProduttore(rs.getString("produttore"));
				bean.setDataUscita(rs.getString("dataUscita"));
				bean.setNomeCore(rs.getString("nomeCore"));
				bean.setNumeroCore(rs.getInt("numeroCore"));
				bean.setTDP(rs.getInt("TPD"));
				bean.setFrequenzaCore(rs.getInt("frequenzaCore"));
				bean.setFrequenzaMemoria(rs.getInt("frequenzaMemoria"));
				bean.setGenerazioneMemoria(rs.getString("generazioneMemoria"));
				bean.setDimensioneMemoria(rs.getDouble("dimensioneMemoria"));
				*/
				bean.setNomeAmministratore(rs.getString("nomeAmministratore"));
				bean.setPassword(rs.getString("password"));
				
				amministratori.add(bean);
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
		return amministratori;
	}
	
}
