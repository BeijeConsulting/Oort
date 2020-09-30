package it.beije.oort.kirolosmater.biblioteca;

import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.query.Query;

import it.beije.oort.rubrica.Contatto;
import it.beije.oort.rubrica.HybSessionFactory;

public class MetodiAutore {
	
	public void menuAutore () {
		Scanner inputFromUser = new Scanner(System.in);
		System.out.println("inserisci 1 | Per visualizzare un autore in base al suo id");
		System.out.println("inserisci 2 | Per visualizzare più autori in base ad un parametro differente");
		System.out.println("inserisci 3 | Per modificare i parametri di un autore");
		System.out.println("inserisci 4 | Per inserire un nuovo autore");
		System.out.println("inserisci 5 | Per rimuovere un autore");
		System.out.println("inserisci 6 | Per esportare una lista di autori");
		String lineFromInput = inputFromUser.nextLine();
		int numberFromInput = Integer.parseInt(lineFromInput);
		
	}
	
	public void visualizzaAutoreById () {
		String showRecordById = "Per visualizzare un autore inserisci il suo id: ";
		System.out.println(showRecordById);
		Scanner inputFromUser = new Scanner(System.in);
		String lineFromInput = inputFromUser.next();
		int id = Integer.parseInt(lineFromInput);
		System.out.println("hai inserito questo id: " + id);
		System.out.println("questo è il record: ");
		readRecordFromDb(id);
	}
	
	public static Autore readRecordFromDb(int id) {
		//apro sessione
		Session session = HybSessionFactory.openSession();
		System.out.println("session is open? " + session.isOpen());
		String hql = "SELECT c FROM Autore as c WHERE id = " + id ;
		Autore autoreOutput = new Autore();
		Query<Autore> query = session.createQuery(hql);
//		System.out.println(query.list().size());
		for (Autore autore : query.list()) {
			System.out.println("id : " + autore.getId());			
			System.out.println("cognome : " + autore.getCognome());
			System.out.println("nome : " + autore.getNome());
			System.out.println("data_nascita : " + autore.getData_nascita());
			System.out.println("data_morte : " + autore.getData_morte());
			System.out.println("biografia: " + autore.getBiografia());
			autoreOutput = autore;
		}
		//chiudo sessione
		session.close();
		return autoreOutput;
	}
}
