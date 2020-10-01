package it.beije.oort.kirolosmater.biblioteca;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import static it.beije.oort.kirolosmater.biblioteca.LibraryManager.libraryPersistenceUnit;
import java.util.Scanner;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;

import it.beije.oort.rubrica.Contatto;
import it.beije.oort.rubrica.HybSessionFactory;

public class MetodiAutore {
	
	static EntityManager entityManager = JPAEntityManagerSingleton.getEntityManager(libraryPersistenceUnit);
	public static void menuAutore () {
		Scanner inputFromUser = new Scanner(System.in);
		System.out.println("inserisci 1 | Per visualizzare un autore in base al suo id");
		System.out.println("inserisci 2 | Per visualizzare più autori in base ad un parametro differente");
		System.out.println("inserisci 3 | Per modificare i parametri di un autore");
		System.out.println("inserisci 4 | Per inserire un nuovo autore");
		System.out.println("inserisci 5 | Per rimuovere un autore");
		System.out.println("inserisci 6 | Per esportare una lista di autori");
		String lineFromInput = inputFromUser.nextLine();
		int numberFromInput = Integer.parseInt(lineFromInput);
		switch (numberFromInput) {
		case 1: visualizzaAutoreById();	
			break;
			
		case 2: readRecordByStringFromInput();		
			break;
		case 4: inserimentoAutore();
			break;

		default: System.out.println("Hai inserito un valore non valido");
			break;
		}
		
	}
	
	public static void visualizzaAutoreById () {
		String showRecordById = "Per visualizzare un autore inserisci il suo id: ";
		System.out.println(showRecordById);
		Scanner inputFromUser = new Scanner(System.in);
		String lineFromInput = inputFromUser.next();
		int id = Integer.parseInt(lineFromInput);
		System.out.println("hai inserito questo id: " + id);
		System.out.println("questo è il record: ");
		Autore autore = readRecordFromDb(id);
		System.out.println("id : " + autore.getId());			
		System.out.println("cognome : " + autore.getCognome());
		System.out.println("nome : " + autore.getNome());
		System.out.println("data_nascita : " + autore.getData_nascita());
		System.out.println("data_morte : " + autore.getData_morte());
		System.out.println("biografia : " + autore.getBiografia());
	}
	
	public static Autore readRecordFromDb(int id) { 		
		Autore autore = entityManager.find(Autore.class, id);
		return autore;
	}
	
	public static void readRecordByStringFromInput () {
		Scanner inputFromUser = new Scanner(System.in);
		System.out.println("Inserisci il parametro da analizzare: ");
		String parameter = inputFromUser.nextLine();
		String stringRequest = "Inserisci la stringa iniziale: ";
		System.out.println(stringRequest);
		String lineFromInput = inputFromUser.nextLine();
		String jpql = "SELECT c FROM Autore as c WHERE " + parameter + " LIKE '" + lineFromInput + "%'";
		Query query = (Query) entityManager.createQuery(jpql);
		List<Autore> autori = query.getResultList();
		for (Autore autore : autori) {
			System.out.println("id : " + autore.getId());			
			System.out.println("cognome : " + autore.getCognome());
			System.out.println("nome : " + autore.getNome());
			System.out.println("data_nascita : " + autore.getData_nascita());
			System.out.println("data_morte : " + autore.getData_morte());
			System.out.println("biografia : " + autore.getBiografia());
		}
	}
	
	public static void inserimentoAutore () {
		//SCANNER
		Scanner myObj = new Scanner(System.in);
		System.out.println("Inserisci nome");
		String nome = myObj.nextLine();
		System.out.println("Inserisci cognome");
		String cognome = myObj.nextLine();
		System.out.println("Inserisci data di nascita in questo formato YYYY-MM-DD");
		String data_nascita =  myObj.nextLine();
		System.out.println("Inserisci data di morte in questo formato YYYY-MM-DD");
		String data_morte =  myObj.nextLine();
		System.out.println("Inserisci biografia");
		String biografia = myObj.nextLine();
		//INSERT
		Autore autore = new Autore();
		autore.setNome(nome);
		autore.setCognome(cognome);
		autore.setData_nascita(dateFromString(data_nascita));
		autore.setData_morte(dateFromString(data_morte));
		autore.setBiografia(biografia);
		entityManager.getTransaction().begin();
		entityManager.persist(autore);
		entityManager.getTransaction().commit();
		entityManager.close();
	}
	
	public static LocalDate dateFromString (String str) {
		DateTimeFormatter f = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate date = LocalDate.parse(str, f);
		return date;
	}
}
