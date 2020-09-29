package it.beije.oort.kirolosmater.csvandxml.db;

import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import it.beije.oort.rubrica.*;

public class HibernateDbManager {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		HibernateDbManager hDBM = new HibernateDbManager();
		Session session = hDBM.hibernateSessionStarter();

	}
	
	public Session hibernateSessionStarter () {
		System.out.println("INIZIO");

		//inizializzo configurazione
		Configuration configuration = new Configuration();
		configuration = configuration.configure();
		
		//chiedo generatore di sessioni
		SessionFactory factory = configuration.buildSessionFactory();
		
		System.out.println("is open? " + factory.isOpen());
		
		//apro sessione
		Session session = factory.openSession();
		System.out.println("session is open? " + session.isOpen());
		return session;
	}
	
	public void printMenu (Session session) {
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
		case 1: searchRecord(session);
				break;
//		case 2: modifyRecordById();
//				break;
//		case 3: insertContactByCmd();
//				break;
//		case 4: removeRecordById();
//				break;
//		case 5: exportListFromMenu();
//				break;

		default: System.out.println("hai inserito un numero non valido");
			break;
		}
	}
	
	public void searchRecord (Session session) {
		String firstLine = "Per cercare un contatto in base al suo id inserisci 1: ";
		String secondLine = "Per cercare un contatto in base alla sua iniziale inserisci 2 ";
		System.out.println(firstLine);
		System.out.println(secondLine);
		
		Scanner inputFromUser = new Scanner(System.in);
		String lineFromInput = inputFromUser.next();
		int numberFromInput = Integer.parseInt(lineFromInput);
		switch (numberFromInput) {
		case 1: readRecordByIdFromInput(session);			
			break;
//		case 2: readRecordByStringFromInput();
//			break;

		default: System.out.println("hai inserito un numero non valido");
			break;
		}
		
	}
	
	public void readRecordByIdFromInput (Session session) {
		String showRecordById = "Per visualizzare un contatto inserisci il suo id: ";
		System.out.println(showRecordById);
		Scanner inputFromUser = new Scanner(System.in);
		String lineFromInput = inputFromUser.next();
		int id = Integer.parseInt(lineFromInput);
		System.out.println("hai inserito questo id: " + id);
		System.out.println("questo è il record: " + readRecordFromDb(id, session));
	}
	
	public Contatto readRecordFromDb(int id, Session session) {
		Contatto contatto = new Contatto();
		String hql = "SELECT c FROM Contatto as c WHERE cognome = 'rossi'";
		Query<Contatto> query = session.createQuery(hql);
		return contatto;
	}
}
