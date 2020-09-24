package it.beije.oort.bm.exercises.rubrica.db;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import it.beije.oort.bm.exercises.rubrica.Contatto;
import it.beije.oort.bm.exercises.rubrica.CsvContactsReader;
import it.beije.oort.bm.exercises.rubrica.XmlParser;

public class DBImport {

	public static void main(String[] args) {
		if(args.length != 1) {
			System.out.println("Correct usage: java DBImport <sourceFile>");
			System.exit(0);
		}
		Database db = Database.getDatabase();
		File source = new File(args[0]);
		List<Contatto> contacts = null;
		try {
			if(args[0].endsWith(".csv")) {
				contacts = CsvContactsReader.readFile(source);
			}else if(args[0].endsWith(".xml")) {
				contacts = XmlParser.readContatti(source);
			}else {
				System.out.println("The specified file must be a .csv or .xml file");
			}
		} catch (Exception e1) {
			e1.printStackTrace();
			System.exit(0);
		}
		try {
			Connection conn = db.getConnection();
			PreparedStatement stmt = conn.prepareStatement("INSERT INTO rubrica (cognome, nome, telefono, email) " +
					"VALUES (?,?,?,?)");
			for(Contatto c : contacts) {
				stmt.setString(1, c.getCognome());
				stmt.setString(2, c.getNome());
				stmt.setString(3, c.getTelefono());
				stmt.setString(4, c.getEmail());
				stmt.executeUpdate();
			}
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				db.closeConnection();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		

	}

}
