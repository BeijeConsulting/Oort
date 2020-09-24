package it.beije.oort.kirolosmater.csvandxml.db;

import java.io.File;
import java.sql.Connection;

import it.beije.oort.db.DBManager;
import it.beije.oort.kirolosmater.csvandxml.*;
import phonebookgenerator.Contact;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalTime;
import java.util.List;

public class DbManager {
	public static final String DB_USER = "root";
	public static final String DB_PASSWORD = "Beije03";
	public static final String DB_URL = "jdbc:mysql://localhost:3306/rubrica?serverTimezone=CET";
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("Start: " + LocalTime.now());
//		Connection connection = getMySqlConnection(DB_URL, DB_USER, DB_PASSWORD);
//		System.out.println("connection is open? " + !connection.isClosed());
//		List<Contact> contacts = CsvToXml.getContactListFromPath("/temp/records.csv");
//		System.out.println(contacts.get(0).getName());
//		insertContactList(contacts);
		Contact contactFromDb = readRecord(1);
		System.out.println(contactFromDb);
		
		
		System.out.println("Finish: " + LocalTime.now());
	}

	public static Connection getMySqlConnection(String url, String user, String password) throws SQLException, ClassNotFoundException {
		Class.forName("com.mysql.cj.jdbc.Driver");

		Connection connection = DriverManager.getConnection(url, user, password);
		
		return connection;
	}
	
	public static void insertContact (Contact contact) {
		Connection connection = null;
		Statement statement = null;
		try {
			connection = getMySqlConnection(DB_URL, DB_USER, DB_PASSWORD);
//			System.out.println("connection is open? " + !connection.isClosed());
			statement = connection.createStatement();
			statement.execute("INSERT INTO rubrica (nome, cognome, telefono, email) VALUES ('"+contact.getName()+"', '"+contact.getSurname()+"', '"+contact.getMobile()+"' , '"+contact.getEmail()+"')");
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				statement.close();
				connection.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}
	
	public static void insertContactList (List<Contact> contacts ) {
		for (Contact contact : contacts) {
			insertContact(contact);
		}
	}
	
	public static Contact readRecord (int id) {
		Contact contact = new Contact();
		Connection connection = null;
		Statement statement = null;
		ResultSet rs = null;
		try {
			connection = getMySqlConnection(DB_URL, DB_USER, DB_PASSWORD);
//			System.out.println("connection is open? " + !connection.isClosed());
			statement = connection.createStatement();
			rs = statement.executeQuery("SELECT * FROM rubrica r where id = '" + id + "'");
			while (rs.next()) {
				contact.setName(rs.getString("nome"));
				contact.setSurname(rs.getString("cognome"));
				contact.setMobile(rs.getString("telefono"));
				contact.setEmail(rs.getString("email"));
			}
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				statement.close();
				connection.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return contact;
	}
	
}
