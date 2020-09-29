package it.beije.oort.gregori.biblioteca;

import java.util.List;
import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class EditoreUtility {

public static List<Editore> visualizza() {
		
		String hql = "SELECT e FROM Editore as e";
		Session session = HybSessionFactory.openSession();
		List<Editore> editori = session.createQuery(hql).list();
		
		session.close();
		
		return editori;
	}

public static void inserisci() {
	Scanner sc = new  Scanner(System.in);
	
	Session session = HybSessionFactory.openSession();
	
	Transaction transaction = session.beginTransaction();
	Editore editore = new Editore();
	
	System.out.println("Inserimento editore:");
	
	System.out.print("Denominazione: ");
	editore.setDenominazione(sc.nextLine());
	
	System.out.print("Descrizione: ");
	editore.setDescrizione(sc.nextLine());
	
	session.save(editore);
	transaction.commit();
	
	session.close();		
	System.out.println("Editore inserito correttamente!");
}
	
}
