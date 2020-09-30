package it.beije.oort.kirolosmater.biblioteca;

import java.util.Map;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.hibernate.Session;


import it.beije.oort.rubrica.HybSessionFactory;

public class LibraryManager {
	static Map entityManagerFromPersistence = JPAEntityManagerSingleton
			.getJpaEntityManager("OortBiblioteca");
	static EntityManager entityManager = (EntityManager) entityManagerFromPersistence.get("OortBiblioteca");

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		LibraryManager libraryManager = new LibraryManager();
//		EntityManagerFactory factory = Persistence.createEntityManagerFactory("OortRubrica");
//		EntityManager entityManager = factory.createEntityManager();
		
		libraryManager.menuBiblioteca();
		
				
	}
	
	public void menuBiblioteca () {
		Scanner inputFromUser = new Scanner(System.in);
		System.out.println("Scegli la tabella su cui vuoi lavorare: ");
		System.out.println("inserisci 1 | Per gestire gli autori");
		System.out.println("inserisci 2 | Per gestire gli editori");
		System.out.println("inserisci 3 | Per gestire i libri");
		System.out.println("inserisci 4 | Per gestire i prestiti");
		System.out.println("inserisci 5 | Per gestire gli utenti");
		String lineFromInput = inputFromUser.nextLine();
		int numberFromInput = Integer.parseInt(lineFromInput);
		switch (numberFromInput) {
		case 1: MetodiAutore.menuAutore();
			
			break;

		default:
			break;
		}
	}
	

}
