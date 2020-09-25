package it.beije.oort.kirolosmater.csvandxml.db;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;

import it.beije.oort.db.DBManager;
import it.beije.oort.kirolosmater.csvandxml.*;
import phonebookgenerator.Contact;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.mysql.cj.ParseInfo;

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
//		Contact contactFromDb = readRecord(1);
//		List<Contact> contactsFromDb = readRecords(1, 50);
//		exportListToCsv(contactsFromDb, "/temp/contactsListFromDb.csv");
		printMenu();
		
		
//		System.out.println(contactsFromDb);
		
		
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
	
	public static Contact readRecordFromDb (int id) {
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
	
	public static List<Contact> readRecords (int first, int last) {
		List<Contact> contacts = new ArrayList<Contact>();
		for(int i = first; i < last; i++) {
			contacts.add(readRecordFromDb(i));
		}
		return contacts;
	}
	
	public static void exportListToCsv (List<Contact> list, String path) {
		File fileCsv = new File(path);
		try {
			FileWriter writer = new FileWriter(fileCsv);
			writer.write("NOME;COGNOME;EMAIL;TELEFONO\n");
			for (Contact contact : list ) {
				writer.write(contact.toRow());
			}
			System.out.println("Done records: " + LocalTime.now());
			writer.flush();
			writer.close();
			System.out.println("Done file: " + LocalTime.now());

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	public static void printMenu () {
		Scanner inputFromUser = new Scanner(System.in);
		String menuFirstLine = "Per visualizzare un contatto inserisci 1: ";
		String menuSecondLine = "Per modificare un contatto inserisci 2: ";
		System.out.println(menuFirstLine);
		String lineFromInput = inputFromUser.next();
		int numberFromInput = Integer.parseInt(lineFromInput);
		switch (numberFromInput) {
		case 1: searchRecord();
				break;

		default: System.out.println("hai inserito un numero non valido");
			break;
		}
	}
	
	public static void searchRecord () {
		String firstLine = "Per cercare un contatto in base al suo id inserisci 1: ";
		String secondLine = "Per cercare un contatto in base alla sua iniziale inserisci 2 ";
		System.out.println(firstLine);
		System.out.println(secondLine);
		
		Scanner inputFromUser = new Scanner(System.in);
		String lineFromInput = inputFromUser.next();
		int numberFromInput = Integer.parseInt(lineFromInput);
		switch (numberFromInput) {
		case 1: readRecordByIdFromInput();			
			break;
		case 2: readRecordByStringFromInput();
			break;

		default: System.out.println("hai inserito un numero non valido");
			break;
		}
		
	}
	
	public static void readRecordByIdFromInput () {
		String showRecordById = "Per visualizzare un contatto inserisci il suo id: ";
		System.out.println(showRecordById);
		Scanner inputFromUser = new Scanner(System.in);
		String lineFromInput = inputFromUser.next();
		int id = Integer.parseInt(lineFromInput);
		System.out.println("hai inserito questo id: " + id);
		System.out.println("questo è il record: " + readRecordFromDb(id));
	}
	
	public static void readRecordByStringFromInput () {
		String showRecordById = "Inserisci la stringa iniziale: ";
		System.out.println(showRecordById);
		Scanner inputFromUser = new Scanner(System.in);
		String lineFromInput = inputFromUser.nextLine();
		Contact contact = new Contact();
		Connection connection = null;
		Statement statement = null;
		ResultSet rs = null;
		try {
			connection = getMySqlConnection(DB_URL, DB_USER, DB_PASSWORD);
//			System.out.println("connection is open? " + !connection.isClosed());
			statement = connection.createStatement();
			rs = statement.executeQuery("SELECT * FROM rubrica r where cognome LIKE '" + lineFromInput + "%" + "'");
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
		System.out.println(contact);
	}
}
