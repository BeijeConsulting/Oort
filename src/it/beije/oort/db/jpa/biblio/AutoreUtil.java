package it.beije.oort.db.jpa.biblio;

import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;


public class AutoreUtil {
	public static List<Autore> visualizza() {
		
		EntityManager entityManager = JPAFactoryBiblio.openEntity();
		String jpql = "SELECT a FROM Autore as a";
		Query query = entityManager.createQuery(jpql);
		List<Autore> autori = query.getResultList();
		System.out.println(autori.size());
		for (Autore autore : autori) {
			autore.toString();
		}
		entityManager.close();
		return autori;
		
	}

public static void inserisci() {
	Scanner sc = new  Scanner(System.in);
	EntityManager entityManager = JPAFactoryBiblio.openEntity();
	EntityTransaction entityTransaction = entityManager.getTransaction();
	entityTransaction.begin();
	Autore autore = new Autore();

	System.out.println("Inserimento autore:");

	System.out.print("Nome: ");
	autore.setNome(sc.nextLine());

	System.out.print("Cognome: ");
	autore.setCognome(sc.nextLine());

	System.out.print("Biografia: ");
	autore.setBiografia(sc.nextLine());

	System.out.print("Data di nascinta: ");
	autore.setDataNascita(sc.nextLine());
	
	System.out.println("Data di morte: ");
	autore.setDataMorte(sc.nextLine());
	
	
	entityManager.persist(autore);
	entityManager.getTransaction().commit();
	entityManager.close();	
	sc.close();
	System.out.println("Autore inserito correttamente!");

}

}
