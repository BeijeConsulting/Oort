package it.beije.oort.bru.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import it.beije.oort.bru.db.rubrica.Contatto;

public class DBUtilities {
	private static Connection getConnection() {
		Connection connection = null;
		try {
			connection = DBManager.getMySqlConnection(DBManager.DB_URL, DBManager.DB_USER, DBManager.DB_PASSWORD);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return connection;
	}
	
	public static void insertRecord(String cognome, String nome, String telefono, String email) {
		try (Connection connection = getConnection();
			PreparedStatement ps = connection.prepareStatement("INSERT INTO rubrica (cognome, nome, telefono, email) values(?, ?, ?, ?)");) {
			ps.setString(1, cognome);
			ps.setString(2, nome);
			ps.setString(3, telefono);
			ps.setString(4, email);
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static List<Contatto> searchContact(String column, String value) {
		List<Contatto> contacts = new ArrayList<Contatto>();
		try (Connection connection = getConnection();
			PreparedStatement ps = connection.prepareStatement("SELECT * FROM rubrica where " + column + " = ?");) {
			ps.setString(1, value);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Contatto contact = new Contatto();
				contact.setNome(rs.getString("nome"));
				contact.setCognome(rs.getString("cognome"));
				contact.setTelefono(rs.getString("telefono"));
				contact.setEmail(rs.getString("email"));
				contacts.add(contact);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return contacts;
	}
	
	public static Contatto exportContact(List<Contatto> rubrica, int id) {
		Contatto contact = new Contatto();
		try (Connection connection = getConnection();
			PreparedStatement ps = connection.prepareStatement("SELECT * FROM rubrica where id = ?");) {
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				contact.setNome(rs.getString("nome"));
				contact.setCognome(rs.getString("cognome"));
				contact.setTelefono(rs.getString("telefono"));
				contact.setEmail(rs.getString("email"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return contact;
	}
	
	public static void editContact(String paramMod, String newValue, int id) {
		try (Connection connection = getConnection();
			PreparedStatement ps = connection.prepareStatement("UPDATE rubrica set " + paramMod + " = ? where id = ?");) {
			ps.setString(1, newValue);
			ps.setInt(2, id);
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void deleteContact(int id) {
		try (Connection connection = getConnection();
			PreparedStatement ps = connection.prepareStatement("DELETE from rubrica where id = ?");) {
			ps.setInt(1, id);
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void exportDB(List<Contatto> contacts) {
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			connection = DBManager.getMySqlConnection(DBManager.DB_URL, DBManager.DB_USER, DBManager.DB_PASSWORD);
			
			ps = connection.prepareStatement("SELECT * FROM rubrica");
			rs = ps.executeQuery();
				while (rs.next()) {
					Contatto contatto = new Contatto();
					contatto.setCognome(rs.getString("cognome"));
					contatto.setNome(rs.getString("nome"));
					contatto.setTelefono(rs.getString("telefono"));
					contatto.setEmail(rs.getString("email"));
					contacts.add(contatto);
				}
		} catch (SQLException sqlException) {
			sqlException.printStackTrace();
		} catch (ClassNotFoundException cnfEx) {
			cnfEx.printStackTrace();
		} finally {
			try {
				ps.close();
				connection.close();
			} catch (SQLException sqlex) {
				sqlex.printStackTrace();
			}
		}
	}
}
