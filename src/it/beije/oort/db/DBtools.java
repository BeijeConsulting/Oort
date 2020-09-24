package it.beije.oort.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBtools {

	public static void select(String editore) {
		Connection connection = null;
		Statement statement = null;
		ResultSet rs = null;
		
		try {
			connection = DBManager.getMySqlConnection(DBManager.DB_URL, DBManager.DB_USER, DBManager.DB_PASSWORD);
			System.out.println("connection is open? " + !connection.isClosed());
			
			statement = connection.createStatement();
			
			rs = statement.executeQuery("SELECT * FROM libri l where editore = '" + editore + "'");
//			rs = statement.executeQuery("SELECT * FROM libri l, autore a where l.id_autore = a.id");
			
			while (rs.next()) {
				System.out.println("titolo : " + rs.getString("titolo"));
				System.out.println("desc : " + rs.getString("descrizione"));
				System.out.println("editore : " + rs.getString("editore"));
				System.out.println("anno : " + rs.getString("anno"));
				//System.out.println("autore : " + rs.getString("nome"));
			}
		} catch (SQLException sqlException) {
			sqlException.printStackTrace();
		} catch (ClassNotFoundException cnfEx) {
			cnfEx.printStackTrace();
//		} catch (Exception e) {
//			e.printStackTrace();
		} finally {
			try {
				rs.close();
				statement.close();
				connection.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	
	}
	
	public static void preparedSelect(String editore) {
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			connection = DBManager.getMySqlConnection(DBManager.DB_URL, DBManager.DB_USER, DBManager.DB_PASSWORD);
			System.out.println("connection is open? " + !connection.isClosed());
			
			ps = connection.prepareStatement("SELECT * FROM libri l where editore = ?");
			ps.setString(1, editore);
			
			rs = ps.executeQuery();
			
			while (rs.next()) {
				System.out.println("titolo : " + rs.getString("titolo"));
				System.out.println("desc : " + rs.getString("descrizione"));
				System.out.println("editore : " + rs.getString("editore"));
				System.out.println("anno : " + rs.getString("anno"));
				//System.out.println("autore : " + rs.getString("nome"));
			}
		} catch (SQLException sqlException) {
			sqlException.printStackTrace();
		} catch (ClassNotFoundException cnfEx) {
			cnfEx.printStackTrace();
//		} catch (Exception e) {
//			e.printStackTrace();
		} finally {
			try {
				rs.close();
				ps.close();
				connection.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	
	}

	public static void insert(String titolo, String descrizione, String editore) {
		Connection connection = null;
		Statement statement = null;
		
		try {
			connection = DBManager.getMySqlConnection(DBManager.DB_URL, DBManager.DB_USER, DBManager.DB_PASSWORD);
			System.out.println("connection is open? " + !connection.isClosed());
			
			statement = connection.createStatement();
			
			statement.execute("INSERT INTO libri (titolo, descrizione, editore) VALUES ('"+titolo+"', '"+descrizione+"', '"+editore+"')");
//			statement.execute("INSERT INTO libri (titolo, descrizione, editore) VALUES ('OCA manual', 'testo certificazione', 'wiley')");
			
			System.out.println("insert record : " + statement.getUpdateCount());
			
		} catch (SQLException sqlException) {
			sqlException.printStackTrace();
		} catch (ClassNotFoundException cnfEx) {
			cnfEx.printStackTrace();
//		} catch (Exception e) {
//			e.printStackTrace();
		} finally {
			try {
				statement.close();
				connection.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public static void preparedInsert(String titolo, String descrizione, String editore) {
		Connection connection = null;
		PreparedStatement ps = null;
		
		try {
			connection = DBManager.getMySqlConnection(DBManager.DB_URL, DBManager.DB_USER, DBManager.DB_PASSWORD);
			System.out.println("connection is open? " + !connection.isClosed());
			
			ps = connection.prepareStatement("INSERT INTO libri (titolo, descrizione, editore) VALUES (?, ?, ?)");
			ps.setString(1, titolo);
			ps.setString(2, descrizione);
			ps.setString(3, editore);
			
			ps.execute();
			
			System.out.println("insert record : " + ps.getUpdateCount());
			
		} catch (SQLException sqlException) {
			sqlException.printStackTrace();
		} catch (ClassNotFoundException cnfEx) {
			cnfEx.printStackTrace();
//		} catch (Exception e) {
//			e.printStackTrace();
		} finally {
			try {
				ps.close();
				connection.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		preparedInsert("OCP", "manuale OCP", "mondadori");
		preparedSelect("mondadori");
	}

}
