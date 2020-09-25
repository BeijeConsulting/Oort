package it.beije.oort.bm.dbclient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.List;
import it.beije.oort.bm.dbclient.database.Database;

public class RubricaClient {
	private static Database db;
	private static BufferedReader in;
	public static void main(String[] args) throws SQLException, IOException {
		db = Database.getDatabase();
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
				System.out.println(contacts);
				break;
			case "2":
				contacts = selectContact();
				System.out.println(contacts);
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
				exportContact();
				break;
			case "0":
				System.out.println("See ya!");
				System.exit(0);
				break;
			default:
				System.out.println("What do you mean?");
		}
		}
		
//		String command, token;
//		System.out.println("Welcome to Brando's Rubrica");
//		command = in.readLine();
//		StringTokenizer st = new StringTokenizer(command);
//		switch(st.nextToken().toLowerCase()) {
//			case "select":
//				switch(st.nextToken().toLowerCase()) {
//					case "all":
//						contacts = db.selectAll();
//						System.out.println(contacts);
//						break;
//					default:
//						do {
//							
//						}while()
//				}
//				break;
//			case "add":
//				break;
//			case "delete":
//				break;
//			case "export":
//				break;
//			case "exit":
//				break;
//			default:
//				
//		}
//		

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
	
	private static void exportContact() {
		System.out.println("Not implemented yet.");
		
	}


}
