package it.beije.oort.bm.rubrica.application;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import it.beije.oort.bm.rubrica.Contatto;
import it.beije.oort.bm.rubrica.database.Database;
import it.beije.oort.bm.rubrica.database.HibernateDatabase;
import it.beije.oort.bm.rubrica.utils.XmlParser;

public class RubricaClient {
	private static Database db;
	private static BufferedReader in;
	public static void main(String[] args) throws SQLException, IOException, ParserConfigurationException, TransformerException {
		db = HibernateDatabase.getDatabase();
		List<Contatto> contacts;
		String command;
		in = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Welcome to Brando's Rubrica");
		while(true) {
			System.out.println();
			System.out.println("1 - View all contacts");
			System.out.println("2 - Search contact");
			System.out.println("3 - Add contact");
			System.out.println("4 - Update contact");
			System.out.println("5 - Delete contact");
			System.out.println("6 - Export contacts");
			System.out.println("0 - Exit");
			command = in.readLine();
			switch(command) {
				case "1":
					contacts = db.selectAll();
					if(contacts != null) pagedPrint(contacts);
					else System.err.println("Questo è un problema");
					break;
				case "2":
					contacts = selectContact();
					if(contacts != null) pagedPrint(contacts);
					else System.out.println("At least one value is required.");
					break;
				case "3":
					System.out.println(addContact() ? "OK" : "FAIL");
					break;
				case "4":
					System.out.println(updateContact() ? "OK" : "FAIL");
					break;
				case "5":
					System.out.println(deleteContact() ? "OK" : "FAIL");
					break;
				case "6":
					exportContacts();
					break;
				case "0":
					System.out.println("See ya!");
					System.exit(0);
					break;
				default:
					System.out.println("What do you mean?");
			}
		}

	}

	private static void pagedPrint(List<Contatto> contacts) throws IOException {
		if(contacts.size() == 0) {
			System.out.println(contacts);
			return;
		}
		int pages = contacts.size()/10 + (contacts.size()%10 > 0 ? 1 : 0);
		int page = 1;
		int start = 0;
		int end = contacts.size() < 10 ? contacts.size() : 10;
		String command;
		boolean previous, next;
		do {
			previous = page > 1;
			next = page < pages;
			for(int i = start; i<end; i++) {
				System.out.println(contacts.get(i));
			}
			System.out.println();
			System.out.println("Page " + page + "      " + start + " to " + end + " of " + contacts.size());
			System.out.println();
			System.out.println((previous ? "1 - previous    " : "") + (next ? "2 - next    " : "") + "0 - back");
			command = in.readLine();
			switch(command) {
				case "0":
					return;
				case "2":
					if(next) {
						page++;
						start = end;
						end += 10;
						if(end > contacts.size()) {
							end = contacts.size();
						}
					}	
					break;
				case "1":
					if(previous) {
						page--;
						start -= 10;
						end = start + 10;
					}
					break;
				default:
					System.out.println("What do you mean?");
			}
		}while(true);
		
	}

	private static List<Contatto> selectContact() throws IOException, SQLException {
		String[] vals = new String[4];
		boolean[] sel = new boolean[4];
		System.out.println("Insert surname:");
		vals[0] = in.readLine().trim();
		if(vals[0] == null || vals[0].equals("")) sel[0] = false;
		else sel[0] = true;
		System.out.println("Insert name:");
		vals[1] = in.readLine().trim();
		if(vals[1] == null || vals[1].equals("")) sel[1] = false;
		else sel[1] = true;
		System.out.println("Insert phone number:");
		vals[2] = in.readLine().trim();
		if(vals[2] == null || vals[2].equals("")) sel[2] = false;
		else sel[2] = true;
		System.out.println("Insert email:");
		vals[3] = in.readLine().trim();
		if(vals[3] == null || vals[3].equals("")) sel[3] = false;
		else sel[3] = true;
		
		return db.select(sel, vals);
	}

	private static boolean addContact() throws IOException, SQLException {
		Contatto c = new Contatto();
		System.out.println("Insert surname:");
		c.setCognome(in.readLine().trim());
		System.out.println("Insert name:");
		c.setNome(in.readLine().trim());
		System.out.println("Insert phone number:");
		c.setTelefono(in.readLine().trim());
		System.out.println("Insert email:");
		c.setEmail(in.readLine().trim());
		return db.insert(c);
		
	}

	private static boolean updateContact() throws IOException, SQLException {
		int id;
		String[] vals = new String[4];
		boolean[] sel = new boolean[4];
		System.out.println("Insert contact id:");
		try {
			id = Integer.parseInt(in.readLine().trim());
		}catch(NumberFormatException nfe) {
			System.out.println("This is not a number!");
			return false;
		}
		System.out.println("Insert surname:");
		vals[0] = in.readLine().trim();
		if(vals[0] == null || vals[0].equals("")) sel[0] = false;
		else sel[0] = true;
		System.out.println("Insert name:");
		vals[1] = in.readLine().trim();
		if(vals[1] == null || vals[1].equals("")) sel[1] = false;
		else sel[1] = true;
		System.out.println("Insert phone number:");
		vals[2] = in.readLine().trim();
		if(vals[2] == null || vals[2].equals("")) sel[2] = false;
		else sel[2] = true;
		System.out.println("Insert email:");
		vals[3] = in.readLine().trim();
		if(vals[3] == null || vals[3].equals("")) sel[3] = false;
		else sel[3] = true;
		
		return db.update(id, sel, vals);
		
	}
	
	private static boolean deleteContact() throws IOException, SQLException {
		int id;
		System.out.println("Insert contact id:");
		try {
			id = Integer.parseInt(in.readLine().trim());
		}catch(NumberFormatException nfe) {
			System.out.println("This is not a number!");
			return false;
		}
		return db.delete(id);
		
	}
	
	private static void exportContacts() throws SQLException, ParserConfigurationException, TransformerException {
		List<Contatto> contacts = db.selectAll();
		XmlParser.writeFile(new File("exported_contacts.xml"), contacts);
		
	}


}
