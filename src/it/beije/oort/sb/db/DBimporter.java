package it.beije.oort.sb.db;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

import java.sql.SQLException;

import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import it.beije.oort.rubrica.*;
public class DBimporter {
	
	public static void preparedInsert(List<Contatto> list) {
		Connection connection = null;
		PreparedStatement ps = null;
		
		try {
			connection = DBManager.getMySqlConnection(DBManager.DB_URL, DBManager.DB_USER, DBManager.DB_PASSWORD);
			
			ps = connection.prepareStatement("INSERT INTO rubrica (cognome, nome, telefono, email) VALUES (?, ?, ?, ?)");
			for(Contatto c: list) {
			ps.setString(1, c.getCognome());
			ps.setString(2, c.getNome());
			ps.setString(3, c.getTelefono());
			ps.setString(4, c.getEmail());
			ps.execute();
			}
			
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
	
	public static void individualInsert(Contatto c, int ind) {
		Connection connection = null;
		PreparedStatement ps = null;
		
		try {
			connection = DBManager.getMySqlConnection(DBManager.DB_URL, DBManager.DB_USER, DBManager.DB_PASSWORD);
			
			ps = connection.prepareStatement("INSERT INTO rubrica (cognome, nome, telefono, email, id) VALUES (?, ?, ?, ?, ?)");
			ps.setString(1, c.getCognome());
			ps.setString(2, c.getNome());
			ps.setString(3, c.getTelefono());
			ps.setString(4, c.getEmail());
			ps.setInt(5, ind);
			ps.execute();
			
			
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
	
	
	public static void main(String[] args) throws IOException, ParserConfigurationException, SAXException {
		File file = new File("./src/it/beije/oort/rubrica/rubrica2.xml");
		System.out.println(file.exists());
		preparedInsert(RubricaCsvXml.LetturaFile(file));
	}
	
	

}
