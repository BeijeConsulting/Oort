package it.beije.oort.gregori.biblioteca.jpa;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class LibroUtility {

	public static List<Libro> visualizza() {
			
		String jpaql = "SELECT l FROM Libro as l";
		EntityManager entityManager = JPAEntityManager.createEntityManager();
		List<Libro> libri = entityManager.createQuery(jpaql).getResultList();
		
		entityManager.close();
		
		return libri;
	}
	
	public static void inserisci() {
		Scanner sc = new  Scanner(System.in);
		
		EntityManager entityManager = JPAEntityManager.createEntityManager();
		
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		
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
		
		entityManager.persist(libro);
		entityManager.getTransaction().commit();
		
		entityManager.close();	
		System.out.println("Libro inserito correttamente!");
		
	}
	
	public static void rimuovi() {
		int id;
		EntityManager entityManager;
		Scanner sc = new Scanner(System.in);
		char continuare = 'n';
		
		do {
			System.out.println("Rimozione libro:");
			Map<Integer, Libro> mapLibri = new HashMap<Integer, Libro>();
			for (Libro libro : LibroUtility.visualizza()) {
				mapLibri.put(libro.getId(), libro);
				System.out.println(libro);
			}
			do {
				System.out.println("Scegli un id:");
				id = 0;
				try {
					id = Integer.parseInt(sc.nextLine());
				} catch (NumberFormatException e) {
					continue;
				}
				if (!mapLibri.containsKey(id)) {
					System.out.println("ERRORE: inserire una chiave valida!");
				}
			} while (!mapLibri.containsKey(id));
			
			entityManager = JPAEntityManager.createEntityManager();
			EntityTransaction entityTransaction = entityManager.getTransaction();
			entityTransaction.begin();
			try {
				entityManager.remove(entityManager.find(Libro.class, id));
				entityManager.getTransaction().commit();
			} catch (Exception e) {
				System.out.println("ERRORE: Impossibile rimuove il libro.");
				System.out.println("Il libro è in uso come chiave esterna!");
				entityManager.close();
				System.out.println("Riprovare? (n per tornare al menù principale)");
				continuare = sc.nextLine().charAt(0);
			} 
		} while (continuare == 's');
		
		entityManager.close();			
		
		System.out.println("Libro " + id + " rimosso correttamente!");		
	}
	
	public static void modifica() {		
		System.out.println("Modifica libro:");
		Map<Integer, Libro> mapLibri = new HashMap<Integer, Libro>();
		for(Libro libro : LibroUtility.visualizza()) {
			mapLibri.put(libro.getId(), libro);
			System.out.println(libro);
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
			if (!mapLibri.containsKey(id)) {
				System.out.println("ERRORE: inserire una chiave valida!");
				flag = false;
			} 
		} while(!flag);
		EntityManager entityManager = JPAEntityManager.createEntityManager();
		
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		
		Libro libro = entityManager.find(Libro.class, id);		
		
		System.out.println("Inserimento libro:");
		
		System.out.print("Titolo: ");
		libro.setTitolo(sc.nextLine());
		
		System.out.print("Descrizione: ");
		libro.setDescrizione(sc.nextLine());	
		
		System.out.print("Anno: ");
		libro.setAnno(sc.nextLine());	
		
		int scelta = 0;
		List<Autore> autori = AutoreUtility.visualizza();
		List<Integer> idAutori = new ArrayList<Integer>();
		do {
			for(Autore autore : autori) {
				System.out.println(autore);
				idAutori.add(autore.getId());
			}
			try {
				System.out.print("Id autore: ");
				scelta = Integer.parseInt(sc.nextLine());
			}
			catch (NumberFormatException e) {
				System.out.println("ERRORE: inserire un valore valido!");
				continue;
			}
			if(!idAutori.contains(scelta)) {
				System.out.println("ERRORE: inserire un valore valido!");
			}
		} while(!idAutori.contains(scelta));
		
		libro.setAutore(scelta);	
		
		scelta = 0;
		List<Editore> editori = EditoreUtility.visualizza();
		List<Integer> idEditori = new ArrayList<Integer>();
		do {
			for(Editore editore : editori) {
				System.out.println(editore);
				idEditori.add(editore.getId());
			}
			try {
				System.out.print("Id editore: ");
				scelta = Integer.parseInt(sc.nextLine());
			}
			catch (NumberFormatException e) {
				continue;
			}
			if(!idEditori.contains(scelta)) {
				System.out.println("ERRORE: inserire un valore valido!");
			}
		} while(!idEditori.contains(scelta));
		
		libro.setEditore(scelta);	
		
		entityManager.persist(libro);
		entityManager.getTransaction().commit();
		
		entityManager.close();	
	
		System.out.println("Libro aggiornato correttamente!");
	}

}
