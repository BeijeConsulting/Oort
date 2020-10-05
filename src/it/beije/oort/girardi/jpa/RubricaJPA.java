package it.beije.oort.girardi.jpa;

import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import it.beije.oort.girardi.inOut.RubricaCSV;
import it.beije.oort.girardi.inOut.RubricaXML;
import it.beije.oort.rubrica.Contatto;

public class RubricaJPA {
	
	private static final String PATH_FILES = "C:\\Users\\Padawan05\\Desktop\\file_testo\\";
	private static String file_destinazione = "RubricaFromDB";

// ------------ METODI ------------
//1)VISUALIZZA;  2)MODIFICA/CANCELLA;  3)INSERIMENTO; 4)ESPORTA
	
//1) VISUALIZZA	
//ridà la list dei Contatti:
	public static List<Contatto> listContatti () {
		//apro EntityManagerFactory
		EntityManager entityManager = JPAfactory.openEntityFactory();
		
		//query JPQL
		String jpql = "SELECT c FROM Contatto as c";
		Query query = entityManager.createQuery(jpql);
		List<Contatto> contatti = query.getResultList();
		entityManager.close();
		
		return contatti;
	}
	
//visualizza tutti i contatti:
	public static void visualizzaTutti() {
		List<Contatto> listContatti = RubricaJPA.listContatti();
		int count = 0;
		Scanner myInput = new Scanner(System.in);  
		
		System.out.println("# contatti presenti: " + listContatti.size());
		System.out.println("ID, COGNOME, NOME, TELEFONO, EMAIL");
		for (Contatto c : listContatti) {
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
	
	public static void visualizzaId(int id) {
		//apro EntityManagerFactory
		EntityManager entityManager = JPAfactory.openEntityFactory();
		
		//SELECT
		Contatto contatto = entityManager.find(Contatto.class, id);
		System.out.println(contatto);
		
		entityManager.close();
	}
	
	
//2) MODIFICA/CANCELLA
//elimina contatto tramite id
	public static void cancellaContatto(int id) {
		//apro EntityManagerFactory
		EntityManager entityManager = JPAfactory.openEntityFactory();
		//SELECT
		Contatto contatto = entityManager.find(Contatto.class, id);
		System.out.println(contatto);
		if (contatto == null) {
			System.out.println("non sono presenti contatti con l'id richiesto");
			entityManager.close();
			return;
		}
		//apro transazione
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
				
		//entityManager.remove(entityManager.find(Contatto.class, id));
		entityManager.remove(contatto);
		entityManager.getTransaction().commit();
		
		System.out.println("contatto eliminato");
		entityManager.close();
	}
	
//modifica contatto tramite id
	public static void modificaContatto(int id) {
		//apro EntityManagerFactory
		EntityManager entityManager = JPAfactory.openEntityFactory();
		//SELECT
		Contatto contatto = entityManager.find(Contatto.class, id);
		System.out.println(contatto);
		if (contatto == null) {
			System.out.println("non sono presenti contatti con l'id richiesto");
			entityManager.close();
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
		
		//apro transazione
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();

		entityManager.persist(contatto); //UPDATE
		
		entityManager.getTransaction().commit();
		entityManager.close();
		System.out.println("Contatto aggiornato con successo:\n"+ contatto);
		
		//query JPQL
//		String jpql = "UPDATE rubrica SET nome='" + contatto.getNome() + "', cognome='"
//					+ contatto.getCognome() + "',telefono='" + contatto.getTelefono() 
//					+ "', email='" + contatto.getEmail() + "' WHERE id = " + id;
//		Query query = entityManager.createQuery(jpql);
//		entityManager.close();
			
		System.gc();
			
	}
	
	
//3) INSERIMENTO	
	public static void inserisciContatto(Contatto c) {	
		//apro EntityManagerFactory
		EntityManager entityManager = JPAfactory.openEntityFactory();
		
		//entityManager.getTransaction().begin();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
	
		if (c.getNome().trim().equals("") && c.getCognome().trim().equals("") &&
			c.getTelefono().trim().equals("") && c.getEmail().trim().equals("") ) {
			System.out.println("ALERT: il contatto è vuoto e non verrà inserito\n");
			//annullo transizione
			entityManager.getTransaction().rollback();
		} else {
			entityManager.persist(c);
			System.out.println("id assegnato: " + c.getId());
			System.out.println("contatto inserito: " + c);
			//confermo aggiornamento su DB
			entityManager.getTransaction().commit();
		}
		entityManager.close();
	}
	
//4) ESPORTA
	//menu
	public static void menuExport () 
			throws ParserConfigurationException, TransformerException, IOException {
		Scanner myInput = new Scanner(System.in);  //apre lo scanner
		int id = 0;	
		String in = "";
		
		try {
			System.out.println("digitare 1 o 2 per le seguenti azioni: ");
			System.out.println("\t 1) esporta in formato csv");
			System.out.println("\t 2) esporta in formato xml");
			in = myInput.nextLine();
			
			switch (in) {
			case "1":  // csv
				RubricaJPA.esportaCSV();
				break;
			case "2":  // xml
				RubricaJPA.esportaXML();
				break;
			default:
				System.out.println("");
			}
			
		} catch (NumberFormatException nfe) {
//				nfe.printStackTrace();
			System.out.println("inserimento non valido");
		} catch (InputMismatchException ime) {
			ime.printStackTrace();
			System.out.println("riprova");
		} catch (NoSuchElementException nse) {
			nse.printStackTrace();
			System.out.println("riprova");
		}
	}
	
//esporta in un file xml
	public static void esportaXML () 
		throws ParserConfigurationException, TransformerException, IOException {
		List<Contatto> listContatti = new ArrayList<>();
		
		//prendo la lista dei Contatti dal database:
		listContatti = RubricaJPA.listContatti();
		
		//scrittura di un nuovo file xml con la List di Contatti:
		RubricaXML.writeContatti(listContatti, PATH_FILES + file_destinazione + ".xml");
		
		System.out.println("percorso file rubrica esportata: " + PATH_FILES 
												+ file_destinazione + ".xml");
		System.out.println("");
	}

//esporta in un file csv
	public static void esportaCSV () throws IOException {
		List<Contatto> listContatti = new ArrayList<>();
		
		//prendo la lista dei Contatti dal database:
		listContatti = RubricaJPA.listContatti();
		
		//scrittura di un nuovo file csv con la List di Contatti:
		RubricaCSV.writeContatti(listContatti, PATH_FILES + file_destinazione + ".csv");
		
		System.out.println("percorso file rubrica esportata: " + PATH_FILES 
												+ file_destinazione + ".csv");
		System.out.println("");
	}
	
	
	

	
// -------------- MAIN -----------------
	public static void main (String[] args) {
		int id = 73;
		RubricaJPA.cancellaContatto(id);
		
//		//apro EntityManagerFactory
//		EntityManager entityManager = JPAfactory.openEntityFactory();
//		//SELECT
//		Contatto contatto = entityManager.find(Contatto.class, id);
//		System.out.println(contatto);
//		if (contatto == null) {
//			System.out.println("non sono presenti contatti con l'id richiesto");
//			entityManager.close();
//			return;
//		}
//		
//		//query JPQL
//		String jpql = "UPDATE rubrica SET telefono='777' WHERE id = 35"; //+ id;
//		System.out.println(jpql);
//		Query query = entityManager.createQuery(jpql);
//		entityManager.close();
			
	}
}
