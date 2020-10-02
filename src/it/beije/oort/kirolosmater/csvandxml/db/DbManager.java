package it.beije.oort.kirolosmater.csvandxml.db;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;

import it.beije.oort.db.DBManager;
import it.beije.oort.kirolosmater.csvandxml.*;
import it.beije.oort.kirolosmater.rubrica.Contact;

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
		String menuThirdLine = "Per inserire un contatto inserisci 3: ";
		String menuFourthLine = "Per rimuovere un contatto inserisci 4:";
		String menuFifthLine = "Per esportare una lista di contatti in un file .csv premi 5: ";
		System.out.println(menuFirstLine);
		System.out.println(menuSecondLine);
		System.out.println(menuThirdLine);
		System.out.println(menuFourthLine);
		System.out.println(menuFifthLine);
		String lineFromInput = inputFromUser.next();
		int numberFromInput = Integer.parseInt(lineFromInput);
		switch (numberFromInput) {
		case 1: searchRecord();
				break;
		case 2: modifyRecordById();
				break;
		case 3: insertContactByCmd();
				break;
		case 4: removeRecordById();
				break;
		case 5: exportListFromMenu();
				break;

		default: System.out.println("hai inserito un numero non valido");
			break;
		}
	}
	
	public static void searchRecord () {
		String firstLine = "Per cercare un contatto in base al suo id inserisci 1: ";
		String secondLine = "Per cercare un contatto in base alla sua iniziale inserisci 2: ";
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
	
	public static Contact contactFromRecordByIdFromInput (int id) {
		
		Contact contact = readRecordFromDb(id);
		System.out.println("hai inserito questo id: " + id);
		System.out.println("questo è il record: " + contact);
		return contact;
	}
	
	public static void readRecordByStringFromInput () {
		//parameter & initial string request
		System.out.println("Inserisci il parametro da analizzare: ");
		Scanner inputFromUser = new Scanner(System.in);
		String parameter = inputFromUser.nextLine();
		String stringRequest = "Inserisci la stringa iniziale: ";
		System.out.println(stringRequest);
		String lineFromInput = inputFromUser.nextLine();		
		Contact contact = new Contact();
		Connection connection = null;
		Statement statement = null;
		ResultSet rs = null;
		try {
			connection = getMySqlConnection(DB_URL, DB_USER, DB_PASSWORD);
//			System.out.println("connection is open? " + !connection.isClosed());
			statement = connection.createStatement();
			rs = statement.executeQuery("SELECT * FROM rubrica r where " + parameter + " LIKE '" + lineFromInput + "%" + "'");
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
	
	public static void modifyRecordById () {
		String showRecordById = "Per modificare un contatto inserisci il suo id: ";
		System.out.println(showRecordById);
		Scanner inputFromUser = new Scanner(System.in);
		String idFromInput = inputFromUser.nextLine();
		int id = Integer.parseInt(idFromInput);
		Contact contact = contactFromRecordByIdFromInput(id);
		String parameterRequest = "Inserisci il parametro da modificare: ";
		System.out.println(parameterRequest);
		String parameter = inputFromUser.nextLine();
		String parameterValue = "Inserisci il valore ";
		System.out.println(parameterValue);
		String value = inputFromUser.nextLine();
		String query = "UPDATE rubrica r SET " + parameter + " = '" + value + "' WHERE id = '" + idFromInput + "'";
		System.out.println(query);
		Connection connection = null;
		Statement statement = null;
		boolean rs;
		try {
			connection = getMySqlConnection(DB_URL, DB_USER, DB_PASSWORD);
//			System.out.println("connection is open? " + !connection.isClosed());
			statement = connection.createStatement();
			rs = statement.execute(query);
//			while (rs.next()) {
//				contact.setName(rs.getString("nome"));
//				contact.setSurname(rs.getString("cognome"));
//				contact.setMobile(rs.getString("telefono"));
//				contact.setEmail(rs.getString("email"));
//			}
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
	
	public static void insertContactByCmd () {
		System.out.println("Inserisci nome, cognome, telefono, email in questo ordine");
		Scanner myObj = new Scanner(System.in);
		String name = myObj.nextLine();
		String surname = myObj.nextLine();
		String mobile = myObj.nextLine();
		String email = myObj.nextLine();
		Contact contact = new Contact();
		contact.setName(name);
		contact.setSurname(surname);
		contact.setMobile(mobile);
		contact.setEmail(email);
		insertContact(contact);
	}
	
	public static void removeRecordById() {
		System.out.println("Per rimuovere un contatto inserisci il suo id: ");
		Scanner inputFromUser = new Scanner(System.in);
		String idFromInput = inputFromUser.nextLine();
		int id = Integer.parseInt(idFromInput);
		readRecordByIdFromInput();
		System.out.println("Sei sicuro di voler rimuovere il contatto? SI | NO");
		String confirm = inputFromUser.nextLine();
		if(confirm.equalsIgnoreCase("SI")) {
			String query = "DELETE FROM rubrica WHERE id = '" + idFromInput + "'";
			System.out.println(query);
			Connection connection = null;
			Statement statement = null;
			boolean rs;
			try {
				connection = getMySqlConnection(DB_URL, DB_USER, DB_PASSWORD);
//				System.out.println("connection is open? " + !connection.isClosed());
				statement = connection.createStatement();
				rs = statement.execute(query);
//				while (rs.next()) {
//					contact.setName(rs.getString("nome"));
//					contact.setSurname(rs.getString("cognome"));
//					contact.setMobile(rs.getString("telefono"));
//					contact.setEmail(rs.getString("email"));
//				}
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
	}
	
	public static void exportListFromMenu () {
		System.out.println("Inserisci la path del file: ");
		Scanner inputFromUser = new Scanner(System.in);
		String path = inputFromUser.nextLine();
		System.out.println("Inserisci il primo id del file: ");
		String firstId = inputFromUser.nextLine();
		int id1 = Integer.parseInt(firstId);
		System.out.println("Inserisci l'ultimo (da escludere) id del file: ");
		String lastId = inputFromUser.nextLine();
		int idfinal = Integer.parseInt(lastId);
		exportListToCsv(readRecords(id1, idfinal), path);
	}
}
