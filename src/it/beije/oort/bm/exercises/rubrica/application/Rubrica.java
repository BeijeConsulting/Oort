package it.beije.oort.bm.exercises.rubrica.application;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.xml.sax.SAXException;

import it.beije.oort.bm.exercises.rubrica.*;

public class Rubrica {
	private static final File RUBRICA = new File("./src/it/beije/oort/bm/exercises/rubrica/application/rubrica.xml");
	private static List<Contatto> contacts;
	
	public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException, TransformerException {
		if(RUBRICA.exists()) contacts = XmlParser.readContatti(RUBRICA);
		else contacts = new ArrayList<>();
		List<Contatto> temporaryContacts = new ArrayList<>();
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		String command;
		System.out.println("Welcome to Brando's Magical Rubrica Application!");
		do {
			System.out.println("Type: \n1 - for adding new contact \nexit - for leaving the application");
			command = reader.readLine();
			switch(command) {
				case "1":
					Contatto c = addNewContact(reader);
					if(c != null) temporaryContacts.add(c);
					break;
			}
		} while(!command.equalsIgnoreCase("exit"));
		contacts = ListUtilities.mergeLists(contacts, temporaryContacts);
		XmlParser.writeFile(RUBRICA, contacts);
		System.out.println("Goodbye than...");

	}

	private static Contatto addNewContact(BufferedReader reader) throws IOException {
		String cognome = "", nome = "", telefono = "", email = "";
		boolean requirement = false;
		while(!requirement) {
			System.out.println("Please, type contact surname");
			cognome = reader.readLine();
			System.out.println("Please, type contact name");
			nome = reader.readLine();
			System.out.println("Please, type contact phone number");
			telefono = reader.readLine();
			System.out.println("Please, type contact e-mail");
			email = reader.readLine();
			requirement = !cognome.equals("") || !nome.equals("") || !telefono.equals("") || !email.equals("");
			if(!requirement) System.out.println("At least 1 field is required.");
		}
		Contatto c = new Contatto(nome, cognome, telefono, email);
		System.out.println();
		boolean exit = false;
		while(!exit) {
			System.out.println("You inserted the following data:");
			System.out.println();
			System.out.println(c);
			System.out.println();
			System.out.println("Are you ok with these or you want to make some changes? Type:");
			System.out.println();
			System.out.println("1 - to save the new contact\n2 - to change surname\n3 - to change name\n4 - to change phone number\n5 - to change e-mail\n6 - to abort operation");
			System.out.println();
			switch(reader.readLine()) {
				case "1":
					exit = true;
					break;
				case "2":
					System.out.println("Please, type contact surname");
					cognome = reader.readLine();
					c.setCognome(cognome);
					break;
				case "3":
					System.out.println("Please, type contact name");
					nome = reader.readLine();
					c.setNome(nome);
					break;
				case "4":
					System.out.println("Please, type contact phone number");
					telefono = reader.readLine();
					c.setTelefono(telefono);
					break;
				case "5":
					System.out.println("Please, type contact e-mail");
					email = reader.readLine();
					c.setEmail(email);
					break;
				case "6":
					c=null;
					exit = true;
					break;
				default:
					System.out.println("Think about that a little more...");
			}
		}
		return c;
		
		
	}

}
