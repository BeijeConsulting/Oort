package it.beije.oort.gregori.parser;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import it.beije.oort.gregori.rubrica.db.DBManager;
import it.beije.oort.rubrica.Contatto;

public class ReaderDb {
	
	public static List<Contatto> readContatti() {
		List<Contatto> contatti = new ArrayList<Contatto>();
		
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			connection = DBManager.getMySqlConnection(DBManager.DB_URL, DBManager.DB_USER, DBManager.DB_PASSWORD);
			System.out.println("connection is open? " + !connection.isClosed());
			
			ps = connection.prepareStatement("SELECT cognome, nome, telefono, email FROM rubrica.rubrica");
			
			rs = ps.executeQuery();
			
			while (rs.next()) {
				Contatto c = new Contatto();
				c.setCognome(rs.getString("cognome"));
				c.setNome(rs.getString("nome"));
				c.setTelefono(rs.getString("telefono"));
				c.setEmail(rs.getString("email"));
				contatti.add(c);
			}
		} catch (SQLException sqlException) {
			sqlException.printStackTrace();
		} catch (ClassNotFoundException cnfEx) {
			cnfEx.printStackTrace();
		} finally {
			try {
				rs.close();
				ps.close();
				connection.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return contatti;
	}
	
//	private static Contatto select() {
//		Connection connection = null;
//		PreparedStatement ps = null;
//		ResultSet rs = null;
//		
//		try {
//			connection = DBManager.getMySqlConnection(DBManager.DB_URL, DBManager.DB_USER, DBManager.DB_PASSWORD);
//			System.out.println("connection is open? " + !connection.isClosed());
//			
//			ps = connection.prepareStatement("SELECT cognome, nome, telefono, email FROM rubrica.rubrica");
//			
//			rs = ps.executeQuery();
//			
//			while (rs.next()) {
//				Contatto c = new Contatto();
//				c.setCognome(rs.getString("cognome"));
//				c.setNome(rs.getString("nome"));
//				c.setTelefono(rs.getString("telefono"));
//				c.setEmail(rs.getString("email"));
//			}
//		} catch (SQLException sqlException) {
//			sqlException.printStackTrace();
//		} catch (ClassNotFoundException cnfEx) {
//			cnfEx.printStackTrace();
//		} finally {
//			try {
//				rs.close();
//				ps.close();
//				connection.close();
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		}
//	
//		return c;
//	}
}
