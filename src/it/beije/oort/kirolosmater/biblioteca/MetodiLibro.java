package it.beije.oort.kirolosmater.biblioteca;

import static it.beije.oort.kirolosmater.biblioteca.LibraryManager.libraryPersistenceUnit;

import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.hibernate.query.Query;

public class MetodiLibro {
	static EntityManager entityManager = JPAEntityManagerSingleton.getEntityManager(libraryPersistenceUnit);
	public static void menuLibro () {
		Scanner inputFromUser = new Scanner(System.in);
		System.out.println("inserisci 1 | Per visualizzare un libro in base al suo id");
		System.out.println("inserisci 2 | Per visualizzare più libri in base ad un parametro differente");
		System.out.println("inserisci 3 | Per modificare i parametri di un libro");
		System.out.println("inserisci 4 | Per inserire un nuovo libro");
		System.out.println("inserisci 5 | Per rimuovere un libro");
		System.out.println("inserisci 6 | Per esportare una lista di libri");
		String lineFromInput = inputFromUser.nextLine();
		int numberFromInput = Integer.parseInt(lineFromInput);
		switch (numberFromInput) {
		case 1: visualizzaLibroById();			
			break;
		case 2: readRecordByStringFromInput();		
			break;
		case 4: inserimentoLibro();
			break;

		default: System.out.println("Hai inserito un valore non valido");
			break;
		}
		
	}
	
	public static void visualizzaLibroById () {
		String showRecordById = "Per visualizzare un libro inserisci il suo id: ";
		System.out.println(showRecordById);
		Scanner inputFromUser = new Scanner(System.in);
		String lineFromInput = inputFromUser.next();
		int id = Integer.parseInt(lineFromInput);
		System.out.println("hai inserito questo id: " + id);
		System.out.println("questo è il record: ");
		Libro libro = readRecordFromDb(id);
		System.out.println("id : " + libro.getId());			
		System.out.println("titolo : " + libro.getTitolo());
		System.out.println("autore : " + libro.getAutore());
		System.out.println("editore : " + libro.getEditore());
		System.out.println("anno : " + libro.getAnno());
		System.out.println("descrizione : " + libro.getDescrizione());
		entityManager.close();
	}
	
	public static void readRecordByStringFromInput () {
		Scanner inputFromUser = new Scanner(System.in);
		System.out.println("Inserisci il parametro da analizzare: ");
		String parameter = inputFromUser.nextLine();
		String stringRequest = "Inserisci la stringa iniziale: ";
		System.out.println(stringRequest);
		String lineFromInput = inputFromUser.nextLine();
		String jpql = "SELECT c FROM Libro as c WHERE " + parameter + " LIKE '" + lineFromInput + "%'";
		Query query = (Query) entityManager.createQuery(jpql);
		List<Libro> libri = query.getResultList();
		for (Libro libro : libri) {
			System.out.println("id : " + libro.getId());			
			System.out.println("titolo : " + libro.getTitolo());
			System.out.println("autore : " + libro.getAutore());
			System.out.println("editore : " + libro.getEditore());
			System.out.println("anno : " + libro.getAnno());
			System.out.println("descrizione : " + libro.getDescrizione());
		}
	}
	
	public static Libro readRecordFromDb(int id) { 
		Libro libro = entityManager.find(Libro.class, id);
		return libro;
	}
	
	public static void inserimentoLibro () {
		//SCANNER
		Scanner myObj = new Scanner(System.in);
		System.out.println("Inserisci titolo");
		String titolo = myObj.nextLine();
		System.out.println("Inserisci id dell'autore del libro");
		List<Autore> autori = mostraTuttiAutori();
		String idautore = myObj.nextLine();
		int id = Integer.parseInt(idautore);
		Autore autoreSelezionato = autori.get(id-1);
//		System.out.println(autoreSelezionato.getId());
		String autoreLibro = autoreSelezionato.getNome() + " " + autoreSelezionato.getCognome();
		System.out.println("Inserisci editore");
		String editore =  myObj.nextLine();
		System.out.println("Inserisci anno di pubblicazione");
		int anno = Integer.parseInt(myObj.nextLine());
		System.out.println("Inserisci descrizione");
		String descrizione = myObj.nextLine();
		//INSERT
		Libro libro = new Libro();
		libro.setAnno(anno);
		libro.setAutore(autoreLibro);
		libro.setEditore(editore);
		libro.setDescrizione(descrizione);
		libro.setTitolo(titolo);
		entityManager.getTransaction().begin();
		entityManager.persist(libro);
		entityManager.getTransaction().commit();
		entityManager.close();
	}
	
	public static List mostraTuttiAutori () {
		String jpql = "SELECT a FROM Autore a ORDER BY a.id";
		Query query = (Query) entityManager.createQuery(jpql);
		List<Autore> autori = query.getResultList();
		for (Autore autore : autori) {
			System.out.println(autore.getId() + ") " + autore.getNome() + " "  + autore.getCognome());			
		}
		return autori;
	}
	
	
}
