package it.beije.oort.girardi.dataBase;

import java.io.File;
import java.util.List;
import java.util.ArrayList;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import it.beije.oort.db.DBManager;
import it.beije.oort.rubrica.Contatto;
import it.beije.oort.girardi.inOut.RubricaCSV;

public class FromDB {

	private static final String PATH_FILES = "C:\\Users\\Padawan05\\Desktop\\file_testo\\";
	private static int count = 0;
	
// ------------ METODI ------------
	
//seleziona la List di Contatti contenuta nel database. 
	public static List<Contatto> selectContatti(Connection connection) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Contatto> listContatti = new ArrayList<>();
		
		try {
			ps = connection.prepareStatement("SELECT * FROM rubrica");
			rs = ps.executeQuery();
			
			while (rs.next()) {
				//costruttore Contatto: nome, cognome, telefono, email
				// 					DB: cognome, nome, telefono, email
				// la colonna 1 è dedicata all' id del contatto
				listContatti.add(new Contatto(rs.getString(3),
											  rs.getString(2),
											  rs.getString(4),
											  rs.getString(5)));
			}
			
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
	
		return listContatti;
	}
	
	
//inserisce la List di conttti nel database. Usa il metodo ToDB.insertContatto
	public static void insertListContatti(Connection connection,List<Contatto> listContatti) {
		for (Contatto contatto : listContatti)
			ToDB.insertContatto(connection,contatto);
		
	}
	
	
// -------------- MAIN -----------------
//Dato il database "rubrica" di mySQL, legge i contatti i li inserisce 
//in un file csv.
	public static void main(String[] args) throws IOException {
		//mi collego al DataBase (db):
		Connection connection = null;
		List<Contatto> listContatti = new ArrayList<>();
		
		try {
			connection = DBManager.getMySqlConnection(DBManager.DB_URL, 
													  DBManager.DB_USER, 
													  DBManager.DB_PASSWORD);
			System.out.println("connection is open? " + !connection.isClosed());
	
			//prendo la lista dei Contatti dal database:
			listContatti = FromDB.selectContatti(connection);
		
		
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
		
		//scrittura di un nuovo file con la List di Contatti:
		RubricaCSV.writeContatti(listContatti, PATH_FILES + "rubricaFromDB.csv");
		
		
	} //main

} //class
