package it.beije.oort.gregori.rubrica.clientdb.hibernate;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import it.beije.oort.gregori.parser.WriterCsv;
import it.beije.oort.gregori.parser.WriterXml;
import it.beije.oort.gregori.rubrica.db.*;
import it.beije.oort.rubrica.Contatto;
import it.beije.oort.rubrica.HybSessionFactory;

/**
 * Client da riga di comando per la gestione di un database "rubrica".
 * 
 * Funzionalità del programma: 
 * VISUALIZZAZIONE CONTATTI
 * 	ricerca
 * 	visualizza tutti i contatti (divisi per pagina o per iniziale)
 * MODIFICA/CANCELLAZIONE CONTATTI
 * 	chiedere ID contatto
 * INSERIMENTO CONTATTO
 * 	inserimento diretto in DB con alert presenza almeno un campo
 * EXPORT
 * 	funzionalità esportazione dati su file csv/xml con nome file indicato
 * 
 * @author Luca Gregori
 *
 */
public class Menu {
	private static Scanner sc = new Scanner(System.in);
	
	/**
	 * Metodo che si occupa della chiamata delle varie funzioni
	 * tramite una scelta fatta dall'utente
	 * 
	 * @throws IOException
	 * @throws ParserConfigurationException
	 * @throws TransformerException
	 */
	public static void showMenu() throws IOException, ParserConfigurationException, TransformerException {
		System.out.println("-----CMD client per database rubrica-----");
		System.out.println("Menu: ");
		System.out.println("1) Visualizzatore contatti.");
		System.out.println("2) Ricerca contatto/i.");
		System.out.println("3) Modifica contatto.");
		System.out.println("4) Rimozione contatto.");
		System.out.println("5) Inserimento contatto.");
		System.out.println("6) Export database.");
		System.out.println("7) Termina programma.");
		
		Scanner sc = new Scanner(System.in);
		int scelta = Integer.parseInt(sc.nextLine());
		switch(scelta) {
			case 1:
				visualizzaContatti();
				break;
			case 2:
				ricercaContatto();
				break;
			case 3:
				modificaContatto();
				break;
			case 4:
				rimuoviContatto();
				break;
			case 5:
				inserisciContatto();
				break;
			case 6:
				exportDatabase();
				break;
			case 7:
				break;
			default:
				System.out.println("ERRORE: inserire una scelta valida!");
				showMenu();
		}
		sc.close();
	}

	/**
	 * Metodo che carica tutti i contatti del db in una lista
	 * chiede all'utente quanti records visualizzare per pagina
	 * per uscire dalla visualizzazione l'utente deve inserire il carattere 'q'
	 * per visualizzare la pagina seguente può inserire un carattere qualsiasi
	 * 
	 * @throws IOException
	 * @throws ParserConfigurationException
	 * @throws TransformerException
	 */
	private static void visualizzaContatti() throws IOException, ParserConfigurationException, TransformerException {
		List<Contatto> contatti = Menu.readContatti();
		
		System.out.println("Caricati " + contatti.size() + " contatti.");
		int value = 0; 
		
		System.out.println("Quanti contatti vuoi visualizzare per pagina?");
		
		try {
			value = Integer.parseInt(sc.nextLine());
		} catch(NumberFormatException e) {
			System.out.println("ERRORE: Inserire un numero valido!");
			Menu.visualizzaContatti();
			System.exit(0);
		}
		
		int i = 0;
		char scelta = ' ';
		do {
			int maxSize = ((value + i) > contatti.size() ? contatti.size() : (value + i));
			while(i < maxSize) {
				System.out.println(contatti.get(i).getId() + ") " + contatti.get(i));
				i++;
			}

			if(i >= contatti.size()) {
				System.out.println("Non ci sono più pagine da visualizzare");
				System.out.println("Premere q per terminare la visualizzazione.");
			}
			else {
			System.out.println("Premere q per terminare la visualizzazione.");
			System.out.println("Premere un altro carattere per passare alla pagina successiva.");
			}
			scelta = sc.nextLine().charAt(0);
		} while(scelta != 'q');
		Menu.showMenu();
	}
	
	/**
	 * Metodo di utility che ritorna una map dove le chiavi sono gli 'id' del db
	 * e le values sono i conttatti presenti sul db
	 * 
	 * @return
	 */
	private static List<Contatto> readContatti() {
		String hql = "SELECT c FROM Contatto as c";
		Session session = HybSessionFactory.openSession();
		List<Contatto> contatti = session.createQuery(hql).list();
		session.close();
		
		return contatti;
	}

	/**
	 * Metodo che permette di effettuare la ricerca di un contatto sul database
	 * Per effettuare la ricerca l'utente può inserire una semplice stringa
	 * che verra confrontata con id, nome, cognome, mail e telefono per trovare un riscontro
	 * Se l'utente inserisce il carattere % nella stringa, verrà effettuata una 
	 * ricerca utilizzando il LIKE di sql sui campi id, nome, cognome, mail e telefono.
	 * 
	 * @throws IOException
	 * @throws ParserConfigurationException
	 * @throws TransformerException
	 */
	private static void ricercaContatto() throws IOException, ParserConfigurationException, TransformerException {
		System.out.println("Inserisci cosa vuoi cercare:");
		System.out.println("Inserire % se si vuole cercare in stile SQL");
		
		String search = sc.nextLine();
		List<Contatto> contatti = Menu.select(search);
		
		for(Contatto contatto : contatti) {
			System.out.println(contatto.getId() + ") " + contatto);
		}
		if(contatti.size() == 0) {
			System.out.println("La ricerca non ha prodotto risultati.");
		}
		System.out.println("Vuoi cercare ancora? (s per cercare)");
		if(sc.nextLine().charAt(0) == 's') {
			ricercaContatto();
		}
		else {
			Menu.showMenu();
		}
	}
	
	/**
	 * Metodo di utility che restituisce tutti i records
	 * che corrispondono alla String search
	 * Se search contiene uno o più % la sintatti utilizzata
	 * per la ricerca è quella SQL, utilizzando quindi il LIKE
	 * 
	 * @param search
	 * @return
	 */
	private static List<Contatto> select(String search){
		Session session = HybSessionFactory.openSession();
		
		String hql = "";
		
			if(search.contains("%")) {
				hql = ("SELECT c "
						+ "FROM Contatto as c "
						+ "WHERE id like '" + search + "' OR nome like '" + search + "' OR cognome like '" + search + "' OR telefono like '" + search + "' OR email like  '" + search + "'");
			}
			else {
				hql = ("SELECT c "
						+ "FROM Contatto as c "
						+ "WHERE id = '" + search + "' OR nome = '" + search + "' OR cognome = '" + search + "' OR telefono = '" + search + "' OR email = '" + search + "'");
			}
			
			Query<Contatto> query = session.createQuery(hql);
			List<Contatto> contatti = query.list();
			
			session.close();
			
		return contatti;
	}
	
	/**
	 * Metodo che sostituisce il contatto presente sul db all'id indicato dall'utente
	 * con un nuovo contatto inserito dall'utente.
	 * 
	 * @throws IOException
	 * @throws ParserConfigurationException
	 * @throws TransformerException
	 */
	private static void modificaContatto() throws IOException, ParserConfigurationException, TransformerException {
		List<Contatto> tempList = Menu.readContatti();
		Map<Integer, Contatto> contatti = new HashMap<Integer, Contatto>();
		
		for(Contatto contatto : tempList) {
			contatti.put(contatto.getId(), contatto);
		}
	
		System.out.println("Inserisci l'id del contatto da modificare: ");		
		int id = Integer.parseInt(sc.nextLine());
		if (!contatti.containsKey(id)) {
			System.out.println("L'id inserito non è presente nel database!");
			Menu.modificaContatto();
		} 
		
		Session session = HybSessionFactory.openSession();
		
		Transaction transaction = session.beginTransaction();
		Contatto contatto = session.get(Contatto.class, id);
		Contatto temp = Menu.creaContatto();
		contatto.setCognome(temp.getCognome());
		contatto.setNome(temp.getNome());
		contatto.setTelefono(temp.getTelefono());
		contatto.setEmail(temp.getEmail());
		session.save(contatto);
		transaction.commit();
		
		session.close();
		
		Menu.showMenu();
	}
	
	/**
	 * Metodo di utility che crea un contatto con dati inseriti dall'utente.
	 * 
	 * @return
	 * @throws IOException
	 * @throws ParserConfigurationException
	 * @throws TransformerException
	 */
	private static Contatto creaContatto() throws IOException, ParserConfigurationException, TransformerException {
		
		System.out.println("Inserimento di un contatto.");
		Contatto contatto = new Contatto();
		System.out.print("Nome: ");
		contatto.setNome(sc.nextLine());
		System.out.print("Cognome: ");
		contatto.setCognome(sc.nextLine());
		System.out.print("Telefono: ");
		contatto.setTelefono(sc.nextLine());
		System.out.print("Email: ");
		contatto.setEmail(sc.nextLine());
		int counter = 0;
		if(contatto.getNome().equals(" ")) { counter++; }
		if(contatto.getCognome().equals(" ")) { counter++; }
		if(contatto.getEmail().equals(" ")) { counter++; }
		if(contatto.getTelefono().equals(" ")) { counter++; }
		if(counter == 4) {
			System.out.println("ERRORE: Almeno un campo deve essere inserito!");
			Menu.inserisciContatto();
		}
		System.out.println(contatto);
		System.out.println();
		
		return contatto;
	}

	/**
	 * Metodo che rimuove un contatto presente sul db con
	 * l'id inserito dall'utente.
	 * 
	 * @throws IOException
	 * @throws ParserConfigurationException
	 * @throws TransformerException
	 */
	private static void rimuoviContatto() throws IOException, ParserConfigurationException, TransformerException {
		System.out.println("Inserisci l'id del contatto da eliminare: ");
		
		int id = Integer.parseInt(sc.nextLine());
		Menu.delete(id);
		Menu.showMenu();
	}
	
	/**
	 * Metodo di utility che elimina un record dal db con l'id indicato
	 * 
	 * @param id
	 */
	private static void delete(int id) {
		Session session = HybSessionFactory.openSession();
		
		Transaction transaction = session.beginTransaction();
		session.delete(session.get(Contatto.class, id));
		transaction.commit();
		
		session.close();
		
		System.out.println("Contatto eliminato correttamente!");
	}

	/**
	 * Metodo che inserisce nel db un contatto inserito dall'utente
	 * 
	 * @throws IOException
	 * @throws ParserConfigurationException
	 * @throws TransformerException
	 */
	private static void inserisciContatto() throws IOException, ParserConfigurationException, TransformerException {
		Contatto contatto = creaContatto();
		Menu.insert(contatto);
		Menu.showMenu();		
	}
	
	/**
	 * Metodo di utility che inserisce nel db un contatto
	 * 
	 * @param contatto
	 */
	private static void insert(Contatto contatto) {
		Session session = HybSessionFactory.openSession();
		
		Transaction transaction = session.beginTransaction();
		session.save(contatto);
		transaction.commit();
		session.close();
	}

	/**
	 * Metodo che chiede all'utente il nome del file ed il formato 
	 * con cui esportare i dati presenti sul db.
	 * 
	 * @throws IOException
	 * @throws ParserConfigurationException
	 * @throws TransformerException
	 */
	private static void exportDatabase() throws IOException, ParserConfigurationException, TransformerException {
		
		System.out.println("Inserire il nome del file: (formato non necessario)");
		String fileName = sc.nextLine();
		
		System.out.println("In che formato vuoi esportare il file?");
		System.out.println("1) .csv");
		System.out.println("2) .xml");
		
		List<Contatto> contatti = ReaderDb.readContatti();
		
		int sceltaScrittura = Integer.parseInt(sc.nextLine());
		
		if (sceltaScrittura == 1) {
			// csv
			WriterCsv.writeContatti(contatti, new File("/temp/" + fileName + ".csv"));
			System.out.println("File csv salvato correttamente in: \"/temp/" + fileName + ".csv\"");
		}
		if (sceltaScrittura == 2) {
			// xml
			WriterXml.writeContatti(contatti, new File("/temp/" + fileName + ".xml"));
			System.out.println("File xml salvato correttamente in: \"/temp/" + fileName + ".xml\"");
		}	
		Menu.showMenu();
	}
	
	public static void main(String[] args) throws IOException, ParserConfigurationException, TransformerException {
		Menu.showMenu();
		Menu.sc.close();
	}
}
