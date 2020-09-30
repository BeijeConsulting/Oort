package it.beije.oort.db.jpa.biblio;

import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;


public class UtentiUtil {

	public static List<Utenti> visualizza() {

		EntityManager entityManager = JPAFactoryBiblio.openEntity();
		String jpql = "SELECT e FROM Editori as e";
		Query query = entityManager.createQuery(jpql);
		List<Utenti> utenti = query.getResultList();
		System.out.println(utenti.size());
		for (Utenti utente : utenti) {
			utente.toString();
		}
		entityManager.close();
		return utenti;
		
	}


	public static void inserisci() {
		Scanner sc = new  Scanner(System.in);
		EntityManager entityManager = JPAFactoryBiblio.openEntity();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		Utenti utente = new Utenti();

		System.out.println("Inserimento utente..");

		System.out.print("Nome: ");
		utente.setNome(sc.nextLine());

		System.out.print("Cognome: ");
		utente.setCognome(sc.nextLine());

		System.out.print("Codice fiscale: ");
		utente.setCodiceFiscale(sc.nextLine());

		System.out.print("Telefono: ");
		utente.setTelefono(sc.nextLine());

		System.out.print("Email: ");
		utente.setEmail(sc.nextLine());

		System.out.print("Indirizzo: ");
		utente.setIndirizzo(sc.nextLine());

		entityManager.persist(utente);
		entityManager.getTransaction().commit();
		entityManager.close();	
		sc.close();
		System.out.println("Utente inserito correttamente!");
	}

}
