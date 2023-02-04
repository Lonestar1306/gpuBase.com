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


public class SchedaModelDS implements SchedaModel{

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

	private static final String TABLE_NAME = "Scheda";
	
	
	
	public synchronized void doSave(SchedaBean scheda) throws SQLException {

		Connection connection = null;
		PreparedStatement preparedStatement = null;

		//System.out.println("saving scheda details");
		
		String insertSQL = "INSERT INTO " + SchedaModelDS.TABLE_NAME + " (idScheda,produttore,dataUscita,nomeCore,numeroCore,TDP,frequenzaCore,frequenzaMemoria,generazioneMemoria,dimensioneMemoria) "
				+ " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		
		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);
			preparedStatement.setString(1, scheda.getIdScheda());
			preparedStatement.setString(2, scheda.getProduttore());
			preparedStatement.setDate(3, scheda.getDataUscita());
			preparedStatement.setString(4, scheda.getNomeCore());
			preparedStatement.setInt(5, scheda.getNumeroCore());
			preparedStatement.setInt(6, scheda.getTDP());
			preparedStatement.setInt(7, scheda.getFrequenzaCore());
			preparedStatement.setInt(8, scheda.getFrequenzaMemoria());
			preparedStatement.setString(9, scheda.getGenerazioneMemoria());
			preparedStatement.setDouble(10, scheda.getDimensioneMemoria());
			
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
			

	public synchronized SchedaBean doRetrieveByKey(String idScheda) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		SchedaBean bean = new SchedaBean();

		String selectSQL = "SELECT * FROM " + SchedaModelDS.TABLE_NAME + " WHERE idScheda = ?";
		//System.out.println("getting scheda details");

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, idScheda);

			ResultSet rs = preparedStatement.executeQuery();

			if(rs!=null && rs.next()) {
				bean.setIdScheda(rs.getString("idScheda"));
				bean.setProduttore(rs.getString("produttore"));
				bean.setDataUscita(rs.getDate("dataUscita"));
				bean.setNomeCore(rs.getString("nomeCore"));
				bean.setNumeroCore(rs.getInt("numeroCore"));
				bean.setTDP(rs.getInt("TDP"));
				bean.setFrequenzaCore(rs.getInt("frequenzaCore"));
				bean.setFrequenzaMemoria(rs.getInt("frequenzaMemoria"));
				bean.setGenerazioneMemoria(rs.getString("generazioneMemoria"));
				bean.setDimensioneMemoria(rs.getDouble("dimensioneMemoria"));
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
	
	
	public synchronized boolean doDelete(String idScheda) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		int result = 0;

		String deleteSQL = "DELETE FROM " + SchedaModelDS.TABLE_NAME + " WHERE idScheda = ?";

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(deleteSQL);
			preparedStatement.setString(1, idScheda);

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
	
	
	
	public synchronized Collection<SchedaBean> doRetrieveAll(String order) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		Collection<SchedaBean> schede = new LinkedList<SchedaBean>();

		
		//System.out.println("getting ALL scheda details");
		String selectSQL = "SELECT * FROM " + SchedaModelDS.TABLE_NAME;

		if (order != null && !order.equals("")) {
			selectSQL += " ORDER BY " + order;
		}

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				SchedaBean bean = new SchedaBean();
				
				bean.setIdScheda(rs.getString("idScheda"));
				bean.setProduttore(rs.getString("produttore"));
				bean.setDataUscita(rs.getDate("dataUscita"));
				bean.setNomeCore(rs.getString("nomeCore"));
				bean.setNumeroCore(rs.getInt("numeroCore"));
				bean.setTDP(rs.getInt("TPD"));
				bean.setFrequenzaCore(rs.getInt("frequenzaCore"));
				bean.setFrequenzaMemoria(rs.getInt("frequenzaMemoria"));
				bean.setGenerazioneMemoria(rs.getString("generazioneMemoria"));
				bean.setDimensioneMemoria(rs.getDouble("dimensioneMemoria"));
				
				schede.add(bean);
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
		return schede;
	}
	
}
