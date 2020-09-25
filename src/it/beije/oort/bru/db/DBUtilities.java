package it.beije.oort.bru.db;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import it.beije.oort.files.CsvParser;
import it.beije.oort.files.XmlParser;
import it.beije.oort.files.Contatto;

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
	
	private static void insertRecord(String cognome, String nome, String telefono, String email, PreparedStatement ps) {
		try {
			ps.setString(1, cognome);
			ps.setString(2, nome);
			ps.setString(3, telefono);
			ps.setString(4, email);
			ps.execute();
		} catch (SQLException sqlException) {
			sqlException.printStackTrace();
		}
	}
	
	public static List<Contatto> searchContact(String column, String value) throws SQLException {
		List<Contatto> contacts = new ArrayList<Contatto>();
		Connection connection = getConnection();
		PreparedStatement ps = connection.prepareStatement("SELECT * FROM rubrica where " + column + " = ?");
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
		ps.close();
		connection.close();
		return contacts;
	}
	
	public static Contatto exportContact(List<Contatto> rubrica, int id) throws SQLException {
		Contatto contact = new Contatto();
		Connection connection = getConnection();
		PreparedStatement ps = connection.prepareStatement("SELECT * FROM rubrica where id = ?");
		ps.setInt(1, id);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			contact.setNome(rs.getString("nome"));
			contact.setCognome(rs.getString("cognome"));
			contact.setTelefono(rs.getString("telefono"));
			contact.setEmail(rs.getString("email"));
		}
		ps.close();
		connection.close();
		return contact;
	}
	
	public static void modifyContact(String paramMod, String newValue, int id) throws SQLException {
		Connection connection = getConnection();
		PreparedStatement ps = connection.prepareStatement("UPDATE rubrica set " + paramMod + " = ? where id = ?");
		ps.setString(1, newValue);
		ps.setInt(2, id);
		ps.execute();
		ps.close();
		connection.close();
	}
	
	public static void deleteContact(int id) throws SQLException {
		Connection connection = getConnection();
		PreparedStatement ps = connection.prepareStatement("DELETE from rubrica where id = ?");
		ps.setInt(1, id);
		ps.execute();
		ps.close();
		connection.close();
	}
	
	public static Map<Integer, List<Contatto>> layout(List<Contatto> contacts) {
		Map<Integer, List<Contatto>> pages = new HashMap<Integer, List<Contatto>>();
		int pageStart = 0;
		int pageEnd = 50;
		int end = (contacts.size()/50) + (contacts.size()%50);
		for (int i = 0; i < end; i++) {
			if (pageEnd > contacts.size()) {
				pageEnd = contacts.size();
			}
			pages.put(i, contacts.subList(pageStart, pageEnd));
			pageStart = pageEnd;
			pageEnd += 50;
		}
		return pages;
	}
	
	public static void loadCsv(File fileCsv) throws IOException, ClassNotFoundException, SQLException {
		List<Contatto> contacts = CsvParser.readContatti(fileCsv);
		Connection connection = getConnection();
		PreparedStatement ps = connection.prepareStatement("INSERT INTO rubrica (cognome, nome, telefono, email) VALUES (?, ?, ?, ?)");
		for (Contatto contact : contacts) {
			insertRecord(contact.getCognome(),contact.getNome(),contact.getTelefono(),contact.getEmail(),ps);
		}
		ps.close();
		connection.close();
	}
	
	public static void loadXml(File fileXml) throws IOException, ParserConfigurationException, SAXException, SQLException, ClassNotFoundException {
		List<Contatto> contacts = XmlParser.readContatti(fileXml);
		Connection connection = getConnection();
		PreparedStatement ps = connection.prepareStatement("INSERT INTO rubrica (cognome, nome, telefono, email) VALUES (?, ?, ?, ?)");
		for (Contatto contact : contacts) {
			insertRecord(contact.getCognome(),contact.getNome(),contact.getTelefono(),contact.getEmail(),ps);
		}
		ps.close();
		connection.close();
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
