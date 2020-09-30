package it.beije.oort.gregori.biblioteca;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.exception.ConstraintViolationException;

import it.beije.oort.rubrica.Contatto;

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

	public static void rimuovi() {
		Map<Integer, Utente> mapUtenti = new HashMap<Integer, Utente>();
		for(Utente utente : UtenteUtility.visualizza()) {
			mapUtenti.put(utente.getId(), utente);
			System.out.println(utente);
		}
		System.out.println("Rimozione utente:");	
		System.out.println("Scegli un id:");	
		Scanner sc = new Scanner(System.in);
		int id = 0;
		try {
			id = Integer.parseInt(sc.nextLine());
		}
		catch(NumberFormatException e) {
			System.out.println("ERRORE: inserire una chiave valida!");
			UtenteUtility.rimuovi();
		}
		if(!mapUtenti.containsKey(id)) {
			System.out.println("ERRORE: inserire una chiave valida!");
			UtenteUtility.rimuovi();
		}
		
		Session session = HybSessionFactory.openSession();
		
		Transaction transaction = session.beginTransaction();
		try {
			session.remove(session.get(Utente.class, id));
		}
		catch(Exception e) {
			System.out.println("ERRORE: Impossibile rimuove l'utente.");
			System.out.println("L'utente è in uso come chiave esterna!");
			transaction.rollback();
			session.close();
			UtenteUtility.rimuovi();
		}
		
		transaction.commit();
		
		session.close();		
		
		System.out.println("Utente " + id + " rimosso correttamente!");
	}

	public static void modifica() {
		Map<Integer, Utente> mapUtenti = new HashMap<Integer, Utente>();
		for(Utente utente : UtenteUtility.visualizza()) {
			mapUtenti.put(utente.getId(), utente);
			System.out.println(utente);
		}
		System.out.println("Modifica utente:");	
		System.out.println("Scegli un id:");	
		Scanner sc = new Scanner(System.in);
		int id = 0;
		try {
			id = Integer.parseInt(sc.nextLine());
		}
		catch(NumberFormatException e) {
			System.out.println("ERRORE: inserire una chiave valida!");
			UtenteUtility.modifica();
		}
		if(!mapUtenti.containsKey(id)) {
			System.out.println("ERRORE: inserire una chiave valida!");
			UtenteUtility.modifica();
		}
		
		Session session = HybSessionFactory.openSession();
		
		Transaction transaction = session.beginTransaction();
		
		Utente utente = session.get(Utente.class, id);		
		
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
		System.out.println("Utente aggiornato correttamente!");
	}
	
}
