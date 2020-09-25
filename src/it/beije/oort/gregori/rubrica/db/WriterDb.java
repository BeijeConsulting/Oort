package it.beije.oort.gregori.rubrica.db;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import it.beije.oort.rubrica.Contatto;

public class WriterDb {
	
	public static void writeContatti(List<Contatto> contatti) {
		for(Contatto contatto : contatti) {
			if(contatto.getTelefono().indexOf("'") != -1) {
				StringBuilder sb = new StringBuilder(contatto.getTelefono());
				sb.deleteCharAt(sb.indexOf("'"));
				contatto.setTelefono(sb.toString());
			}
			if(contatto.getNome().indexOf("'") != -1) {
				StringBuilder sb = new StringBuilder(contatto.getNome());
				sb.deleteCharAt(sb.indexOf("'"));
				contatto.setNome(sb.toString());
			}
			if(contatto.getCognome().indexOf("'") != -1) {
				StringBuilder sb = new StringBuilder(contatto.getCognome());
				sb.deleteCharAt(sb.indexOf("'"));
				contatto.setCognome(sb.toString());
			}
			if(contatto.getEmail().indexOf("'") != -1) {
				StringBuilder sb = new StringBuilder(contatto.getEmail());
				sb.deleteCharAt(sb.indexOf("'"));
				contatto.setEmail(sb.toString());
			}
			WriterDb.insert(contatto.getCognome(), contatto.getNome(), contatto.getTelefono(), contatto.getEmail());
		}
	}
	
	private static void insert(String cognome, String nome, String telefono, String email) {
		Connection connection = null;
		Statement statement = null;
		
		try {
			connection = DBManager.getMySqlConnection(DBManager.DB_URL, DBManager.DB_USER, DBManager.DB_PASSWORD);
			//System.out.println("Connected? " + !connection.isClosed());
			
			statement = connection.createStatement();
			
			statement.execute("INSERT INTO rubrica (cognome, nome, telefono, email) VALUES ('"+cognome+"', '"+nome+"', '"+telefono+"', '"+email+"')");
			
			System.out.println("Record inserted: " + statement.getUpdateCount());
			
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

}
