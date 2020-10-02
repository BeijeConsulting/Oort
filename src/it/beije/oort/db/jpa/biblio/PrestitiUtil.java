package it.beije.oort.db.jpa.biblio;

import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

public class PrestitiUtil {

public static List<Prestiti> visualizza() {

	EntityManager entityManager = JPAFactoryBiblio.openEntity();
	String jpql = "SELECT p FROM Prestiti as p";
	Query query = entityManager.createQuery(jpql);
	List<Prestiti> prestiti = query.getResultList();
	System.out.println(prestiti.size());
	for (Prestiti prestito : prestiti) {
		prestito.toString();
	}
	entityManager.close();
	return prestiti;
	
}

public static void inserisci() {
	Scanner sc = new  Scanner(System.in);
	EntityManager entityManager = JPAFactoryBiblio.openEntity();
	EntityTransaction entityTransaction = entityManager.getTransaction();
	entityTransaction.begin();
	Prestiti prestito = new Prestiti();

	System.out.println("Inserimento prestito:");

	int scelta = 0;
	List<Libri> libri = LibroUtil.visualizza();
	do {
		for(int i = 0; i < libri.size(); i++) {
			System.out.println(i + ") " + libri.get(i));
		}
		try {
			System.out.print("Id libro: ");
			scelta = Integer.parseInt(sc.nextLine());
		}
		catch (NumberFormatException e) {
			System.out.println("ERRORE: inserire un valore valido!");
			continue;
		}
		if(scelta >= libri.size() || scelta < 0) {
			System.out.println("ERRORE: inserire un valore valido!");
		}
	} while(scelta >= libri.size() || scelta < 0);

	prestito.setLibro(libri.get(scelta).getId());	

	scelta = 0;
	List<Utenti> utenti = UtentiUtil.visualizza();
	do {
		for(int i = 0; i < utenti.size(); i++) {
			System.out.println(i + ") " + utenti.get(i));
		}
		try {
			System.out.print("Id utente: ");
			scelta = Integer.parseInt(sc.nextLine());
		}
		catch (NumberFormatException e) {
			System.out.println("ERRORE: inserire un valore valido!");
			continue;
		}
		if(scelta >= utenti.size() || scelta < 0) {
			System.out.println("ERRORE: inserire un valore valido!");
		}
	} while(scelta >= utenti.size() || scelta < 0);

	prestito.setUtente(utenti.get(scelta).getId());	

	System.out.print("Data inizio prestito: ");
	prestito.setDataInizio(sc.nextLine());
	
	System.out.print("Data fine prestito: ");
	prestito.setDataFine(sc.nextLine());

	System.out.print("Note: ");
	prestito.setNote(sc.nextLine());

	entityManager.persist(prestito);
	entityManager.getTransaction().commit();
	entityManager.close();	
	sc.close();
	System.out.println("Prestito inserito correttamente!");

}
}