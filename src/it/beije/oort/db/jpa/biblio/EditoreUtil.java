package it.beije.oort.db.jpa.biblio;
import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.Transaction;



public class EditoreUtil {

public static List<Editori> visualizza() {
	
	EntityManager entityManager = JPAFactoryBiblio.openEntity();
	String jpql = "SELECT e FROM Editori as e";
	Query query = entityManager.createQuery(jpql);
	List<Editori> editori = query.getResultList();
	System.out.println(editori.size());
	for (Editori editore : editori) {
		editore.toString();
	}
	entityManager.close();
	return editori;
	
}

public static void inserisci() {
	Scanner sc = new  Scanner(System.in);
	EntityManager entityManager = JPAFactoryBiblio.openEntity();
	EntityTransaction entityTransaction = entityManager.getTransaction();
	entityTransaction.begin();
	Editori editore = new Editori();

	System.out.println("Inserimento editore:");

	System.out.print("Denominazione: ");
	editore.setDenominazione(sc.nextLine());
	
	System.out.print("Descrizione: ");
	editore.setDenominazione(sc.nextLine());
	
	entityManager.persist(editore);
	entityManager.getTransaction().commit();
	entityManager.close();	
	sc.close();
	System.out.println("Editore inserito correttamente!");

}
}
