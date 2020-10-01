package it.beije.oort.gregori.biblioteca.jpa;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class PrestitoUtility {

	public static List<Prestito> visualizza() {
			
		String jpaql = "SELECT p FROM Prestito as p";
		EntityManager entityManager = JPAEntityManager.createEntityManager();
		List<Prestito> prestiti = entityManager.createQuery(jpaql).getResultList();
		
		entityManager.close();
		
		return prestiti;
	}
	
	public static void inserisci() {
		Scanner sc = new  Scanner(System.in);
		
		EntityManager entityManager = JPAEntityManager.createEntityManager();
		
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		
		Prestito prestito = new Prestito();
		
		System.out.println("Inserimento prestito:");
		
		int scelta = 0;
		List<Libro> libri = LibroUtility.visualizza();
		do {
			for(int i = 0; i < libri.size(); i++) {
				System.out.println(i + ") " + libri.get(i));
			}
			try {
				System.out.print("Index libro: ");
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
		List<Utente> utenti = UtenteUtility.visualizza();
		do {
			for(int i = 0; i < utenti.size(); i++) {
				System.out.println(i + ") " + utenti.get(i));
			}
			try {
				System.out.print("Index utente: ");
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
		
		boolean flag = false;
		do {
			System.out.print("Data inizio: ");
			String dataInput = sc.nextLine();
			LocalDate dataInizio = null;
			if (dataInput.length() > 0) {
				dataInizio = LocalDate.parse(dataInput);
			}
			System.out.print("Data fine: ");
			dataInput = sc.nextLine();
			LocalDate dataFine = null;
			if (dataInput.length() > 0) {
				dataFine = LocalDate.parse(dataInput);
			}
			if (dataFine.isBefore(dataInizio)) {
				System.out.println("ERRORE: data fine > data inizio!");
				flag = true;
			} else {
				prestito.setDataFine(dataFine);
				prestito.setDataInizio(dataInizio);
				flag = false;
			} 
		} while(flag);
		
		System.out.print("Note: ");
		prestito.setNote(sc.nextLine());
		
		entityManager.persist(prestito);
		entityManager.getTransaction().commit();
		
		entityManager.close();		
		System.out.println("Prestito inserito correttamente!");
		
		
	}
	
	public static void rimuovi() {
		int id;
		EntityManager entityManager;
		Scanner sc = new Scanner(System.in);
		char continuare = 'n';
		
		do {
			System.out.println("Rimozione prestito:");
			Map<Integer, Prestito> mapPrestiti = new HashMap<Integer, Prestito>();
			for (Prestito prestito : PrestitoUtility.visualizza()) {
				mapPrestiti.put(prestito.getId(), prestito);
				System.out.println(prestito);
			}
			do {
				System.out.println("Scegli un id:");
				id = 0;
				try {
					id = Integer.parseInt(sc.nextLine());
				} catch (NumberFormatException e) {
					continue;
				}
				if (!mapPrestiti.containsKey(id)) {
					System.out.println("ERRORE: inserire una chiave valida!");
				}
			} while (!mapPrestiti.containsKey(id));
			
			entityManager = JPAEntityManager.createEntityManager();
			EntityTransaction entityTransaction = entityManager.getTransaction();
			entityTransaction.begin();
			try {
				entityManager.remove(entityManager.find(Autore.class, id));
				entityManager.getTransaction().commit();
			} catch (Exception e) {
				System.out.println("ERRORE: Impossibile rimuove il prestito.");
				System.out.println("Il prestito è in uso come chiave esterna!");
				entityManager.close();
				System.out.println("Riprovare? (n per tornare al menù principale)");
				continuare = sc.nextLine().charAt(0);
			} 
		} while (continuare == 's');
		
		entityManager.close();			
		
		System.out.println("Prestito " + id + " rimosso correttamente!");
		
	}
	
	public static void modifica() {
		System.out.println("Modifica prestito:");
		Map<Integer, Prestito> mapPrestiti = new HashMap<Integer, Prestito>();
		for(Prestito prestito : PrestitoUtility.visualizza()) {
			mapPrestiti.put(prestito.getId(), prestito);
			System.out.println(prestito);
		}	
		Scanner sc = new Scanner(System.in);
		int id;
		boolean flag = true;
		do {
			System.out.println("Scegli un id:");
			id = 0;
			try {
				id = Integer.parseInt(sc.nextLine());
			} catch (NumberFormatException e) {
				System.out.println("ERRORE: inserire una chiave valida!");
				flag = false;
			}
			if (!mapPrestiti.containsKey(id)) {
				System.out.println("ERRORE: inserire una chiave valida!");
				flag = false;
			} 
		} while(!flag);
		
		
		EntityManager entityManager = JPAEntityManager.createEntityManager();
		
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		
		Prestito prestito = entityManager.find(Prestito.class, id);
		
		System.out.println("Inserimento prestito:");
		
		int scelta = 0;
		List<Libro> libri = LibroUtility.visualizza();
		do {
			for(int i = 0; i < libri.size(); i++) {
				System.out.println(i + ") " + libri.get(i));
			}
			try {
				System.out.print("Index libro: ");
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
		List<Utente> utenti = UtenteUtility.visualizza();
		do {
			for(int i = 0; i < utenti.size(); i++) {
				System.out.println(i + ") " + utenti.get(i));
			}
			try {
				System.out.print("Index utente: ");
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
		
		boolean flag2 = false;
		do {
			System.out.print("Data inizio: ");
			String dataInput = sc.nextLine();
			LocalDate dataInizio = null;
			if (dataInput.length() > 0) {
				dataInizio = LocalDate.parse(dataInput);
			}
			System.out.print("Data fine: ");
			dataInput = sc.nextLine();
			LocalDate dataFine = null;
			if (dataInput.length() > 0) {
				dataFine = LocalDate.parse(dataInput);
			}
			if (dataFine.isBefore(dataInizio)) {
				System.out.println("ERRORE: data fine > data inizio!");
				flag2 = true;
			} else {
				prestito.setDataFine(dataFine);
				prestito.setDataInizio(dataInizio);
				flag2 = false;
			} 
		} while(flag2);
		
		System.out.print("Note: ");
		prestito.setNote(sc.nextLine());
		
		entityManager.persist(prestito);
		entityManager.getTransaction().commit();
		
		entityManager.close();		
		System.out.println("Prestito aggiornato correttamente!");
	}
	
}
