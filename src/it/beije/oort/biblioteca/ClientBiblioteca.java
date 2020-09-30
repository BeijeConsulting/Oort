package it.beije.oort.biblioteca;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import it.beije.oort.bru.db.HDBUtilities;
import it.beije.oort.bru.db.Utilities;

public class ClientBiblioteca {

	public static void main(String[] args) {
		int option = 0;
		Scanner keyboard = new Scanner(System.in);
		while (option != 5) {
			System.out.println("1. Inserisci nuovo libro");
			System.out.println("2. Registra nuovo utente");
			System.out.println("3. Registra un prestito");
			System.out.println("4. Cerca libro");
			System.out.println("5. Esci");
			try {
				option = Integer.parseInt(keyboard.nextLine());
				switch (option) {
				case 1:
					//INSERISCI NUOVO LIBRO NEL DATABASE
					System.out.println("Inserisci titolo:");
					String titolo = keyboard.nextLine();
					System.out.println("Inserisci descrizione:");
					String descrizioneLibro = keyboard.nextLine();
					System.out.println("Scegli l'auotore dalla seguente lista (inserisci id). Se non è presente inserisci -1");
					List<Autore> authors = new ArrayList<Autore>();
					HDBUtilities.exportAuthors(authors);
					int lastAuthorID = Utilities.printAuthors(authors);
					int idAutore = Integer.parseInt(keyboard.nextLine());
					if (idAutore == -1) {
						//INSERISCI NUOVO AUTORE SE NON PRESENTE
						System.out.println("Inserisci nome:");
						String nome = keyboard.nextLine();
						System.out.println("Inserisci cognome:");
						String cognome = keyboard.nextLine();
						System.out.println("Inserisci data_nascita:");
						String data_nascita = keyboard.nextLine();
						System.out.println("Inserisci data_morte:");
						String data_morte = keyboard.nextLine();
						System.out.println("Inserisci biografia:");
						String biografia = keyboard.nextLine();
						HDBUtilities.insertAuthor(cognome, nome, data_nascita, data_morte, biografia);
						System.out.println("autore inserito!");
						idAutore = lastAuthorID + 1;
					} 
					System.out.println("Scegli l'editore dalla seguente lista (inserisci id). Se non è presente inserisci -1");
					List<Editore> publishers = new ArrayList<Editore>();
					HDBUtilities.exportPublishers(publishers);
					int lastPublisherID = Utilities.printPublishers(publishers);
					int idEditore = Integer.parseInt(keyboard.nextLine());
					if (idEditore == -1) {
						//INSERISCI NUOVO EDITORE SE NON PRESENTE 
						System.out.println("Inserisci il nome della casa editrice:");
						String denominazione = keyboard.nextLine();
						System.out.println("Inserisci descrizione della casa editrice:");
						String descrizioneEditore = keyboard.nextLine();
						HDBUtilities.insertPublisher(denominazione, descrizioneEditore);
						idEditore = lastPublisherID + 1;
					}
					System.out.println("Inserisci l'anno di pubblicazione del libro:");
					int anno = Integer.parseInt(keyboard.nextLine());
					HDBUtilities.insertBook(titolo, descrizioneLibro, idAutore, idEditore, anno);
					break;
				case 2:
					//REGISTRAZIONE NUOVO UTENTE
					System.out.println("Inserisci il nome dell'utente:");
					String nome = keyboard.nextLine();
					System.out.println("Inserisci il cognome dell'utente:");
					String cognome = keyboard.nextLine();
					System.out.println("Inserisci il numero di telefono:");
					String telefono = keyboard.nextLine();
					System.out.println("Inserisci l'email:");
					String email = keyboard.nextLine();
					System.out.println("Inserisci codice fiscale:");
					String codice_fiscale = keyboard.nextLine();
					System.out.println("Inserisci indirizzo:");
					String indirizzo = keyboard.nextLine();
					HDBUtilities.insertUser(nome, cognome, telefono, email, codice_fiscale, indirizzo);
					System.out.println("Nuovo utente registrato.");
					break;
				case 3:
					//REGISTRAZIONE NUOVO PRESTITO
					System.out.println("opzione non ancora disponibile");
				case 5:
					System.out.println("Arrivederci!");
					break;
				default:
					System.out.println("Opzione non valida.");
					break;
				}
			} catch (NumberFormatException e) {
				System.out.println("Inserire un numero da 1 a 5.");
			}
		}
		keyboard.close();
	}
}
