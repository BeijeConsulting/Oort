package it.beije.oort.madonia.rubrica.db;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.xml.sax.SAXException;

import it.beije.oort.madonia.rubrica.Contatto;
import it.beije.oort.madonia.rubrica.ParserCsvRubrica;
import it.beije.oort.madonia.rubrica.ParserXmlRubrica;
import it.beije.oort.madonia.rubrica.WriterCsvRubrica;
import it.beije.oort.madonia.rubrica.WriterXmlRubrica;

public class DBRubrica {
	
	public static void main(String[] args) {
		System.out.println(exportRubricaFromDB("/temp/rubrica/rubrica_database.xml"));
	}
	
	public static boolean importFile(String pathfile) {
		return DBRubrica.importFile(new File(pathfile));
	}
	
	public static boolean importFile(File file) {
		boolean successo = false;
		try {
			List<Contatto> contatti = ParserCsvRubrica.creaListaContatti(file);
			successo = importContatti(contatti);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return successo;
	}

	public static boolean importContatti(List<Contatto> contatti) {
		boolean successo = false;
		Connection connection = null;
		PreparedStatement ps = null;
		
		try {
			connection = DBManager.getMySqlConnection(DBManager.DB_URL, DBManager.DB_USER, DBManager.DB_PASSWORD);
			ps = connection.prepareStatement(RubricaPreparedStatements.INSERT_CONTATTO_IN_RUBRICA);
			
			for(Contatto contatto : contatti) {
				ps.setString(1, contatto.getNome());
				ps.setString(2, contatto.getCognome());
				ps.setString(3, contatto.getTelefono());
				ps.setString(4, contatto.getEmail());
				
				ps.execute();
			}
			
			successo = true;
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				ps.close();
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return successo;
	}
	
	public static boolean exportRubricaFromDB(String pathfile) {
		return exportRubricaFromDB(new File(pathfile));
	}
	
	public static boolean exportRubricaFromDB(File file) {
		boolean successo = false;
		List<Contatto> contatti = getContattiFromDB();
		String[] intestazione = {"NOME","COGNOME","TELEFONO","EMAIL"};
		int punto = file.getPath().lastIndexOf('.');
		String extension = file.getPath().substring(punto + 1).toLowerCase();
		try {
			if (extension.equals("csv")) {
				WriterCsvRubrica.writeCsvFile(intestazione, contatti, file, true);
			} else if (extension.equals("xml")) {
				WriterXmlRubrica.writeXmlFile(contatti, file, true);
			}
			
			successo = true;
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return successo;
	}
	
	public static List<Contatto> getContattiFromDB() {
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Contatto> contatti = new ArrayList<Contatto>();
		
		try {
			connection = DBManager.getMySqlConnection(DBManager.DB_URL, DBManager.DB_USER, DBManager.DB_PASSWORD);
			ps = connection.prepareStatement(RubricaPreparedStatements.SELECT_FROM_RUBRICA);
			rs = ps.executeQuery();
			while(rs.next()) {
				Contatto contatto = new Contatto();
				contatto.setNome(rs.getString("nome"));
				contatto.setCognome(rs.getString("cognome"));
				contatto.setTelefono(rs.getString("telefono"));
				contatto.setEmail(rs.getString("email"));
				
				contatti.add(contatto);
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				ps.close();
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return contatti;
	}
}
