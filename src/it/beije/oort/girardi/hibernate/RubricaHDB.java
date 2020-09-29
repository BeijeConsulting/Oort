package it.beije.oort.girardi.hibernate;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import it.beije.oort.girardi.dataBase.ToDB;
import it.beije.oort.rubrica.Contatto;


public class RubricaHDB {
//1) VISUALIZZA
// viualizza menù
	public static void visualizzaMenu (Session session) {
		Scanner myInput = new Scanner(System.in);  //apre lo scanner
		int id = 0;	
		String in = "";
		
		try {
			System.out.println("digitare 1 o 2 per le seguenti azioni: ");
			System.out.println("\t 1) visualizza tutti i contatti");
			System.out.println("\t 2) visualizza singolo contatto");
			in = myInput.nextLine();
			
			switch (in) {
			case "1": 
				RubricaHDB.visualizzaTutti(session);
				break;
				
			case "2": 
				System.out.print("inserire l'id del contatto che si vuole visualizzare: ");
				id = (int) Integer.parseInt(myInput.nextLine());
				RubricaHDB.visualizzaId(session, id);
				break;
				
			default:
				System.out.println("inserimento non valido");
			}
			System.out.println("");
			
		} catch (NumberFormatException nfe) {
		//	nfe.printStackTrace();
			System.out.println("inserimento non valido");
		}
	}
	
	
//visualizza contatto tramite id:
	public static void visualizzaId (Session session, int id) {
		Contatto c = session.find(Contatto.class, id);
//		System.out.println(c);
		System.out.println("ID, COGNOME, NOME, TELEFONO, EMAIL");
		System.out.println(c.getId()+",  "+c.getCognome()+",  "+c.getNome() 
								+",  "+c.getTelefono()+",  "+c.getEmail());
	}

//visualizza tutti i contatti presenti nel db:
	public static void visualizzaTutti (Session session) {
		Scanner myInput = new Scanner(System.in);  
		int count = 0;
		//query HQL
		String hql = "SELECT c FROM Contatto as c";
		Query<Contatto> query = session.createQuery(hql);
		System.out.println("# contatti presenti: " + query.list().size());
		System.out.println("ID, COGNOME, NOME, TELEFONO, EMAIL");
		for (Contatto c : query.list()) {
			System.out.println(c.getId()+",  "+c.getCognome()+",  "+c.getNome() 
									+",  "+c.getTelefono()+",  "+c.getEmail());
			if (++count % 30 == 0) { //mostra 30 contatti alla volta
				System.out.print("digitare 1 per vedere la seconda pagina: ");
				String si = myInput.nextLine();
				if (!(si.contentEquals("1")))
					break;
			}
		}	
	}
	

	
	
//2) MODIFICA / CANCELLA
//cancella contatto tramite id:
	public static void CancModMenu (Session session) {
		Scanner myInput = new Scanner(System.in);  //apre lo scanner
		int id = 0;	
		String in = "";
		
		try {
			System.out.println("digitare 1 o 2 per le seguenti azioni: ");
			System.out.println("\t 1) cancella contatto");
			System.out.println("\t 2) modifica contatto");
			in = myInput.nextLine();
			
			switch (in) {
			case "1": 
				System.out.print("inserire l'id del contatto che si vuole cancellare: ");
				id = (int) Integer.parseInt(myInput.nextLine());
				RubricaHDB.cancellaId(session, id);
				break;
				
			case "2": 
				System.out.print("inserire l'id del contatto che si vuole modificare: ");
				id = (int) Integer.parseInt(myInput.nextLine());
				RubricaHDB.modificaContatto(session, id);
				break;
				
			default:
				System.out.println("inserimento non valido");
			}
			System.out.println("");
			
		} catch (NumberFormatException nfe) {
		//	nfe.printStackTrace();
			System.out.println("inserimento non valido");
		}
	}
	
	
//cancella contatto tramite id:
	public static void cancellaId (Session session, int id) {
		//apro transazione
		Transaction transaction = session.beginTransaction();
		
		Contatto contatto = session.get(Contatto.class, id);
		System.out.println(contatto);
		if (contatto == null) {
			System.out.println("non sono presenti contatti con l'id richiesto");
			transaction.commit();
			return;
		}
		session.delete(contatto);

		//confermo aggiornamento su DB
		transaction.commit();
		System.out.println("contatto eliminato");
		//annullo aggiornamento su DB
		//transaction.rollback();
	}
	
//modifica contatto:
	public static void modificaContatto (Session session, int id) {
		//apro transazione
		Transaction transaction = session.beginTransaction();

		Contatto contatto = session.get(Contatto.class, id);
		System.out.println(contatto);
		if (contatto == null) {
			System.out.println("non sono presenti contatti con l'id richiesto");
			transaction.commit();
			return;
		}
		
		Scanner myInput = new Scanner(System.in);  //apre lo scanner
		String input = ""; 
	
		//cognome, nome, telefono, email
		System.out.println("inserire i campi relativi al contatto:");
		System.out.println("(se non si vuole cambiare un campo premere invio)");
		System.out.print("\t inserire il nome: ");
		input = myInput.nextLine();
		if (!(input.contentEquals("")))
			contatto.setNome(input);
		System.out.print("\t inserire il cognome: ");
		input = myInput.nextLine();
		if (!(input.contentEquals("")))
			contatto.setCognome(input);
		System.out.print("\t inserire il telefono: ");
		input = myInput.nextLine();
		if (!(input.contentEquals("")))
			contatto.setTelefono(input);
		System.out.print("\t inserire la email: ");
		input = myInput.nextLine();
		if (!(input.contentEquals("")))
			contatto.setEmail(input);
		
		System.out.println(contatto);
		session.save(contatto);
	
		//confermo aggiornamento su DB
		transaction.commit();
		
		//annullo aggiornamento su DB
		//transaction.rollback();
		
		System.gc();
	
	}
	

	
//3) Inserimento 
//inserimento contatto nel DataBase 
	public static void inserisciContatto (Session session) {
		Contatto contatto = new Contatto();
		Scanner myInput = new Scanner(System.in);  //apre lo scanner
		
		//cognome, nome, telefono, email
		System.out.println("inserire i campi relativi al contatto:");
		System.out.println("(se non si conosce il campo richiesto premere invio)");
		System.out.print("\t inserire il nome: ");
		contatto.setNome(myInput.nextLine());
		System.out.print("\t inserire il cognome: ");
		contatto.setCognome(myInput.nextLine());
		System.out.print("\t inserire il telefono: ");
		contatto.setTelefono(myInput.nextLine());
		System.out.print("\t inserire la email: ");
		contatto.setEmail(myInput.nextLine());
		
		
		//apro transazione
		Transaction transaction = session.beginTransaction();
		
		//INSERT
		Contatto contatto = new Contatto();
		contatto.setId(3);
		contatto.setNome("Fiorenza");
		contatto.setCognome("Volpe");
		contatto.setEmail("fiore@volpe.it");
		contatto.setTelefono("34556616");
		System.out.println("id : " + contatto.getId());
		System.out.println("id : " + contatto.getId());
		
		
		if (contatto.getNome().trim().equals("") && contatto.getCognome().trim().equals("") &&
			contatto.getTelefono().trim().equals("") && contatto.getEmail().trim().equals("") )
			System.out.println("ALERT: il contatto è vuoto e non verrà inserito\n");
			//annullo aggiornamento su DB
			transaction.rollback();
		else {
			session.save(contatto);
			//confermo aggiornamento su DB
			transaction.commit();
		}
		System.out.println("");
		System.gc();
	}
		
		
	
//4) Esporta rubrica
	
	
		
// -------------- MAIN -----------------
//Permette di gestire la rubrica di mySQL inserendo comandi da tastiera:
	public static void main(String[] args) {
		//A) collegamento al DataBase (apro sessione):
		System.out.println("INIZIO");
		Session session = MyHibSessionFactory.openSession();
		System.out.println("session is open? " + session.isOpen());
			
		//B) interfaccia utente: menù di selezione:
		System.out.print("Benvenuto!");
			
//		CORPO: while (true) {
		System.out.println("\nEcco le azioni eseguibili sulla tua rubrica:");
		final String[] menu = {"1) Visualizzazione contatti", "2) Modifica / Cancellazione", 
				    "3) Inserimento contatto", "4) Esporta rubrica", "5) Termina programma"};
		System.out.println("\t" + menu[0] + "\n\t" + menu[1] + "\n\t" + menu[2] 
							+ "\n\t" + menu[3] + "\n\t" + menu[4] );
	CORPO: while (true) {
			System.out.print("Digitare il numero legato all'azione che si vuole eseguire: ");
			
			//C) prende parole da tastiera tramite scanner:
			Scanner myInput = new Scanner(System.in); 
			String azione = "";
			try {
				azione = myInput.nextLine();
			} catch (NoSuchElementException nse) {
			//	nse.printStackTrace();
				System.out.println("inserimento non valido");
			}
			
			//D) Azioni eseguibili:
			switch (azione) {
			case "1" : 	//1) Visualizzazione contatti
				System.out.println("Hai selto di eseguire: " + menu[0]);
				RubricaHDB.visualizzaMenu(session);
				break;
									
			case "2" : 	//2) Modifica / Cancellazione
				System.out.println("Hai selto di eseguire: " + menu[1]);
				RubricaHDB.CancModMenu(session);
				break;
									
			case "3" :	//3) Inserimento contatto
				System.out.println("Hai selto di eseguire: " + menu[2]);
//				RubricaDB.inserisciContatto (connection); 
				break;
									
			case "4" : 	//4) Esporta rubrica
				System.out.println("Hai selto di eseguire: " + menu[3]);
				break;
	
			case "5" :	//5) Termina programma
				System.out.println("Hai selto di eseguire: " + menu[4]);
				break CORPO; //esce dal while
					
			default: 	//Azione incompresa
				System.out.println("La scelta non è andata a buon fine, prego riprovare.\n");
			} //switch
			System.gc();	//garbage collector
		} //while
			
		//E) chiusura connessione con il database (chiudo la sessione):
		session.close();
		System.out.println("session is open? " + session.isOpen());

		System.out.println("BYE!!");
	} //main

} //class
