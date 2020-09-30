package it.beije.oort.db.jpa.biblio;

import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;



public class LibroUtil {
	public static List<Libri> visualizza() {

		EntityManager entityManager = JPAFactoryBiblio.openEntity();
		String jpql = "SELECT l FROM Libri as l";
		Query query = entityManager.createQuery(jpql);
		List<Libri> libri = query.getResultList();
		System.out.println(libri.size());
		for (Libri libro : libri) {
			libro.toString();
		}
		entityManager.close();
		return libri;
	
	}

public static void inserisci() {
	Scanner sc = new  Scanner(System.in);
	EntityManager entityManager = JPAFactoryBiblio.openEntity();
	EntityTransaction entityTransaction = entityManager.getTransaction();
	entityTransaction.begin();
	Libri libro = new Libri();

	System.out.println("Inserimento libro..");

	System.out.print("Titolo: ");
	 libro.setTitolo(sc.nextLine());

	System.out.print("Descrizione: ");
	libro.setDescrizione(sc.nextLine());	

	System.out.print("Anno: ");
	libro.setAnno(sc.nextLine());	

	int scelta = 0;
	List<Autore> autori = AutoreUtil.visualizza();
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
	List<Editori> editori = EditoreUtil.visualizza();
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

	entityManager.persist(libro);
	entityManager.getTransaction().commit();
	entityManager.close();		
	sc.close();
	System.out.println("Libro inserito correttamente!");

}

}
