package it.beije.oort.gregori.biblioteca.jpa;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.exception.ConstraintViolationException;

import it.beije.oort.rubrica.Contatto;

public class UtenteUtility {

	public static List<Utente> visualizza() {
		
		String jpaql = "SELECT u FROM Utente as u";
		EntityManager entityManager = JPAEntityManager.createEntityManager();
		List<Utente> utenti = entityManager.createQuery(jpaql).getResultList();
		
		entityManager.close();
		
		return utenti;
	}

	public static void inserisci() {
		Scanner sc = new  Scanner(System.in);
		
		EntityManager entityManager = JPAEntityManager.createEntityManager();
		
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		
		Utente utente = new Utente();
		
		System.out.println("Inserimento utente:");
		
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
		System.out.println("Utente inserito correttamente!");
	}

	public static void rimuovi() {
		int id;
		EntityManager entityManager;
		Scanner sc = new Scanner(System.in);
		char continuare = 'n';
		
		do {
			System.out.println("Rimozione utente:");
			Map<Integer, Utente> mapUtenti = new HashMap<Integer, Utente>();
			for (Utente utente : UtenteUtility.visualizza()) {
				mapUtenti.put(utente.getId(), utente);
				System.out.println(utente);
			}
			do {
				System.out.println("Scegli un id:");
				id = 0;
				try {
					id = Integer.parseInt(sc.nextLine());
				} catch (NumberFormatException e) {
					continue;
				}
				if (!mapUtenti.containsKey(id)) {
					System.out.println("ERRORE: inserire una chiave valida!");
				}
			} while (!mapUtenti.containsKey(id));
			entityManager = JPAEntityManager.createEntityManager();
			EntityTransaction entityTransaction = entityManager.getTransaction();
			entityTransaction.begin();
			try {
				entityManager.remove(entityManager.find(Utente.class, id));
				entityManager.getTransaction().commit();
			} catch (Exception e) {
				System.out.println("ERRORE: Impossibile rimuove l'utente.");
				System.out.println("L'utente è in uso come chiave esterna!");
				entityManager.close();
				System.out.println("Riprovare? (n per tornare al menù principale)");
				continuare = sc.nextLine().charAt(0);
			} 
		} while (continuare == 's');
		
		entityManager.close();			
		
		System.out.println("Utente " + id + " rimosso correttamente!");
	}

	public static void modifica() {
		System.out.println("Modifica utente:");	
		Map<Integer, Utente> mapUtenti = new HashMap<Integer, Utente>();
		for(Utente utente : UtenteUtility.visualizza()) {
			mapUtenti.put(utente.getId(), utente);
			System.out.println(utente);
		}
		
		Scanner sc = new Scanner(System.in);;
		int id;
		do {
			System.out.println("Scegli un id:");
			id = 0;
			try {
				id = Integer.parseInt(sc.nextLine());
			} catch (NumberFormatException e) {
				continue;
			}
			if (!mapUtenti.containsKey(id)) {
				System.out.println("ERRORE: inserire una chiave valida!");
			} 
		} while (!mapUtenti.containsKey(id));
		EntityManager entityManager = JPAEntityManager.createEntityManager();
		
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		
		Utente utente = entityManager.find(Utente.class, id);		
		
		System.out.println("Nuovo utente:");
		
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
		System.out.println("Utente aggiornato correttamente!");
	}
	
}
