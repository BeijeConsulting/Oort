package it.beije.oort.db;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import it.beije.oort.rubrica.Contatto;
import it.beije.oort.team.map.ReaderCsv;

public class ImportDB {
	
	public static final String DB_USER = "root";
	public static final String DB_PASSWORD = "simone9810";
	public static final String DB_URL = "jdbc:mysql://localhost:3306/rubrica?serverTimezone=CET";
	
	public static Connection getMySqlConnection(String url, String user, String password) throws SQLException, ClassNotFoundException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection connection = DriverManager.getConnection(url, user, password);
		return connection;
	}
	
	public static void preparedInsert(List<Contatto> contatti) throws IOException {
		Connection connection = null;
		PreparedStatement ps = null;
		int count = 0 ;
		try {
			connection = ImportDB.getMySqlConnection(ImportDB.DB_URL, ImportDB.DB_USER, ImportDB.DB_PASSWORD);
			System.out.println("connection is open? " + !connection.isClosed());
			
			for(int i=0; i<contatti.size(); i++) {
				String name = contatti.get(i).getNome();
				String surname = contatti.get(i).getCognome();
				String phone = contatti.get(i).getTelefono();
				String email = contatti.get(i).getEmail();
				ps = connection.prepareStatement("INSERT INTO contatti (nome, cognome,telefono, email) VALUES (?, ?, ?, ?)");
				ps.setString(1, name);
				ps.setString(2, surname);
				ps.setString(3, phone);
				ps.setString(4, email);
				ps.execute();
				count ++;
			}
			System.out.println("Record importati: " + count);
		} catch (SQLException sqlException) {
			sqlException.printStackTrace();
		} catch (ClassNotFoundException cnfEx) {
			cnfEx.printStackTrace();
		} finally {
			try {
				ps.close();
				connection.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) throws SQLException, ClassNotFoundException, IOException {
		Connection connection = getMySqlConnection(DB_URL, DB_USER, DB_PASSWORD);
		File csvFile = new File("/temp/rubrica_maisto.csv");
		List<Contatto> contatti = ReaderCsv.readContatti(csvFile);
		preparedInsert(contatti);
	}
}
