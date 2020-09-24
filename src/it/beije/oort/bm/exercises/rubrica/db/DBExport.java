package it.beije.oort.bm.exercises.rubrica.db;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import it.beije.oort.bm.exercises.rubrica.Contatto;
import it.beije.oort.bm.exercises.rubrica.CsvContactsWriter;
import it.beije.oort.bm.exercises.rubrica.XmlParser;

public class DBExport {

	public static void main(String[] args) {
		if(args.length != 1) {
			System.out.println("Correct usage: java DBImport <destFile>");
			System.exit(0);
		}
		if(!args[0].endsWith(".csv")&&!args[0].endsWith(".xml")) {
			System.out.println("The specified file must be a .csv or .xml file");
			System.exit(0);
		}
		Database db = Database.getDatabase();
		File dest = new File(args[0]);
		List<Contatto> contacts = new ArrayList<>();
		try {
			Connection conn = db.getConnection();
			String query = "SELECT cognome, nome, telefono, email FROM rubrica";
			PreparedStatement stmt = conn.prepareStatement(query);
			ResultSet rs = stmt.executeQuery();
			String name, surname, phone, email;
			while(rs.next()) {
				surname = rs.getString(1);
				name = rs.getString(2);
				phone = rs.getString(3);
				email = rs.getString(4);
				contacts.add(new Contatto(name, surname, phone, email));
			}
			rs.close();
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
		
		try {
			if(args[0].endsWith(".xml")) {
				XmlParser.writeFile(dest, contacts);
			}else {
				CsvContactsWriter.writeFile(dest, contacts);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
