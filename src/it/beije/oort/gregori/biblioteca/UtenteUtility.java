package it.beije.oort.gregori.biblioteca;

import java.util.List;
import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class UtenteUtility {

	public static List<Utente> visualizza() {
		
		String hql = "SELECT u FROM Utente as u";
		Session session = HybSessionFactory.openSession();
		List<Utente> utenti = session.createQuery(hql).list();
		
		session.close();
		
		return utenti;
	}

	public static void inserisci() {
		Scanner sc = new  Scanner(System.in);
		
		Session session = HybSessionFactory.openSession();
		
		Transaction transaction = session.beginTransaction();
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
		
		session.save(utente);
		transaction.commit();
		
		session.close();		
		System.out.println("Utente inserito correttamente!");
	}
	
}
