package it.beije.oort.girardi.dataBase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;

import it.beije.oort.db.DBManager;
import it.beije.oort.rubrica.Contatto;

public class RubricaDB {
	
// ------------ METODI ------------
	
//1) Visualizzazione contatti
	public static void visualizzaContatto (Connection connection) {
		
	}
	
	
//2) Cancellazione contatto dal Database
	public static void cancellaContatto (Connection connection) {
		Scanner myInput = new Scanner(System.in);  //apre lo scanner
//		Statement statement = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int id = 0;		
		
			try {
				System.out.print("inserire l'id del contatto che si vuole eliminare:");
				id = (int) Integer.parseInt(myInput.nextLine());
//				statement = connection.createStatement();
//				rs = statement.executeQuery("DELETE FROM rubrica r WHERE id = " + id );
				
				ps = connection.prepareStatement("DELETE FROM rubrica r WHERE id = ?");
				ps.setInt(1, id);
	
				rs = ps.executeQuery();			
				
				System.out.println("azione eseguita con successo");
				
				rs.close();
			} catch (NumberFormatException nfe) {
				nfe.printStackTrace();
				System.out.println("riprova");
			} catch (InputMismatchException ime) {
				ime.printStackTrace();
				System.out.println("riprova");
			} catch (NoSuchElementException nse) {
				nse.printStackTrace();
				System.out.println("riprova");
			} catch (SQLException sqlException) {
				sqlException.printStackTrace();	
			} finally {
				try {
//					statement.close();
					ps.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
	}
	
	
	
//2) Modifica contatto dal Database
	
	
//3) inserimento contatto nel DataBase
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

	
	
// -------------- MAIN -----------------
//Permette di gestire la rubrica di mySQL inserendo comandi da tastiera:
	public static void main(String[] args) {
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
