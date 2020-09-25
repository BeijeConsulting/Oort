package it.beije.oort.gregori.rubrica.clientdb;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import it.beije.oort.gregori.parser.WriterCsv;
import it.beije.oort.gregori.parser.WriterXml;
import it.beije.oort.gregori.rubrica.db.*;
import it.beije.oort.rubrica.Contatto;

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
	 * Metodo che carica tutti i contatti del db in una map<id, contatto>
	 * chiede all'utente quanti records visualizzare per pagina
	 * per uscire dalla visualizzazione l'utente deve inserire il carattere 'q'
	 * per visualizzare la pagina seguente può inserire un carattere qualsiasi
	 * 
	 * @throws IOException
	 * @throws ParserConfigurationException
	 * @throws TransformerException
	 */
	private static void visualizzaContatti() throws IOException, ParserConfigurationException, TransformerException {
		Map<Integer, Contatto> contatti = Menu.readContatti();
		Scanner sc = new Scanner(System.in);
		
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
		Iterator<Integer> itr = contatti.keySet().iterator();		
		do {
			int maxSize = ((value + i) > contatti.size() ? contatti.size() : (value + i));
			while(i < maxSize) {
				Integer key = itr.next();
				System.out.println(key + ") " + contatti.get(key));
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
		showMenu();
	}
	
	/**
	 * Metodo di utility che ritorna una map dove le chiavi sono gli 'id' del db
	 * e le values sono i conttatti presenti sul db
	 * 
	 * @return
	 */
	private static Map<Integer, Contatto> readContatti() {
		Map<Integer, Contatto> contatti = new HashMap<Integer, Contatto>();
		
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			connection = DBManager.getMySqlConnection(DBManager.DB_URL, DBManager.DB_USER, DBManager.DB_PASSWORD);
			//System.out.println("connection is open? " + !connection.isClosed());
			
			ps = connection.prepareStatement("SELECT id, cognome, nome, telefono, email FROM rubrica.rubrica");
			
			rs = ps.executeQuery();
			
			while (rs.next()) {
				Contatto c = new Contatto();
				c.setCognome(rs.getString("cognome"));
				c.setNome(rs.getString("nome"));
				c.setTelefono(rs.getString("telefono"));
				c.setEmail(rs.getString("email"));
				contatti.put(rs.getInt("id"), c);
				//contatti.add(c);
			}
		} catch (SQLException sqlException) {
			sqlException.printStackTrace();
		} catch (ClassNotFoundException cnfEx) {
			cnfEx.printStackTrace();
		} finally {
			try {
				rs.close();
				ps.close();
				connection.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
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
		
		Scanner sc = new Scanner(System.in);
		
		String search = sc.nextLine();
		Map<Integer, Contatto> contatti = select(search);
		contatti.forEach((key, value) -> System.out.println(key + ") " + value));
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
	private static Map<Integer, Contatto> select(String search){
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Map<Integer, Contatto> contatti = new HashMap<Integer, Contatto>();
		
		try {
			connection = DBManager.getMySqlConnection(DBManager.DB_URL, DBManager.DB_USER, DBManager.DB_PASSWORD);
			
			if(search.contains("%")) {
				ps = connection.prepareStatement("SELECT id, cognome, nome, telefono, email "
						+ "FROM rubrica "
						+ "WHERE id like ? OR nome like ? OR cognome like ? OR telefono like ? OR email like ?");
				ps.setString(1, search);
				ps.setString(2, search);
				ps.setString(3, search);
				ps.setString(4, search);
				ps.setString(5, search);
			}
			else {
				ps = connection.prepareStatement("SELECT id, cognome, nome, telefono, email "
						+ "FROM rubrica "
						+ "WHERE id = ? OR nome = ? OR cognome = ? OR telefono = ? OR email = ?");
				ps.setString(1, search);
				ps.setString(2, search);
				ps.setString(3, search);
				ps.setString(4, search);
				ps.setString(5, search);
			}
			
			
			rs = ps.executeQuery();
			
			while (rs.next()) {
				Contatto contatto = new Contatto();
				contatto.setNome(rs.getString("nome"));
				contatto.setCognome(rs.getString("cognome"));
				contatto.setEmail(rs.getString("email"));
				contatto.setTelefono(rs.getString("telefono"));
				contatti.put(rs.getInt("id"), contatto);
			}
		} catch (SQLException sqlException) {
			sqlException.printStackTrace();
		} catch (ClassNotFoundException cnfEx) {
			cnfEx.printStackTrace();
		} finally {
			try {
				rs.close();
				ps.close();
				connection.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
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
		Map<Integer, Contatto> contatti = Menu.readContatti();
	
		System.out.println("Inserisci l'id del contatto da modificare: ");		
		Scanner sc = new Scanner(System.in);
		int id = Integer.parseInt(sc.nextLine());
		if(!contatti.containsKey(id)) {
			System.out.println("L'id inserito non è presente nel database!");
			Menu.modificaContatto();
		}
		
		Contatto contatto = creaContatto();
		update(id, contatto);
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
		
		Scanner sc = new Scanner(System.in);
		
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
	 * Metodo di utility che sostituisce il contatto presente sul db all'id 
	 * passato come parametro con il contatto passato.
	 * 
	 * @param id
	 * @param contatto
	 */
	private static void update(int id, Contatto contatto) {
		Connection connection = null;
		Statement statement = null;
		
		try {
			connection = DBManager.getMySqlConnection(DBManager.DB_URL, DBManager.DB_USER, DBManager.DB_PASSWORD);
			
			statement = connection.createStatement();
			
			statement.execute("UPDATE rubrica "
							+ "SET nome = '" + contatto.getNome() + "', " + 
								"cognome = '"  + contatto.getCognome() + "', " +
								"email = '"  + contatto.getEmail() + "', " +
								"telefono = '"  + contatto.getTelefono() + "'"
							+ "WHERE id = '" + id + "'");
			
			//System.out.println("Record inserted: " + statement.getUpdateCount());
			System.out.println("Contatto aggiornato correttamente!");
			
		} catch (SQLException sqlException) {
			sqlException.printStackTrace();
		} catch (ClassNotFoundException cnfEx) {
			cnfEx.printStackTrace();
		} finally {
			try {
				statement.close();
				connection.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
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
		
		Scanner sc = new Scanner(System.in);
		int id = Integer.parseInt(sc.nextLine());
		delete(id);
		Menu.showMenu();
	}
	
	/**
	 * Metodo di utility che elimina un record dal db con l'id indicato
	 * 
	 * @param id
	 */
	private static void delete(int id) {
		Connection connection = null;
		Statement statement = null;
		
		try {
			connection = DBManager.getMySqlConnection(DBManager.DB_URL, DBManager.DB_USER, DBManager.DB_PASSWORD);
			
			statement = connection.createStatement();
			
			statement.execute("DELETE FROM rubrica WHERE id = '" + id + "'");
			
			//System.out.println("Record inserted: " + statement.getUpdateCount());
			System.out.println("Contatto eliminato correttamente!");
			
		} catch (SQLException sqlException) {
			sqlException.printStackTrace();
		} catch (ClassNotFoundException cnfEx) {
			cnfEx.printStackTrace();
		} finally {
			try {
				statement.close();
				connection.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
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
		Connection connection = null;
		Statement statement = null;
		
		try {
			connection = DBManager.getMySqlConnection(DBManager.DB_URL, DBManager.DB_USER, DBManager.DB_PASSWORD);
			//System.out.println("Connected? " + !connection.isClosed());
			
			statement = connection.createStatement();
			
			statement.execute("INSERT INTO rubrica (cognome, nome, telefono, email) "
							+ "VALUES ('"+contatto.getCognome()+"', '"+contatto.getNome()+
										"', '"+contatto.getTelefono()+"', '"+contatto.getEmail()+"')");
			System.out.println("Contatto inserito correttamente!");
		} catch (SQLException sqlException) {
			sqlException.printStackTrace();
		} catch (ClassNotFoundException cnfEx) {
			cnfEx.printStackTrace();
		} finally {
			try {
				statement.close();
				connection.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
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
		Scanner sc = new Scanner(System.in);
		
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
	}
}
