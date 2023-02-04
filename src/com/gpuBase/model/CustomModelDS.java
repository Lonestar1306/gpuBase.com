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

public class CustomModelDS implements CustomModel{

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

	private static final String TABLE_NAME = "Custom";
	/****************************************************************/
	@Override
	public void doSave(CustomBean custom) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String insertSQL = "INSERT INTO " + CustomModelDS.TABLE_NAME
				+ " (idCustom, idScheda, profondita, larghezza, lunghezza, peso, nomeCustom, azienda, risoluzioneMax, porteDP, porteVGA, porteDVI, porteHDMI)"
				+ " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);
			
			
			preparedStatement.setInt(1, custom.getIdCustom());
			preparedStatement.setString(2, custom.getIdScheda());
			preparedStatement.setDouble(3, custom.getProfondita());
			preparedStatement.setDouble(4, custom.getLarghezza());
			preparedStatement.setDouble(5, custom.getLunghezza());
			preparedStatement.setDouble(6, custom.getPeso());
			preparedStatement.setString(7, custom.getNomeCustom());
			preparedStatement.setString(8, custom.getAzienda());
			preparedStatement.setString(9, custom.getRisoluzioneMax());
			preparedStatement.setInt(10, custom.getPorteDP());
			preparedStatement.setInt(11, custom.getPorteVGA());
			preparedStatement.setInt(12, custom.getPorteDVI());
			preparedStatement.setInt(13, custom.getPorteHDMI());
			
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
	public boolean doDelete(int idCustom) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		int result = 0;

		String deleteSQL = "DELETE FROM " + CustomModelDS.TABLE_NAME + " WHERE idCustom = ?";

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(deleteSQL);
			preparedStatement.setInt(1, idCustom);

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
	public CustomBean doRetrieveByKey(int idCustom) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		CustomBean bean = new CustomBean();

		String selectSQL = "SELECT * FROM " + CustomModelDS.TABLE_NAME + " WHERE idCustom = ?";

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setInt(1, idCustom);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				bean.setIdCustom(rs.getInt("idCustom"));
				bean.setIdScheda(rs.getString("idScheda"));
				bean.setProfondita(rs.getDouble("profondita"));
				bean.setLarghezza(rs.getDouble("larghezza"));
				bean.setLunghezza(rs.getDouble("lunghezza"));
				bean.setPeso(rs.getDouble("peso"));
				bean.setNomeCustom(rs.getString("nomeCustom"));
				bean.setAzienda(rs.getString("azienda"));
				bean.setRisoluzioneMax(rs.getString("risoluzioneMax"));
				bean.setPorteDP(rs.getInt("porteDP"));
				bean.setPorteVGA(rs.getInt("porteVGA"));
				bean.setPorteDVI(rs.getInt("porteDVI"));
				bean.setPorteHDMI(rs.getInt("porteHDMI"));
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
	public Collection<CustomBean> doRetrieveAll(String orderParameter) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		Collection<CustomBean> products = new LinkedList<CustomBean>();

		String selectSQL = "SELECT * FROM " + CustomModelDS.TABLE_NAME;

		if (orderParameter != null && !orderParameter.equals("")) {
			selectSQL += " ORDER BY " + orderParameter;
		}

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				CustomBean bean = new CustomBean();

				bean.setIdCustom(rs.getInt("idCustom"));
				bean.setIdScheda(rs.getString("idScheda"));
				bean.setProfondita(rs.getDouble("profondita"));
				bean.setLarghezza(rs.getDouble("larghezza"));
				bean.setLunghezza(rs.getDouble("lunghezza"));
				bean.setPeso(rs.getDouble("peso"));
				bean.setNomeCustom(rs.getString("nomeCustom"));
				bean.setAzienda(rs.getString("azienda"));
				bean.setRisoluzioneMax(rs.getString("risoluzioneMax"));
				bean.setPorteDP(rs.getInt("porteDP"));
				bean.setPorteVGA(rs.getInt("porteVGA"));
				bean.setPorteDVI(rs.getInt("porteDVI"));
				bean.setPorteHDMI(rs.getInt("porteHDMI"));
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

}



