package it.beije.oort.bru.db;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;

import it.beije.oort.files.Contatto;

public class Client {

	public static void main(String[] args) throws IOException, ParserConfigurationException, SAXException, SQLException, ClassNotFoundException {
		List<Contatto> rubrica = new ArrayList<Contatto>();
		DBUtilities.exportDB(rubrica);
		int option = 0;
		Scanner keyboard = new Scanner(System.in);
		while (option != 5) {
			System.out.println("1. Visualizzazione contatti");
			System.out.println("2. Modifica/Cancellazione contatti");
			System.out.println("3. Inserimento contatto");
			System.out.println("4. Export");
			System.out.println("5. Esci");
			option = Integer.parseInt(keyboard.nextLine());
			switch (option) {
			case 1: 
				System.out.println("1. Ricerca contatto.");
				System.out.println("2. Visualizza tutti i contatti.");
				option = Integer.parseInt(keyboard.nextLine());
				if (option == 1) {
					System.out.println("Ricerca contatto per:");
					System.out.println("1. nome");
					System.out.println("2. cognome");
					System.out.println("3. telefono");
					System.out.println("4. email");
					option = Integer.parseInt(keyboard.nextLine());
					switch (option) {
					case 1:
						System.out.println("Inserisci nome:");
						String nome = keyboard.nextLine();
						System.out.println(DBUtilities.searchContact("nome",nome));
					case 2:
						System.out.println("Inserisci cognome:");
						String cognome = keyboard.nextLine();
						System.out.println(DBUtilities.searchContact("cognome",cognome));
					case 3:
						System.out.println("Inserisci telefono:");
						String telefono = keyboard.nextLine();
						System.out.println(DBUtilities.searchContact("telefono",telefono));
					case 4:
						System.out.println("Inserisci cognome:");
						String email = keyboard.nextLine();
						System.out.println(DBUtilities.searchContact("email",email));
					}
				} else {
					//IMPAGINAZIONE
//					List<Contatto> contacts = new ArrayList<Contatto>();
//					DBUtilities.exportDB(contacts);
					Map<Integer, List<Contatto>> pages = DBUtilities.layout(rubrica);
					List<Contatto> contactForPage;
					int page = 0;
					while (page != -1) {
						System.out.println("Inserisci il numero della pagina o premi -1 per uscire dal menu impaginazione.");
						System.out.println("--------------------PAGINA "+page+" DI "+pages.keySet().size()+"----------------------");
						try {
							contactForPage = pages.get(page);
							for (int i = 0; i < contactForPage.size(); i++) {
								System.out.println(contactForPage.get(i));
							}
							page = Integer.parseInt(keyboard.nextLine());
						} catch (NullPointerException e) {
							System.out.println("ERRORE: PAGINA NON ESISTENTE");
							break;
						}
					}
				}
				break;
			case 2:
				System.out.println("1. Modifica contatto.");
				System.out.println("2. Cancella contatto.");
				option = Integer.parseInt(keyboard.nextLine());
				System.out.println("Inserisci id contatto:");
				int id = Integer.parseInt(keyboard.nextLine());
				if (option == 1) {
					System.out.println(DBUtilities.exportContact(rubrica, id));
					System.out.println("Modifica nome, cognome, email, telefono:");
					String paramMod = keyboard.nextLine();
					System.out.println("Inserisci nuovo valore:");
					String newValue = keyboard.nextLine();
					DBUtilities.modifyContact(paramMod, newValue, id);
				} else {
					System.out.println(DBUtilities.exportContact(rubrica, id));
					DBUtilities.deleteContact(id);
					System.out.println("Contatto eliminato");
				}
				break;
			case 3:
				System.out.println("Inserisci nome:");
				String nome = keyboard.nextLine();
				System.out.println("Inserisci cognome:");
				String cognome = keyboard.nextLine();
				System.out.println("Inserisci telefono:");
				String telefono = keyboard.nextLine();
				System.out.println("Inserisci email");
				String email = keyboard.nextLine();
				//metodo per inserire un record.
				break;
			case 4:
				System.out.println("1. Esporta csv");
				System.out.println("2. Esporta xml");
				option = Integer.parseInt(keyboard.nextLine());
				if (option == 1) {
					//metodo per esportare csv
				} else {
					//metodo per esportare csv
				}
				break;
			case 5:
				System.out.println("arrivederci");
				break;
			default:
				System.out.println("inserisci un'opzione valida");
				break;
			}
		}
		keyboard.close();
	}
}
