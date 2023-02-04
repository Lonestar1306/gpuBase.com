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

public class VenditaCustomSchedaModelDS implements VenditaCustomSchedaModel{
	
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

	private static final String T1 = "Vendita";
	private static final String T2 = "Custom";
	private static final String T3 = "Scheda";
	

	@Override
	public Collection<VenditaCustomSchedaBean> doRetrieveAll() throws SQLException {
		Collection<VenditaCustomSchedaBean> myCol=new LinkedList<VenditaCustomSchedaBean>(); 
		VenditaCustomSchedaBean bean = new VenditaCustomSchedaBean();
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String selectSQL = "SELECT * FROM (("+T1+" JOIN "+T2+" ON "+T1+".idCustom="+T2+".idCustom)"+" JOIN "+T3+" ON "+T2+".idScheda="+T3+".idScheda)";

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
		
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				
				bean.setIdCustom(rs.getInt("idCustom"));
				bean.setPrezzo(rs.getDouble("prezzo"));
				bean.setIdScheda(rs.getString("idScheda"));
				bean.setNomeCustom(rs.getString("nomeCustom"));
				bean.setRisoluzioneMax(rs.getString("risoluzioneMax"));
				bean.setDataUscita(rs.getDate("dataUscita"));
				bean.setNumeroCore(rs.getInt("numeroCore"));
				bean.setTDP(rs.getInt("TDP"));
				bean.setFrequenzaCore(rs.getInt("frequenzaCore"));
				bean.setFrequenzaMemoria(rs.getInt("frequenzaMemoria"));
				bean.setDimensioneMemoria(rs.getDouble("dimensioneMemoria"));
				
				myCol.add(bean);

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
		
		return myCol;
		
	}


	@Override
	public VenditaCustomSchedaBean doRetrieveByKey(int key) throws SQLException {
		 
		VenditaCustomSchedaBean bean = new VenditaCustomSchedaBean();
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String selectSQL = "SELECT * FROM  (SELECT * FROM  ((SELECT * FROM Custom WHERE idCustom= ? )AS Tbuf1 "
				+ "JOIN Vendita ON Tbuf1.idCustom=Vendita.idVendita))AS Tbuf2 JOIN Scheda ON Tbuf2.idScheda=Scheda.idScheda";
		

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setInt(1, key);
		
			ResultSet rs = preparedStatement.executeQuery();

			rs.next();
				
			double prezzo=rs.getDouble("prezzo");
			double iva=rs.getDouble("iva");
			double sconto=rs.getDouble("sconto");
			double totale=(prezzo+(prezzo/100*iva)-(prezzo/100*sconto));
			bean.setPrezzo(totale);
					
			bean.setIdCustom(rs.getInt("idCustom"));
			bean.setIdScheda(rs.getString("idScheda"));
			bean.setNomeCustom(rs.getString("nomeCustom"));
			bean.setRisoluzioneMax(rs.getString("risoluzioneMax"));
			bean.setDataUscita(rs.getDate("dataUscita"));
			bean.setNumeroCore(rs.getInt("numeroCore"));
			bean.setTDP(rs.getInt("TDP"));
			bean.setFrequenzaCore(rs.getInt("frequenzaCore"));
			bean.setFrequenzaMemoria(rs.getInt("frequenzaMemoria"));
			bean.setDimensioneMemoria(rs.getDouble("dimensioneMemoria"));

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
		
		return bean;
	}

}
