package it.beije.oort.kirolosmater.biblioteca;

import static it.beije.oort.kirolosmater.biblioteca.LibraryManager.libraryPersistenceUnit;

import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;

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
		case 4: inserimentoLibro();
			break;

		default:
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
	}
	
	public static Libro readRecordFromDb(int id) { 
		Libro libro = entityManager.find(Libro.class, id);
		return libro;
	}
	
	public static void inserimentoLibro () {
		Scanner myObj = new Scanner(System.in);
		System.out.println("Inserisci titolo");
		String titolo = myObj.nextLine();
		System.out.println("Inserisci id dell'autore del libro");
		mostraTuttiAutori();

	}
	
	public static void mostraTuttiAutori () {
		String jpql = "SELECT a FROM Autore a ORDER BY a.id";
		Query query = (Query) entityManager.createQuery(jpql);
		List<Autore> autori = query.getResultList();
		for (Autore autore : autori) {
			System.out.println(autore.getId() + ") " + autore.getNome() + " "  + autore.getCognome());			

		}
	}
}
