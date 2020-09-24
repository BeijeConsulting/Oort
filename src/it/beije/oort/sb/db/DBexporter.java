package it.beije.oort.sb.db;

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

import org.w3c.dom.DOMException;

import it.beije.oort.rubrica.Contatto;
import it.beije.oort.rubrica.RubricaCsvXml;

public class DBexporter {
	
	public static List<Contatto> preparedSelect(String tabella) {
		List<Contatto> list = new ArrayList<Contatto>();
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;		
		try {
			connection = DBManager.getMySqlConnection(DBManager.DB_URL, DBManager.DB_USER, DBManager.DB_PASSWORD);
						
			ps = connection.prepareStatement("SELECT * FROM "+tabella);
			rs = ps.executeQuery();
			
			while (rs.next()) {
				Contatto c = new Contatto();
				c.setNome(rs.getString("nome"));
				c.setCognome(rs.getString("cognome"));
				c.setEmail(rs.getString("email"));
				c.setTelefono(rs.getString("Telefono"));
				list.add(c);
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
		return list;
	}

		public static void main(String[] args) throws DOMException, IOException, ParserConfigurationException, TransformerException {
			File file = new File("./src/it/beije/oort/rubrica/rubricaDB.xml");
			RubricaCsvXml.rubricaWriter(file, preparedSelect("rubrica"));
		}
}
