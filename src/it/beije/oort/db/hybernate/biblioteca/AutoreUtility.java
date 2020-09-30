package it.beije.oort.db.hybernate.biblioteca;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.Transaction;



public class AutoreUtility {

public static List<Autori> visualizza() {

		String hql = "SELECT a FROM Autore as a";
		Session session = HybSessionFactorybiblio.openSession();
		List<Autori> autori = session.createQuery(hql).list();

		session.close();

		return autori;
	}

public static void inserisci() {
	Scanner sc = new  Scanner(System.in);

	Session session = HybSessionFactorybiblio.openSession();

	Transaction transaction = session.beginTransaction();
	Autori autore = new Autori();

	System.out.println("Inserimento autore:");

	System.out.print("Nome: ");
	autore.setNome(sc.nextLine());

	System.out.print("Cognome: ");
	autore.setCognome(sc.nextLine());

	System.out.print("Biografia: ");
	autore.setBiografia(sc.nextLine());

	System.out.print("Data di nascita: ");
	autore.setDataNascita(sc.nextLine());
	
	System.out.print("Data di morte: ");
	autore.setDataMorte(sc.nextLine());
	


	session.save(autore);
	transaction.commit();

	session.close();		
	System.out.println("Autore inserito correttamente!");

}

}
