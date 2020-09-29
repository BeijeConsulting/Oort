package it.beije.oort.gregori.biblioteca;

import java.util.List;
import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class LibroUtility {

public static List<Libro> visualizza() {
		
		String hql = "SELECT l FROM Libro as l";
		Session session = HybSessionFactory.openSession();
		List<Libro> libri = session.createQuery(hql).list();
		
		session.close();
		
		return libri;
	}

public static void inserisci() {
	Scanner sc = new  Scanner(System.in);
	
	Session session = HybSessionFactory.openSession();
	
	Transaction transaction = session.beginTransaction();
	Libro libro = new Libro();
	
	System.out.println("Inserimento libro:");
	
	System.out.print("Titolo: ");
	libro.setTitolo(sc.nextLine());
	
	System.out.print("Descrizione: ");
	libro.setDescrizione(sc.nextLine());	
	
	System.out.print("Anno: ");
	libro.setAnno(sc.nextLine());	
	
	int scelta = 0;
	List<Autore> autori = AutoreUtility.visualizza();
	do {
		for(int i = 0; i < autori.size(); i++) {
			System.out.println(i + ") " + autori.get(i));
		}
		try {
			System.out.print("Id autore: ");
			scelta = Integer.parseInt(sc.nextLine());
		}
		catch (NumberFormatException e) {
			System.out.println("ERRORE: inserire un valore valido!");
			continue;
		}
		if(scelta >= autori.size() || scelta < 0) {
			System.out.println("ERRORE: inserire un valore valido!");
		}
	} while(scelta >= autori.size() || scelta < 0);
	
	libro.setAutore(autori.get(scelta).getId());	
	
	scelta = 0;
	List<Editore> editori = EditoreUtility.visualizza();
	do {
		for(int i = 0; i < editori.size(); i++) {
			System.out.println(i + ") " + editori.get(i));
		}
		try {
			System.out.print("Id editore: ");
			scelta = Integer.parseInt(sc.nextLine());
		}
		catch (NumberFormatException e) {
			System.out.println("ERRORE: inserire un valore valido!");
			continue;
		}
		if(scelta >= editori.size() || scelta < 0) {
			System.out.println("ERRORE: inserire un valore valido!");
		}
	} while(scelta >= editori.size() || scelta < 0);
	
	libro.setEditore(editori.get(scelta).getId());	
	
	session.save(libro);
	transaction.commit();
	
	session.close();		
	System.out.println("Libro inserito correttamente!");
	
}

}
