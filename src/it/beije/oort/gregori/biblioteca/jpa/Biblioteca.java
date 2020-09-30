package it.beije.oort.gregori.biblioteca.jpa;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

public class Biblioteca {
	private static Scanner sc = new Scanner(System.in);
	
	public static void showMenu() throws IOException, ParserConfigurationException, TransformerException {
		int scelta = 0;
		do {
			System.out.println("-----CMD client per database biblioteca-----");
			System.out.println("Menu: ");
			System.out.println("1) Visualizzatore.");
			System.out.println("2) Ricerca.");
			System.out.println("3) Modifica.");
			System.out.println("4) Rimozione.");
			System.out.println("5) Inserimento.");
			System.out.println("6) Export database.");
			System.out.println("7) Termina programma.");
			try {
				scelta = Integer.parseInt(sc.nextLine());
			}
			catch (NumberFormatException e) {
				scelta = 8;
			}
			switch (scelta) {
			case 1:
				Biblioteca.visualizza();
				break;
			case 2:
				System.out.println();
				System.out.println("Work in progress!");
				System.out.println();
				break;
			case 3:
				Biblioteca.modifica();
				break;
			case 4:
				Biblioteca.rimuovi();
				break;
			case 5:
				Biblioteca.inserisci();
				break;
			case 6:
				System.out.println();
				System.out.println("Work in progress!");
				System.out.println();
				break;
			case 7:
				System.out.println("Arrivederci!");
				break;
			default:
				System.out.println("ERRORE: inserire una scelta valida!");
			}
		} while(scelta != 7);
	}
	
	private static void modifica() {
		do {
			int scelta = Biblioteca.sceltaTabella();	
			switch (scelta) {
			case 1:
				UtenteUtility.modifica();
				break;
			case 2:
				LibroUtility.modifica();
				break;
			case 3:
				EditoreUtility.modifica();
				break;
			case 4:
				AutoreUtility.modifica();
				break;
			case 5:
				PrestitoUtility.modifica();
				break;
			default:
				System.out.println("ERRORE: how did you get here?");
				break;
			}
			System.out.println("Modificare altro? (s per confermare)");
		} while(sc.nextLine().charAt(0) == 's');
	}

	private static void rimuovi() {
		do {
			int scelta = Biblioteca.sceltaTabella();
			switch (scelta) {
			case 1:
				UtenteUtility.rimuovi();
				break;
			case 2:
				LibroUtility.rimuovi();
				break;
			case 3:
				EditoreUtility.rimuovi();
				break;
			case 4:
				AutoreUtility.rimuovi();
				break;
			case 5:
				PrestitoUtility.rimuovi();
				break;
			default:
				break;
			}
			System.out.println("Rimuovere altro? (s per confermare)");
		} while(sc.nextLine().charAt(0) == 's');
	}
	
	private static void inserisci() {
		do {
			int scelta = Biblioteca.sceltaTabella();
			switch (scelta) {
			case 1:
				UtenteUtility.inserisci();
				break;
			case 2:
				LibroUtility.inserisci();
				break;
			case 3:
				EditoreUtility.inserisci();
				break;
			case 4:
				AutoreUtility.inserisci();
				break;
			case 5:
				PrestitoUtility.inserisci();
				break;
			default:
				break;
			}
			System.out.println("Inserire altro? (s per confermare)");
		} while(sc.nextLine().charAt(0) == 's');
	}
	
	private static void visualizza() throws IOException, ParserConfigurationException, TransformerException {
		do {
			int scelta = Biblioteca.sceltaTabella();
			switch (scelta) {
			case 1:
				List<Utente> utenti = UtenteUtility.visualizza();
				System.out.println("Caricati " + utenti.size() + " records.");
				for (Utente utente : utenti) {
					System.out.println(utente);
				}
				break;
			case 2:
				List<Libro> libri = LibroUtility.visualizza();
				System.out.println("Caricati " + libri.size() + " records.");
				for (Libro libro : libri) {
					System.out.println(libro);
				}
				break;
			case 3:
				List<Editore> editori = EditoreUtility.visualizza();
				System.out.println("Caricati " + editori.size() + " records.");
				for (Editore editore : editori) {
					System.out.println(editore);
				}
				break;
			case 4:
				List<Autore> autori = AutoreUtility.visualizza();
				System.out.println("Caricati " + autori.size() + " records.");
				for (Autore autore : autori) {
					System.out.println(autore);
				}
				break;
			case 5:
				List<Prestito> prestiti = PrestitoUtility.visualizza();
				System.out.println("Caricati " + prestiti.size() + " records.");
				for (Prestito prenotazione : prestiti) {
					System.out.println(prenotazione);
				}
				break;
			default:
				break;
			}
			System.out.println("Visualizzare un'altra tabella? (s per confermare)");
		} while(sc.nextLine().charAt(0) == 's');
	}
	
	private static int sceltaTabella() {
		int scelta = 0;
		do {
			System.out.println("Su che tabella effettuare l'operazione?");
			System.out.println("1) Utenti");
			System.out.println("2) Libri");
			System.out.println("3) Editori");
			System.out.println("4) Autori");
			System.out.println("5) Prestiti");
			try {
				scelta = Integer.parseInt(sc.nextLine());
			} catch (NumberFormatException e) {
				scelta = 6;
			}
			if(scelta < 1 || scelta > 5) {
				System.out.println("ERRORE: inserire un valore valido!");
			} 
		} while(scelta < 1 || scelta > 5);
		return scelta;
	}
	
	private static void load() {
		JPAEntityManager.createEntityManager().close();
	}

	public static void main(String[] args) throws IOException, ParserConfigurationException, TransformerException {
		Biblioteca.load();
		Biblioteca.showMenu();
		Biblioteca.sc.close();
	}

}
