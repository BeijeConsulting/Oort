package it.beije.oort.gregori.biblioteca;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class PrestitoUtility {

public static List<Prestito> visualizza() {
		
		String hql = "SELECT p FROM Prestito as p";
		Session session = HybSessionFactory.openSession();
		List<Prestito> prestiti = session.createQuery(hql).list();
		
		session.close();
		
		return prestiti;
	}

public static void inserisci() {
	Scanner sc = new  Scanner(System.in);
	
	Session session = HybSessionFactory.openSession();
	
	Transaction transaction = session.beginTransaction();
	Prestito prestito = new Prestito();
	
	System.out.println("Inserimento prestito:");
	
	int scelta = 0;
	List<Libro> libri = LibroUtility.visualizza();
	do {
		for(int i = 0; i < libri.size(); i++) {
			System.out.println(i + ") " + libri.get(i));
		}
		try {
			System.out.print("Id libro: ");
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
			System.out.print("Id utente: ");
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
	
	session.save(prestito);
	transaction.commit();
	
	session.close();		
	System.out.println("Prestito inserito correttamente!");
	
	
}

public static void rimuovi() {
	// TODO Auto-generated method stub
	
}

public static void modifica() {
	// TODO Auto-generated method stub
	
}
	
}
