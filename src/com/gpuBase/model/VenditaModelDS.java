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

public class VenditaModelDS implements VenditaModel {

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

	private static final String TABLE_NAME = "Vendita";

	/*****************************************************************/
	public void doSave(VenditaBean vendita) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String insertSQL = "INSERT INTO " + VenditaModelDS.TABLE_NAME
				+ " (idVendita, nomeAmministratore,sconto , prezzo, iva, pezzi)" + " VALUES (?, ?, ?, ?, ?, ?)";

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);

			preparedStatement.setInt(1, vendita.getIdVendita());
			preparedStatement.setString(2, vendita.getNomeAmministratore());
			preparedStatement.setDouble(3, vendita.getSconto());
			preparedStatement.setDouble(4, vendita.getPrezzo());
			preparedStatement.setDouble(5, vendita.getIva());
			preparedStatement.setInt(6, vendita.getPezzi());

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

	/*************************************************************************************** */
	public synchronized boolean doDelete(int idVendita) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		int result = 0;

		String deleteSQL = "DELETE FROM " + VenditaModelDS.TABLE_NAME + " WHERE idVendita = ?";

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(deleteSQL);
			preparedStatement.setInt(1, idVendita);

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

	/****************************************************************************************** */
	public synchronized VenditaBean doRetrieveByKey(int idVendita) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		VenditaBean bean = new VenditaBean();

		String selectSQL = "SELECT * FROM " + VenditaModelDS.TABLE_NAME + " WHERE idVendita = ?";

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setInt(1, idVendita);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				bean.setIdVendita(rs.getInt("idVendita"));
				bean.setNomeAmministratore(rs.getString("nomeAmministratore"));
				bean.setSconto(rs.getDouble("sconto"));
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

	/****************************************************************************************** */
	public synchronized VenditaBean doRetrieveByKeyDetailed(int idVendita) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		VenditaBean bean = new VenditaBean();

		
		String T1 = "(SELECT * FROM " + VenditaModelDS.TABLE_NAME +" WHERE idVendita = ? )AS T1";
		String T2 = "(SELECT idCustom,idScheda,nomeCustom FROM Custom  WHERE idCustom = ? ) AS T2";
		String selectSQL="SELECT * FROM "+ T1 +" JOIN "+T2+" ON T1.idVendita=T2.idCustom";
		

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setInt(1, idVendita);
			preparedStatement.setInt(2, idVendita);

			ResultSet rs = preparedStatement.executeQuery();

			if (rs.next()) {
				bean.setIdVendita(rs.getInt("idVendita"));
				bean.setNomeAmministratore(rs.getString("nomeAmministratore"));
				bean.setSconto(rs.getDouble("sconto"));
				bean.setPrezzo(rs.getDouble("prezzo"));
				bean.setIva(rs.getDouble("iva"));
				bean.setPezzi(rs.getInt("pezzi"));
				bean.setIdScheda(rs.getString("idScheda"));
				bean.setNomeCustom(rs.getString("nomeCustom"));
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
	/***************************************************************************************** */

	public synchronized Collection<VenditaBean> doRetrieveAll(String orderParameter) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		Collection<VenditaBean> products = new LinkedList<VenditaBean>();

		String selectSQL = "SELECT * FROM " + VenditaModelDS.TABLE_NAME;

		if (orderParameter != null && !orderParameter.equals("")) {
			selectSQL += " ORDER BY " + orderParameter;
		}

		try {
			connection = ds.getConnection();

			preparedStatement = connection.prepareStatement(selectSQL);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				VenditaBean bean = new VenditaBean();

				bean.setIdVendita(rs.getInt("idVendita"));
				bean.setNomeAmministratore(rs.getString("nomeAmministratore"));
				bean.setSconto(rs.getDouble("sconto"));
				bean.setPrezzo(rs.getDouble("prezzo"));
				bean.setIva(rs.getDouble("iva"));
				bean.setPezzi(rs.getInt("pezzi"));
				

				products.add(bean);

			}
		
		}
		finally {
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

	/***************************************************************************************** */

	public synchronized Collection<VenditaBean> doRetrieveAllDetailed(String orderParameter) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		Collection<VenditaBean> products = new LinkedList<VenditaBean>();

		String T1 = "(SELECT * FROM " + VenditaModelDS.TABLE_NAME +")AS T1";
		String T2 = "(SELECT idCustom,idScheda,nomeCustom FROM Custom) AS T2";
		String selectSQL="SELECT * FROM "+ T1 +" JOIN "+T2+" ON T1.idVendita=T2.idCustom";

		if (orderParameter != null && !orderParameter.equals("")) {
			selectSQL += " ORDER BY " + orderParameter;
		}

		try {
			connection = ds.getConnection();

			preparedStatement = connection.prepareStatement(selectSQL);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				VenditaBean bean = new VenditaBean();

				bean.setIdVendita(rs.getInt("idVendita"));
				bean.setNomeAmministratore(rs.getString("nomeAmministratore"));
				bean.setSconto(rs.getDouble("sconto"));
				bean.setPrezzo(rs.getDouble("prezzo"));
				bean.setIva(rs.getDouble("iva"));
				bean.setPezzi(rs.getInt("pezzi"));
				bean.setIdScheda(rs.getString("idScheda"));
				bean.setNomeCustom(rs.getString("nomeCustom"));

				products.add(bean);
			}
		
		}
		finally {
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
	
	/***************************************************************************************** */

	@Override
	public boolean doUpdatePz(int idVendita, int pezzi) throws SQLException {
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		int result = 0;

		String updateSQL = "UPDATE " + VenditaModelDS.TABLE_NAME + " SET pezzi = ? WHERE idVendita = ?";

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(updateSQL);
			preparedStatement.setInt(1, pezzi);
			preparedStatement.setInt(2, idVendita);

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

}
