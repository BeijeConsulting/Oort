package it.beije.oort.bru.db.biblioteca;

import java.util.List;
import java.util.Scanner;
import it.beije.oort.bru.db.JPDBUtilities;
import it.beije.oort.bru.db.Utilities;

public class ClientBiblioteca {

	public static void main(String args[]) {
		int option = 0;
		Scanner keyboard = new Scanner(System.in);
		while (option != 4) {
			System.out.println("1. Registra nuovo libro");
			System.out.println("2. Registra nuovo utente");
			System.out.println("3. Prendi in prestito un libro");
			System.out.println("4. Esci");
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
					//List<Autore> authors = new ArrayList<Autore>();
					int lastAuthorID = Utilities.printAuthors(JPDBUtilities.exportAuthors());
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
						JPDBUtilities.insertAuthor(cognome, nome, data_nascita, data_morte, biografia);
						System.out.println("autore inserito!");
						idAutore = lastAuthorID + 1;
					} 
					System.out.println("Scegli l'editore dalla seguente lista (inserisci id). Se non è presente inserisci -1");
					//List<Editore> publishers = new ArrayList<Editore>();
					int lastPublisherID = Utilities.printPublishers(JPDBUtilities.exportPublishers());
					int idEditore = Integer.parseInt(keyboard.nextLine());
					if (idEditore == -1) {
						//INSERISCI NUOVO EDITORE SE NON PRESENTE 
						System.out.println("Inserisci il nome della casa editrice:");
						String denominazione = keyboard.nextLine();
						System.out.println("Inserisci descrizione della casa editrice:");
						String descrizioneEditore = keyboard.nextLine();
						JPDBUtilities.insertPublisher(denominazione, descrizioneEditore);
						idEditore = lastPublisherID + 1;
					}
					System.out.println("Inserisci l'anno di pubblicazione del libro:");
					int anno = Integer.parseInt(keyboard.nextLine());
					JPDBUtilities.insertBook(titolo, descrizioneLibro, idAutore, idEditore, anno);
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
					JPDBUtilities.insertUser(nome, cognome, telefono, email, codice_fiscale, indirizzo);
					System.out.println("Nuovo utente registrato.");
					break;
				case 3:
					//CERCA UN LIBRO E VERIFICANE LA DISPONIBLITA'
					System.out.println("Inserisci il tuo id utente per cercare un libro e verificarne la disponibilità:");
					int idUtente = Integer.parseInt(keyboard.nextLine());
					System.out.println("Inserisci titolo del libro:");
					titolo = keyboard.nextLine();
					Libro book = JPDBUtilities.exportBook(titolo);
					System.out.println(book);
					int idLibro = book.getId();
					List<Prestito> loans = JPDBUtilities.exportLoans();
					//Non fa ancora il check sulle date, ma solo se il libro è preso in prestito da un altro utente.
					boolean available = JPDBUtilities.isAvalaible(idUtente, idLibro);
					if (available) {
						//REGISTRA NUOVO PRESTITO
						System.out.println("Data inizio prestito:");
						String data_inizio = keyboard.nextLine();
						System.out.println("Data fine prestito:");
						String data_fine = keyboard.nextLine();
						System.out.println("Inserisci note aggiuntive:");
						String note = keyboard.nextLine();
						JPDBUtilities.insertLoan(idLibro, idUtente, data_inizio, data_fine, note);
						System.out.println("Nuovo prestito registrato.");
					} else {
						System.out.println("Libro non disponibile");
					}
					break;
				case 4:
					System.out.println("Arrivederci!");
					break;
				default:
					System.out.println("Opzione non valida.");
					break;
				}
			} catch (NumberFormatException e) {
				System.out.println("Inserire un numero.");
			}
		}
		keyboard.close();
	}
}
