package it.beije.oort.db.hybernate.biblioteca;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.Transaction;



public class AutoreUtility {

public static List<Autori> visualizza() {

		String hql = "SELECT a FROM Autore as a";
		Session session = HybSessionFactory.openSession();
		List<Autori> autori = session.createQuery(hql).list();

		session.close();

		return autori;
	}

public static void inserisci() {
	Scanner sc = new  Scanner(System.in);

	Session session = HybSessionFactory.openSession();

	Transaction transaction = session.beginTransaction();
	Autori autore = new Autori();

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

	session.save(autore);
	transaction.commit();

	session.close();		
	System.out.println("Autore inserito correttamente!");

}

}
