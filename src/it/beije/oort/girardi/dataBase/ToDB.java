package it.beije.oort.girardi.dataBase;

import java.io.File;
import java.util.List;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import it.beije.oort.db.DBManager;
import it.beije.oort.rubrica.Contatto;
import it.beije.oort.girardi.inOut.RubricaCSV;

public class ToDB {

	private static final String PATH_FILES = "C:\\Users\\Padawan05\\Desktop\\file_testo\\";
	private static int count = 0;
	
// ------------ METODI ------------
	
//inserisce un contatto nel database. 
	public static void insertContatto(Connection connection,Contatto contatto) {
		PreparedStatement ps = null;
		
		try {	
			ps = connection.prepareStatement("INSERT INTO rubrica "
					+ "(cognome, nome, telefono, email) VALUES (?, ?, ?, ?)");
			ps.setString(1, contatto.getCognome());
			ps.setString(2, contatto.getNome());
			ps.setString(3, contatto.getTelefono());
			ps.setString(4, contatto.getEmail());
			
			ps.execute();
			
		//	System.out.println("insert record : " + ++count);
			System.out.println("contatto inserito correttamente");
			
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
	
	
//inserisce la List di contatti nel database. Usa il metodo ToDB.insertContatto
	public static void insertListContatti(Connection connection,List<Contatto> listContatti) {
		for (Contatto contatto : listContatti)
			ToDB.insertContatto(connection,contatto);
		
	}
	
	
// -------------- MAIN -----------------
//Data una rubrica formato csv la legge e inserisce la lista di contatti nel
//database di mySQL.
	public static void main(String[] args) throws IOException {
		//lettura:
		File file = new File(PATH_FILES + "rubricaNonComplProvv.csv");
		if (file.exists()) 
			System.out.println("il file esiste, lo carico... ");
		else
			System.out.println("il file che si vuole caricare non esiste");
		
		//metodo per la List di contatti:
		List<Contatto> listContatti = RubricaCSV.getListContatti(file);  
		
		//mi collego al DataBase (db):
		Connection connection = null;
		
		try {
			connection = DBManager.getMySqlConnection(DBManager.DB_URL, 
													  DBManager.DB_USER, 
													  DBManager.DB_PASSWORD);
			System.out.println("connection is open? " + !connection.isClosed());
	
			//inserisco la lista di contatti nel database:
			ToDB.insertListContatti(connection, listContatti);
		
		
		} catch (ClassNotFoundException cnfEx) {
			cnfEx.printStackTrace();
		} catch (SQLException sqlException) {
			sqlException.printStackTrace();
//		} catch (Exception e) {
//			e.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		
	} //main

} //class
