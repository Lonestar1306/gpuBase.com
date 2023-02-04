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

public class OrdinazioneModelDS implements OrdinazioneModel{



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

	private static final String TABLE_NAME = "Ordinazione";
	/****************************************************************/
	
	@Override
	public void doSave(OrdinazioneBean ordinazione) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String insertSQL = "INSERT INTO " + OrdinazioneModelDS.TABLE_NAME
				+ " (mailCliente, idCustom, data, prezzo, iva, pezzi)"
				+ " VALUES (?, ?, ?, ?, ?, ?)";

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);
			
			
			//preparedStatement.setInt(1, ordinazione.getIdOrdine());
			preparedStatement.setString(1, ordinazione.getMailCliente());
			preparedStatement.setInt(2, ordinazione.getIdCustom());
			preparedStatement.setString(3, ordinazione.getData());
			preparedStatement.setDouble(4, ordinazione.getPrezzo());
			preparedStatement.setDouble(5, ordinazione.getIva());
			preparedStatement.setInt(6, ordinazione.getPezzi());
			
			preparedStatement.executeUpdate();

			//connection.commit();
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
	public boolean doDelete(int idOrdinazione) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		int result = 0;

		String deleteSQL = "DELETE FROM " + OrdinazioneModelDS.TABLE_NAME + " WHERE idOrdine = ?";

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(deleteSQL);
			preparedStatement.setInt(1, idOrdinazione);

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
	public OrdinazioneBean doRetrieveByKey(int idOrdinazione) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		OrdinazioneBean bean = new OrdinazioneBean();

		String selectSQL = "SELECT * FROM " + OrdinazioneModelDS.TABLE_NAME + " WHERE idOrdine = ?";

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setInt(1, idOrdinazione);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				bean.setIdOrdine(rs.getInt("idOrdine"));
				bean.setMailCliente(rs.getString("mailCliente"));
				bean.setIdCustom(rs.getInt("idCustom"));
				bean.setData(rs.getString("data"));
				bean.setPrezzo(rs.getDouble("prezzo"));
				bean.setIva(rs.getDouble("iva"));
				bean.setPezzi(rs.getInt("pezzi"));
				
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
	public Collection<OrdinazioneBean> doRetrieveAll(String orderParameter) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		Collection<OrdinazioneBean> products = new LinkedList<OrdinazioneBean>();

		String selectSQL = "SELECT * FROM " + OrdinazioneModelDS.TABLE_NAME;

		if (orderParameter != null && !orderParameter.equals("")) {
			selectSQL += " ORDER BY " + orderParameter;
		}

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				OrdinazioneBean bean = new OrdinazioneBean();


				bean.setIdOrdine(rs.getInt("idOrdine"));
				bean.setMailCliente(rs.getString("mailCliente"));
				bean.setIdCustom(rs.getInt("idCustom"));
				bean.setData(rs.getString("data"));
				bean.setPrezzo(rs.getDouble("prezzo"));
				bean.setIva(rs.getDouble("iva"));
				bean.setPezzi(rs.getInt("pezzi"));
				
				products.add(bean);	
				
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
		return products;
	}
	
	
	
	/*************************************************************************/
	@Override
	public Collection<OrdinazioneBean> doRetrieveAllByUser(String mailCliente) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		Collection<OrdinazioneBean> orders = new LinkedList<OrdinazioneBean>();

		String selectSQL = "SELECT * FROM " + OrdinazioneModelDS.TABLE_NAME + " WHERE mailCliente = ? ORDER BY idOrdine DESC";
		

		//System.out.println("doRetryveAllByUser");
		
		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, mailCliente);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				OrdinazioneBean bean = new OrdinazioneBean();


				bean.setIdOrdine(rs.getInt("idOrdine"));
				bean.setMailCliente(rs.getString("mailCliente"));
				bean.setIdCustom(rs.getInt("idCustom"));
				bean.setData(rs.getString("data"));
				bean.setPrezzo(rs.getDouble("prezzo"));
				bean.setIva(rs.getDouble("iva"));
				bean.setPezzi(rs.getInt("pezzi"));
				
				orders.add(bean);	
				
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
		return orders;
	}
	
	/*************************************************************************/
	@Override
	public Collection<OrdinazioneBean> doRetrieveAllByUserBetweenDates(String mailCliente, String dataInizio,
			String dataFine) throws SQLException {
		
		
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		Collection<OrdinazioneBean> orders = new LinkedList<OrdinazioneBean>();

		String selectSQL = "SELECT * FROM " + OrdinazioneModelDS.TABLE_NAME + " WHERE mailCliente = ? AND data BETWEEN ? AND ? ORDER BY data DESC";
		

		
		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, mailCliente);
			preparedStatement.setString(2, dataInizio);
			preparedStatement.setString(3, dataFine);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				OrdinazioneBean bean = new OrdinazioneBean();
				//System.out.println("leggo una riga");

				bean.setIdOrdine(rs.getInt("idOrdine"));
				bean.setMailCliente(rs.getString("mailCliente"));
				bean.setIdCustom(rs.getInt("idCustom"));
				bean.setData(rs.getString("data"));
				bean.setPrezzo(rs.getDouble("prezzo"));
				bean.setIva(rs.getDouble("iva"));
				bean.setPezzi(rs.getInt("pezzi"));
				
				orders.add(bean);	
				//if(orders.contains(bean)){System.out.println(orders.toString());}
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
		if(orders.isEmpty())return null;
		else return orders;
	}

}



