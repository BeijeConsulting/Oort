package it.beije.oort.kirolosmater.rubrica;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import it.beije.oort.rubrica.*;

public class HibernateDbManager {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		HibernateDbManager hDBM = new HibernateDbManager();
		//SessionFactory tramite singleton
		Session session = HybSessionFactory.openSession();
		session.close();
		hDBM.printMenu();
		

	}
	
	public void printMenu () {
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
	
	public void searchRecord () {
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
		System.out.println("questo è il record: ");
		readRecordFromDb(id);
	}
	
	public void readRecordByStringFromInput () {
		//apro sessione
		Session session = HybSessionFactory.openSession();
		System.out.println("session is open? " + session.isOpen());
		//parameter & initial string request
		System.out.println("Inserisci il parametro da analizzare: ");
		Scanner inputFromUser = new Scanner(System.in);
		String parameter = inputFromUser.nextLine();
		String stringRequest = "Inserisci la stringa iniziale: ";
		System.out.println(stringRequest);
		String lineFromInput = inputFromUser.nextLine();
		String hql = "SELECT c FROM Contatto as c WHERE " + parameter + " LIKE :epf";
		Query<Contatto> query = session.createQuery(hql)
			       .setParameter("epf", lineFromInput + "%");
//		System.out.println(query.list().size());
		for (Contatto contatto : query.list()) {
			System.out.println("id : " + contatto.getId());
			System.out.println("nome : " + contatto.getNome());
			System.out.println("cognome : " + contatto.getCognome());
			System.out.println("telefono : " + contatto.getTelefono());
			System.out.println("email : " + contatto.getEmail());
		}
		//chiudo sessione
		session.close();
	}
	
	public static Contatto readRecordFromDb(int id) {
		//apro sessione
		Session session = HybSessionFactory.openSession();
		System.out.println("session is open? " + session.isOpen());
		String hql = "SELECT c FROM Contatto as c WHERE id = " + id ;
		Contatto contattoOutput = new Contatto();
		Query<Contatto> query = session.createQuery(hql);
//		System.out.println(query.list().size());
		for (Contatto contatto : query.list()) {
			System.out.println("id : " + contatto.getId());
			System.out.println("nome : " + contatto.getNome());
			System.out.println("cognome : " + contatto.getCognome());
			System.out.println("telefono : " + contatto.getTelefono());
			System.out.println("email : " + contatto.getEmail());
			contattoOutput = contatto;
		}
		//chiudo sessione
		session.close();
		return contattoOutput;
	}
	
	public static void modifyRecordById () {
		//apro sessione
		Session session = HybSessionFactory.openSession();
		System.out.println("session is open? " + session.isOpen());
		//apro transazione
		Transaction transaction = session.beginTransaction();
		//SELECT contatto		
		System.out.println("Per modificare un contatto inserisci il suo id: ");
		Scanner inputFromUser = new Scanner(System.in);
		String idFromInput = inputFromUser.nextLine();
		int id = Integer.parseInt(idFromInput);
		Contatto contatto = session.get(Contatto.class, id);
		System.out.println("Hai selezionato questo contatto" + contatto);
//		Contatto contact = contactFromRecordByIdFromInput(id);
		// richiesta parametro da modificare e nuovo valore
		System.out.println("Inserisci il parametro da modificare: ");
		String parameter = inputFromUser.nextLine();
		String parameterValue = "Inserisci il valore ";
		System.out.println(parameterValue);
		String value = inputFromUser.nextLine();
		switch (parameter.toLowerCase()) {
		case "nome": contatto.setNome(value);			
			break;
		case "cognome": contatto.setCognome(value);	
			break;	
		case "telefono": contatto.setTelefono(value);			
			break;
		case "email": contatto.setEmail(value);		
			break;
		default:
			System.out.println("Hai inserito un parametro non presente");
			break;
		}
		
		//output nuovo contatto
		System.out.println(contatto);
		//conferma salvataggio
		System.out.println("Vuoi  confermare le modifiche? SI | NO");
		String confirmation = inputFromUser.nextLine();
		session.save(contatto);
		if(confirmation.equalsIgnoreCase("SI")) {
			//confermo aggiornamento su DB
			transaction.commit();
		} else {
			//annullo aggiornamento su DB
			transaction.rollback();
		}				
//		session.save(contatto);
		//chiudo sessione
		session.close();
	}
	
	public static Contatto contactFromRecordByIdFromInput (int id) {
		
		Contatto contact = readRecordFromDb(id);
		System.out.println("hai inserito questo id: " + id);
		System.out.println("questo è il record: " + contact);
		return contact;
	}
	
	public static void insertContactByCmd () {
		//apro sessione
		Session session = HybSessionFactory.openSession();
		System.out.println("session is open? " + session.isOpen());
		//apro transazione
		Transaction transaction = session.beginTransaction();
		//scanner
		System.out.println("Inserisci nome, cognome, telefono, email in questo ordine");
		Scanner myObj = new Scanner(System.in);
		String nome = myObj.nextLine();
		String cognome = myObj.nextLine();
		String telefono = myObj.nextLine();
		String email = myObj.nextLine();
		//Insert
		Contatto contatto = new Contatto();
//		contatto.setId(3);
		contatto.setNome(nome);
		contatto.setCognome(cognome);
		contatto.setEmail(email);
		contatto.setTelefono(telefono);
		session.save(contatto);
		//conferma salvataggio
		System.out.println("Vuoi  confermare le modifiche? SI | NO");
		String confirmation = myObj.nextLine();
		if(confirmation.equalsIgnoreCase("SI")) {
			//confermo aggiornamento su DB
			transaction.commit();
		} else {
			//annullo aggiornamento su DB
			transaction.rollback();
		}				
		
		//chiudo sessione
		session.close();
	}
	
	public static void removeRecordById() {
		//apro sessione
		Session session = HybSessionFactory.openSession();
		System.out.println("session is open? " + session.isOpen());
		//apro transazione
		Transaction transaction = session.beginTransaction();
		//scanner
		System.out.println("Per rimuovere un contatto inserisci il suo id: ");
		Scanner inputFromUser = new Scanner(System.in);
		String idFromInput = inputFromUser.nextLine();
		int id = Integer.parseInt(idFromInput);
		readRecordByIdFromInput();
		System.out.println("Sei sicuro di voler rimuovere il contatto? SI | NO");
		String confirm = inputFromUser.nextLine();
		if(confirm.equalsIgnoreCase("SI")) {
			String hql = "DELETE FROM Contatto WHERE id = '" + idFromInput + "'";
			Query query = session.createQuery(hql);
			query.executeUpdate();
			//confermo aggiornamento su DB
			transaction.commit();
		} else {
			//annullo aggiornamento su DB
			transaction.rollback();
		}
		//chiudo sessione
		session.close();
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
	public static List<Contatto> readRecords (int first, int last) {
		List<Contatto> contacts = new ArrayList<Contatto>();
		for(int i = first; i < last; i++) {
			contacts.add(readRecordFromDb(i));
		}
		return contacts;
	}
	
	public static void exportListToCsv (List<Contatto> list, String path) {
		File fileCsv = new File(path);
		try {
			FileWriter writer = new FileWriter(fileCsv);
			writer.write("NOME;COGNOME;TELEFONO;EMAIL\n");
			for (Contatto contact : list ) {
				writer.write(contattoToCsv(contact));
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
	
	public static String contattoToCsv (Contatto contatto) {
		StringBuilder builder = new StringBuilder();
		builder.append(contatto.getNome()).append(";")
			.append(contatto.getCognome()).append(";")
			.append(contatto.getTelefono()).append(";")
			.append(contatto.getEmail()).append("\n");
		
		return builder.toString();
	}
}
