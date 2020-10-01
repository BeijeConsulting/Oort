package it.beije.oort.gregori.biblioteca.jpa;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class EditoreUtility {

	public static List<Editore> visualizza() {
			
		String jpaql = "SELECT e FROM Editore as e";
		EntityManager entityManager = JPAEntityManager.createEntityManager();
		List<Editore> editori = entityManager.createQuery(jpaql).getResultList();
		
		entityManager.close();
		
		return editori;
	}
	
	public static void inserisci() {
		Scanner sc = new  Scanner(System.in);
		
		EntityManager entityManager = JPAEntityManager.createEntityManager();
		
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		
		Editore editore = new Editore();
		
		System.out.println("Inserimento editore:");
		
		System.out.print("Denominazione: ");
		editore.setDenominazione(sc.nextLine());
		
		System.out.print("Descrizione: ");
		editore.setDescrizione(sc.nextLine());
		
		entityManager.persist(editore);
		entityManager.getTransaction().commit();
		
		entityManager.close();	
		System.out.println("Editore inserito correttamente!");
	}
	
	public static void rimuovi() {
		int id;
		EntityManager entityManager;
		Scanner sc = new Scanner(System.in);
		char continuare = 'n';
		
		do {
			System.out.println("Rimozione editore:");
			Map<Integer, Editore> mapEditori = new HashMap<Integer, Editore>();
			for (Editore editore : EditoreUtility.visualizza()) {
				mapEditori.put(editore.getId(), editore);
				System.out.println(editore);
			}
			do {
				System.out.println("Scegli un id:");
				id = 0;
				try {
					id = Integer.parseInt(sc.nextLine());
				} catch (NumberFormatException e) {
					continue;
				}
				if (!mapEditori.containsKey(id)) {
					System.out.println("ERRORE: inserire una chiave valida!");
				}
			} while (!mapEditori.containsKey(id));
			
			entityManager = JPAEntityManager.createEntityManager();
			EntityTransaction entityTransaction = entityManager.getTransaction();
			entityTransaction.begin();
			try {
				entityManager.remove(entityManager.find(Editore.class, id));
				entityManager.getTransaction().commit();
			} catch (Exception e) {
				System.out.println("ERRORE: Impossibile rimuove l'editore.");
				System.out.println("L'editore è in uso come chiave esterna!");
				entityManager.close();
				System.out.println("Riprovare? (n per tornare al menù principale)");
				continuare = sc.nextLine().charAt(0);
			} 
		} while (continuare == 's');
		
		entityManager.close();			
		
		System.out.println("Editore " + id + " rimosso correttamente!");
		
	}
	
	public static void modifica() {
		System.out.println("Modifica editore:");
		Map<Integer, Editore> mapEditori = new HashMap<Integer, Editore>();
		for(Editore editore : EditoreUtility.visualizza()) {
			mapEditori.put(editore.getId(), editore);
			System.out.println(editore);
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
			if (!mapEditori.containsKey(id)) {
				System.out.println("ERRORE: inserire una chiave valida!");
				flag = false;
			} 
		} while(!flag);
		
		EntityManager entityManager = JPAEntityManager.createEntityManager();
		
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		
		Editore editore = entityManager.find(Editore.class, id);
		
		System.out.println("Inserimento editore:");
		
		System.out.print("Denominazione: ");
		editore.setDenominazione(sc.nextLine());
		
		System.out.print("Descrizione: ");
		editore.setDescrizione(sc.nextLine());
		
		entityManager.persist(editore);
		entityManager.getTransaction().commit();
		
		entityManager.close();	
		System.out.println("Editore aggiornato correttamente!");
		
	}
	
}
