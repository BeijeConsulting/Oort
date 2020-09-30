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

public class AutoreUtility {

	public static List<Autore> visualizza() {
			
		String jpaql = "SELECT a FROM Autore as a";
		EntityManager entityManager = JPAEntityManager.createEntityManager();
		List<Autore> autori = entityManager.createQuery(jpaql).getResultList();
		
		entityManager.close();
		
		return autori;
	}
	
	public static void inserisci() {
		Scanner sc = new  Scanner(System.in);
		
		EntityManager entityManager = JPAEntityManager.createEntityManager();
		
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
		
		boolean flag = false;
		do {
			System.out.print("Data di nascita: ");
			String dataInput = sc.nextLine();
			LocalDate dataInizio = null;
			if (dataInput.length() > 0) {
				dataInizio = LocalDate.parse(dataInput);
			}
			System.out.print("Data di morte: ");
			dataInput = sc.nextLine();
			LocalDate dataFine = null;
			if (dataInput.length() > 0) {
				dataFine = LocalDate.parse(dataInput);
			}
			if (dataFine.isBefore(dataInizio)) {
				System.out.println("ERRORE: data morte > data nascita!");
				flag = true;
			} else {
				autore.setDataMorte(dataFine);
				autore.setDataNascita(dataInizio);
				flag = false;
			} 
		} while(flag);
		
		entityManager.persist(autore);
		entityManager.getTransaction().commit();
		
		entityManager.close();
		System.out.println("Autore inserito correttamente!");
		
	}
	
	public static void rimuovi() {
		int id;
		EntityManager entityManager;
		Scanner sc = new Scanner(System.in);
		char continuare = 'n';
		
		do {
			System.out.println("Rimozione autore:");
			Map<Integer, Autore> mapAutori = new HashMap<Integer, Autore>();
			for (Autore autore : AutoreUtility.visualizza()) {
				mapAutori.put(autore.getId(), autore);
				System.out.println(autore);
			}
			do {
				System.out.println("Scegli un id:");
				id = 0;
				try {
					id = Integer.parseInt(sc.nextLine());
				} catch (NumberFormatException e) {
					continue;
				}
				if (!mapAutori.containsKey(id)) {
					System.out.println("ERRORE: inserire una chiave valida!");
				}
			} while (!mapAutori.containsKey(id));
			
			entityManager = JPAEntityManager.createEntityManager();
			EntityTransaction entityTransaction = entityManager.getTransaction();
			entityTransaction.begin();
			try {
				entityManager.remove(entityManager.find(Autore.class, id));
				entityManager.getTransaction().commit();
			} catch (Exception e) {
				System.out.println("ERRORE: Impossibile rimuove l'autore.");
				System.out.println("L'autore è in uso come chiave esterna!");
				entityManager.close();
				System.out.println("Riprovare? (n per tornare al menù principale)");
				continuare = sc.nextLine().charAt(0);
			} 
		} while (continuare == 's');
		
		entityManager.close();			
		
		System.out.println("Autore " + id + " rimosso correttamente!");
		
	}
	
	public static void modifica() {
		System.out.println("Modifica autore:");	
		Map<Integer, Autore> mapAutori = new HashMap<Integer, Autore>();
		for(Autore autore : AutoreUtility.visualizza()) {
			mapAutori.put(autore.getId(), autore);
			System.out.println(autore);
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
			if (!mapAutori.containsKey(id)) {
				System.out.println("ERRORE: inserire una chiave valida!");
			} 
		} while (!mapAutori.containsKey(id));
		
		EntityManager entityManager = JPAEntityManager.createEntityManager();
		
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
	
		Autore autore = entityManager.find(Autore.class, id);
		
		System.out.println("Inserimento autore:");
		
		System.out.print("Nome: ");
		autore.setNome(sc.nextLine());
		
		System.out.print("Cognome: ");
		autore.setCognome(sc.nextLine());
		
		System.out.print("Biografia: ");
		autore.setBiografia(sc.nextLine());
		
		boolean flag = false;
		do {
			System.out.print("Data di nascita: ");
			String dataInput = sc.nextLine();
			LocalDate dataInizio = null;
			if (dataInput.length() > 0) {
				dataInizio = LocalDate.parse(dataInput);
			}
			System.out.print("Data di morte: ");
			dataInput = sc.nextLine();
			LocalDate dataFine = null;
			if (dataInput.length() > 0) {
				dataFine = LocalDate.parse(dataInput);
			}
			if (dataFine.isBefore(dataInizio)) {
				System.out.println("ERRORE: data morte > data nascita!");
				flag = true;
			} else {
				autore.setDataMorte(dataFine);
				autore.setDataNascita(dataInizio);
				flag = false;
			} 
		} while(flag);
		
		entityManager.persist(autore);
		entityManager.getTransaction().commit();
		
		entityManager.close();
		System.out.println("Autore inserito correttamente!");
	}
	
}
