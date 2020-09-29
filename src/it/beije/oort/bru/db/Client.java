package it.beije.oort.bru.db;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import it.beije.oort.files.Contatto;
import it.beije.oort.files.CsvParser;
import it.beije.oort.files.XmlParser;

public class Client {

	public static void main(String[] args) {
		int option = 0;
		Scanner keyboard = new Scanner(System.in);
		while (option != 5) {
			System.out.println("1. Visualizzazione contatti");
			System.out.println("2. Modifica/Cancellazione contatti");
			System.out.println("3. Inserimento contatto");
			System.out.println("4. Export");
			System.out.println("5. Esci");
			try {
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
							System.out.println(HDBUtilities.searchContact("nome",nome));
							break;
						case 2:
							System.out.println("Inserisci cognome:");
							String cognome = keyboard.nextLine();
							System.out.println(HDBUtilities.searchContact("cognome",cognome));
							break;
						case 3:
							System.out.println("Inserisci telefono:");
							String telefono = keyboard.nextLine();
							System.out.println(HDBUtilities.searchContact("telefono",telefono));
							break;
						case 4:
							System.out.println("Inserisci email:");
							String email = keyboard.nextLine();
							System.out.println(HDBUtilities.searchContact("email",email));
							break;
						}
					} else {
						//IMPAGINAZIONE
						List<Contatto> contacts = new ArrayList<Contatto>();
						HDBUtilities.exportDB(contacts);
						Map<Integer, List<Contatto>> pages = Utilities.layout(contacts);
						List<Contatto> contactForPage;
						int page = 1;
						while (page != -1) {
							System.out.println("Inserisci il numero della pagina o premi -1 per uscire dal menu impaginazione.");
							System.out.println("---------------PAGINA "+page+" DI "+pages.keySet().size()+"---------------");
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
					List<Contatto> contacts = new ArrayList<Contatto>();
					HDBUtilities.exportDB(contacts);
					System.out.println("1. Modifica contatto.");
					System.out.println("2. Cancella contatto.");
					option = Integer.parseInt(keyboard.nextLine());
					System.out.println("Inserisci id contatto:");
					int id = Integer.parseInt(keyboard.nextLine());
					if (option == 1) {
						System.out.println(HDBUtilities.exportContact(contacts, id));
						System.out.println("Modifica nome, cognome, email, telefono:");
						String paramMod = keyboard.nextLine();
						System.out.println("Inserisci nuovo valore:");
						String newValue = keyboard.nextLine();
						HDBUtilities.editContact(paramMod, newValue, id);
					} else {
						System.out.println(DBUtilities.exportContact(contacts, id));
						HDBUtilities.deleteContact(id);
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
					HDBUtilities.insertRecord(cognome, nome, telefono, email);
					break;
				case 4:
					contacts = new ArrayList<Contatto>();
					System.out.println("Dove vuoi salvare il file? Inserisci il path");
					String path = keyboard.nextLine();
					System.out.println("Inserisci il nome del file");
					String fileName = keyboard.nextLine();
					File file = new File(path + "/" + fileName);
					if (fileName.contains(".csv")) {
						HDBUtilities.exportDB(contacts);
						try {
							CsvParser.buildContatti(contacts, file);
						} catch (IOException e) {
							e.printStackTrace();
						}
						System.out.println("Completato");
					} else {
						HDBUtilities.exportDB(contacts);
						try {
							XmlParser.buildContatti(contacts, file);
							System.out.println("Completato");
						} catch (ParserConfigurationException e) {
							e.printStackTrace();
						} catch (TransformerException e) {
							e.printStackTrace();
						}
					}
					break;
				case 5:
					System.out.println("arrivederci");
					break;
				default:
					System.out.println("inserisci un'opzione valida");
					break;
				}
			} catch (NumberFormatException e) {
				System.out.println("Inserisci un'opzione valida!");
			}
		}
		keyboard.close();
	}
}
