package it.beije.oort.girardi.dataBase;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.NoSuchElementException;

import it.beije.oort.db.DBManager;
import it.beije.oort.girardi.inOut.RubricaCSV;
import it.beije.oort.rubrica.Contatto;

public class RubricaDB {
	
// ------------ METODI ------------

	private static final String PATH_FILES = "C:\\Users\\Padawan05\\Desktop\\file_testo\\";
	private static String file_destinazione = "RubricaFromDB";
	
//1) Visualizzazione contatti -----------------------------------------
	
//visualizza tutti i contatti nella lista:
	public static void visualizzaTutti (Connection connection) {
		Scanner myInput = new Scanner(System.in);  //apre lo scanner
		PreparedStatement ps = null;
		ResultSet rs = null;
		int count = 0;

		try {
			ps = connection.prepareStatement("SELECT * FROM rubrica");
			rs = ps.executeQuery();			
			
			//fase di stampa a terminale:
			System.out.println("ID, COGNOME, NOME, TELEFONO, EMAIL");
			while (rs.next() ) {
				System.out.println(rs.getString(1)+"   "+rs.getString(2)+"   "+
					rs.getString(3)+"   "+rs.getString(4)+"   "+rs.getString(5));
					if (++count % 30 == 0) { //mostra 30 contatti alla volta
						System.out.print("digitare 1 per vedere la seconda pagina: ");
						String si = myInput.nextLine();
						if (!(si.contentEquals("1")))
							break;
					}
			}
			System.out.println("hai visualizzato " + count + " contatti\n");
			
		} catch (SQLException sqlException) {
			sqlException.printStackTrace();
		} finally {
			try {
				rs.close();
				ps.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
//ricerca e visualizza contatto per ID:
	public static void visualizzaId(Connection connection,int id) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {	
			ps = connection.prepareStatement("SELECT * FROM rubrica where id = ?");
			ps.setInt(1, id);
			rs = ps.executeQuery();
		
			rs.next();
			System.out.println("ID, COGNOME, NOME, TELEFONO, EMAIL");	
			System.out.println(rs.getString(1)+"   "+rs.getString(2)+"   "+
					rs.getString(3)+"   "+rs.getString(4)+"   "+rs.getString(5));
			
			rs.close();
		} catch (SQLException sqlException) {
//			sqlException.printStackTrace();
			System.out.println("Id non disponibile");
		} finally {
			try {
				ps.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	
// viualizza rubrica o contatto da Database
	public static void visualizza (Connection connection) {
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
				RubricaDB.visualizzaTutti(connection);
				break;
			case "2": 
				System.out.print("inserire l'id del contatto che si vuole visualizzare: ");
				id = (int) Integer.parseInt(myInput.nextLine());
				
				RubricaDB.visualizzaId(connection, id);
				break;
			default:
				System.out.println("inserimento non valido");
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
	
	
	
	
	
//2) Modifica / Cancellazione ---------------------------------
//cancella id dal database
	public static void deleteId(Connection connection,int id) {
		PreparedStatement ps = null;
		
		try {	
			ps = connection.prepareStatement("DELETE FROM rubrica where id = ?");
			ps.setInt(1, id);
			
			ps.execute();
		
			System.out.println("id contatto eliminiato");
			
		} catch (SQLException sqlException) {
			sqlException.printStackTrace();
		} finally {
			try {
				ps.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	
// Cancellazione contatto dal Database
	public static void cancellaContatto (Connection connection) {
		Scanner myInput = new Scanner(System.in);  //apre lo scanner
		int id = 0;		
		
			try {
				System.out.print("inserire l'id del contatto che si vuole eliminare:");
				id = (int) Integer.parseInt(myInput.nextLine());
				
				RubricaDB.deleteId(connection, id);
				
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
	
	
	
// Modifica contatto dal Database
	
	
	
	
	
//3) inserimento contatto nel DataBase ----------------------------------
	public static void inserisciContatto (Connection connection) {
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
		
		if (contatto.getNome().trim().equals("") && contatto.getCognome().trim().equals("") &&
			contatto.getTelefono().trim().equals("") && contatto.getEmail().trim().equals("") )
			System.out.println("ALERT: il contatto è vuoto e non verrà inserito\n");
		else {
			ToDB.insertContatto(connection, contatto);
			System.out.println("");
		}
	}
	
	
	
	
//4) esporta rubrica
//esporta in un file csv
	public static void esportaCSV (Connection connection) throws IOException {
		List<Contatto> listContatti = new ArrayList<>();
		
		//prendo la lista dei Contatti dal database:
		listContatti = FromDB.selectContatti(connection);
		
		//scrittura di un nuovo file csv con la List di Contatti:
		RubricaCSV.writeContatti(listContatti, PATH_FILES + file_destinazione);
		
		System.out.println("percorso file rubrica esportata: " + PATH_FILES + file_destinazione );
		System.out.println("");
	}
	
	
// -------------- MAIN -----------------
//Permette di gestire la rubrica di mySQL inserendo comandi da tastiera:
	public static void main(String[] args) throws IOException {
		//A) collegamento al DataBase (db):
		Connection connection = null;
		
		try {
			connection = DBManager.getMySqlConnection(DBManager.DB_URL, 
													  DBManager.DB_USER, 
													  DBManager.DB_PASSWORD);
			System.out.println("connection is open? " + !connection.isClosed());
	
			
			//B) interfaccia utente: menù di selezione:
			System.out.print("Benvenuto!");
			
//			CORPO: while (true) {
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
					nse.printStackTrace();
				}
				
				//D) Azioni eseguibili:
				switch (azione) {
				case "1" : 	//1) Visualizzazione contatti
					System.out.println("Hai selto di eseguire: " + menu[0]);
					RubricaDB.visualizza(connection);
					break;
										
				case "2" : 	//2) Modifica / Cancellazione
					System.out.println("Hai selto di eseguire: " + menu[1]);
					RubricaDB.cancellaContatto (connection);
					break;
										
				case "3" :	//3) Inserimento contatto
					System.out.println("Hai selto di eseguire: " + menu[2]);
					RubricaDB.inserisciContatto (connection); 
					break;
										
				case "4" : 	//4) Esporta rubrica
					System.out.println("Hai selto di eseguire: " + menu[3]);
					RubricaDB.esportaCSV(connection);
					break;

				case "5" :	//5) Termina programma
					System.out.println("Hai selto di eseguire: " + menu[4]);
					break CORPO; //esce dal while
						
				default: 	//Azione incompresa
					System.out.println("La scelta non è andata a buon fine, prego riprovare.\n");
				} //switch
				System.gc();	//garbage collector
			} //while
			
			
		//E) chiusura connessione con il database:
		} catch (ClassNotFoundException cnfEx) {
			cnfEx.printStackTrace();
		} catch (SQLException sqlException) {
			sqlException.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		System.out.println("BYE!!");
	} //main

} //class
