package com.gpuBase.model;


import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class FotoModelDS implements FotoModel{



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

	private static final String TABLE_NAME = "Foto";
	/****************************************************************/
	
	@Override
	public void doSave(FotoBean foto) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String insertSQL = "INSERT INTO " + FotoModelDS.TABLE_NAME
				+ " ( idCustom, path, file)"
				+ " VALUES (?, ?, ?)";

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);
			
			
			preparedStatement.setInt(1, foto.getIdCustom());
			preparedStatement.setString(2, foto.getPath());
			preparedStatement.setBytes(3,foto.getFile());
		
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
	public boolean doDelete(String path) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		int result = 0;

		String deleteSQL = "DELETE FROM " + FotoModelDS.TABLE_NAME + " WHERE path = ?";

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(deleteSQL);
			preparedStatement.setString(1, path);

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
	public FotoBean doRetrieveByKey(String path) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		FotoBean bean = new FotoBean();

		String selectSQL = "SELECT * FROM " + FotoModelDS.TABLE_NAME + " WHERE path = ?";

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, path);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				bean.setIdCustom(rs.getInt("idCustom"));
				bean.setPath(rs.getString("path"));
				bean.setFile(rs.getBlob("file").getBytes(1, (int) rs.getBlob("file").length()));
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
	public Collection<FotoBean> doRetrieveAll(String orderParameter) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		Collection<FotoBean> products = new LinkedList<FotoBean>();

		String selectSQL = "SELECT * FROM " + FotoModelDS.TABLE_NAME;

		if (orderParameter != null && !orderParameter.equals("")) {
			selectSQL += " ORDER BY " + orderParameter;
		}

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				FotoBean bean = new FotoBean();
				
				bean.setIdCustom(rs.getInt("idCustom"));
				bean.setPath(rs.getString("path"));
				bean.setFile(rs.getBlob("file").getBytes(1, (int) rs.getBlob("file").length()));
				
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
	
	
	/********************************************************************************/
	@Override
	public  Collection<FotoBean> doRetrieveByCustom(int idCustom) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		Collection<FotoBean> foto = new LinkedList<FotoBean>();

		String selectSQL = "SELECT * FROM " + FotoModelDS.TABLE_NAME + " WHERE idCustom = ?";

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setInt(1, idCustom);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				FotoBean bean = new FotoBean();
				bean.setIdCustom(rs.getInt("idCustom"));
				bean.setPath(rs.getString("path"));
				Blob bl= rs.getBlob("file");
				long l= bl.length();//num di bytes
				bean.setFile(bl.getBytes(1,(int) l));
					//converto blob in array di bytes
				foto.add(bean);	
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
		return foto;
	}
	
	/*************************************************************************************/
	@Override
	public FotoBean doRetrieveOneByCustom(int idCustom) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		FotoBean foto = new FotoBean();

		String selectSQL = "SELECT * FROM " + FotoModelDS.TABLE_NAME + " WHERE idCustom = ?";

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setInt(1, idCustom);

			ResultSet rs = preparedStatement.executeQuery();

			if(rs.next())
			{
				foto.setIdCustom(rs.getInt("idCustom"));
				foto.setPath(rs.getString("path"));
				Blob bl= rs.getBlob("file");
				long l= bl.length();//num di bytes
				foto.setFile(bl.getBytes(1,(int) l));
					//converto blob in array di bytes
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
		return foto;
	}
	
	/******************************************/
	@Override
	public void doDeleteByCustom(int idVendita) throws SQLException {
		Collection<FotoBean> foto=doRetrieveByCustom(idVendita);
		for(FotoBean f:foto) {
			doDelete(f.getPath());//elimina tutte le foto di una certa custom
		}
		
	}
	
	/*******************************************/
	
	@Override
	public int getCountFoto(int idCustom) throws SQLException {
			
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		int count=0;

		String selectSQL = "SELECT * FROM " + FotoModelDS.TABLE_NAME + " WHERE idCustom = ?";

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setInt(1, idCustom);

			ResultSet rs = preparedStatement.executeQuery();

			while(rs.next())
			{
				count++;
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
		
		return count;
	}
	
	
	
	/**************************************/
	
	@Override
	public FotoBean doRetrieveOneByCustomFlag(int idCustom,int flagFoto) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		FotoBean foto = new FotoBean();

		String selectSQL = "SELECT * FROM " + FotoModelDS.TABLE_NAME + " WHERE idCustom = ?";

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setInt(1, idCustom);

			ResultSet rs = preparedStatement.executeQuery();
			
			int i=0;

			do
			{
				rs.next();
				i++;
				
			}while(i<flagFoto && !rs.isLast());
			
			if(rs.getInt("idCustom")!=idCustom  || i!=flagFoto) {
				//System.out.println("result non founded");
				return null;}
			
			foto.setIdCustom(rs.getInt("idCustom"));
			foto.setPath(rs.getString("path"));
			Blob bl= rs.getBlob("file");
			long l= bl.length();//num di bytes
			foto.setFile(bl.getBytes(1,(int) l));
				//converto blob in array di bytes

		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				if (connection != null)
					connection.close();
			}
		}
		return foto;
	}

}



